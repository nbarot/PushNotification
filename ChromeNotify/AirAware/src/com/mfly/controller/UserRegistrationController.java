package com.mfly.controller;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;
import com.mfly.services.SubscriptionService;
import com.mfly.services.SubscriptionServiceImpl;
import com.mfly.services.UpdateFlightDelayInformationImpl;
import com.mfly.vo.PassengerContactInformation;
import com.mfly.vo.Reference;
 

@Controller
public class UserRegistrationController {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// The SENDER_ID here is the "Browser Key" that was generated when I
	// created the API keys for my Google APIs project.
	private static final String SENDER_ID = "AIzaSyBwgDylmvNVSB6dNRE81hswyyTpdbPq8ZU"; //"AIzaSyC8g2PYqtC1bUZiYBFY5ekR19OX7ylI7fA";

	// This is a *cheat* It is a hard-coded registration ID from an Android
	// device
	// that registered itself with GCM using the same project id shown above.
	private static final String ANDROID_DEVICE = "APA91bE_lB2gKDm6yy0s32XrzTbw9TQE2eKQiYuBe7qJ17VqWBfNNTodlJFTOeQvubsA1W4NmCi4XP3jrP2IcnNnHDQP_Sr090L8Cr0Tn8lKGISwPSk4Ri8orJlYHe3DSPAARakMnCpENPf7ep6tcYgJQ7BuMOOlBVR16vAJoWa4tikGDPIPWPg";
												  

	// This array will hold all the registration ids used to broadcast a
	// message.
	// for this demo, it will only have the ANDROID_DEVICE id that was captured
	// when we ran the Android client app through Eclipse.
	private List<String> androidTargets = null;
 
	@RequestMapping(value = "/subscribe", method = RequestMethod.POST)
	public ModelAndView helloWorld(HttpServletRequest request, 
	        HttpServletResponse response) {
 
		
		
		HttpSession session = request.getSession(true);
		
		//get passengers
		
		//send notification
		
		
		/// We'll collect the "CollapseKey" and "Message" values from our JSP page
				String collapseKey = "";
				String endpoint = "";
				String subscriptionId="";
				
				try {
					StringBuilder buffer = new StringBuilder();
				    BufferedReader reader = request.getReader();
				    String line;
				    while ((line = reader.readLine()) != null) {
				        buffer.append(line);
				    }
				    String data = buffer.toString();
				    String[] st = data.split("\\|");
				    String frequentFlyerNumber = st[2];
					subscriptionId = st[1];
					session.setAttribute("FFP", frequentFlyerNumber);
					session.setAttribute("SubScriptionId", subscriptionId);
					System.out.println("data ::" + data);
					System.out.println("subscriptionId ::" + subscriptionId);
					
					//save in db
					//update database
					SubscriptionService servce = new SubscriptionServiceImpl();
					servce.subscriberUserForPushNotifications(frequentFlyerNumber, subscriptionId);			
				} catch (Exception e) {
					e.printStackTrace();					
				}

				// Instance of com.android.gcm.server.Sender, that does the
				// transmission of a Message to the Google Cloud Messaging service.
				Sender sender = new Sender(SENDER_ID);
				
				// This Message object will hold the data that is being transmitted
				// to the Android client devices.  For this demo, it is a simple text
				// string, but could certainly be a JSON object.
				Message message = new Message.Builder()
				
				// If multiple messages are sent using the same .collapseKey()
				// the android target device, if it was offline during earlier message
				// transmissions, will only receive the latest message for that key when
				// it goes back on-line.
				.collapseKey(collapseKey)
				.timeToLive(30)
				.delayWhileIdle(true)
				.addData("message", "This is a message from Nirav Barot")
				.build();
				
				try {
					// use this for multicast messages.  The second parameter
					// of sender.send() will need to be an array of register ids.
					/*androidTargets.add(subscriptionId);
					MulticastResult result = sender.send(message, androidTargets, 1);
					
					if (result.getResults() != null) {
						int canonicalRegId = result.getCanonicalIds();
						if (canonicalRegId != 0) {
							
						}
					} else {
						int error = result.getFailure();
						System.out.println("Broadcast failure: " + error);
					}*/
					
				} catch (Exception e) {
					e.printStackTrace();
				}
		return new ModelAndView("RegistraionSuccessful", "message", message);
	}
	
	@RequestMapping(value = "/updateDelay", method = RequestMethod.POST)
	public ModelAndView updateDelay(HttpServletRequest request, 
	        HttpServletResponse response) {
		
		Map map = request.getParameterMap();
		System.out.println(request.getParameterMap());
		
		String flightNumber = request.getParameter("flightNumber");
		String estDep = request.getParameter("estDep");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		androidTargets = new ArrayList<String>();
		Date newDepartureTime = new Date();
		try {
			newDepartureTime = format.parse(estDep);
		}catch(Exception e) {
			e.printStackTrace();			
		}
		
		//update flight status
		//get all passengers
		UpdateFlightDelayInformationImpl impl = new UpdateFlightDelayInformationImpl();
		Collection<PassengerContactInformation> passengers = impl.acceptFlightDelayInfoAndReturnListOfPassengersToBeNotified(flightNumber, newDepartureTime);
		//publish notification
		
		String endpoint = "";
		String subscriptionId="";

		
		for(PassengerContactInformation passenger:passengers) {
			
			if(passenger.getReferences()!=null && !passenger.getReferences().isEmpty()) {
				for(Reference reference:passenger.getReferences()) {
					String collapseKey = "";
					
					subscriptionId = reference.getSubscriberId();
					// Instance of com.android.gcm.server.Sender, that does the
					// transmission of a Message to the Google Cloud Messaging service.
					Sender sender = new Sender(SENDER_ID);
					
					// This Message object will hold the data that is being transmitted
					// to the Android client devices.  For this demo, it is a simple text
					// string, but could certainly be a JSON object.
					Message message = new Message.Builder()
					
					// If multiple messages are sent using the same .collapseKey()
					// the android target device, if it was offline during earlier message
					// transmissions, will only receive the latest message for that key when
					// it goes back on-line.
					.collapseKey(collapseKey)
					.timeToLive(30)
					.delayWhileIdle(true)
					.addData("message", "This is a message from Nirav Barot")
					.build();
					
					try {
						// use this for multicast messages.  The second parameter
						// of sender.send() will need to be an array of register ids.
						androidTargets.add(subscriptionId);
						MulticastResult result = sender.send(message, androidTargets, 1);
						
						if (result.getResults() != null) {
							int canonicalRegId = result.getCanonicalIds();
							if (canonicalRegId != 0) {
								
							}
						} else {
							int error = result.getFailure();
							System.out.println("Broadcast failure: " + error);
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}			
		}
		
		
		return new ModelAndView("flightUpdate", "message", "Hello World");
		
	}
	
	private String getSubscriptionId(String data) {

		String[] st = data.split("\\|");
		
		return st[1];
	}
}