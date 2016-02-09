package com.mfly.services;


import java.util.ArrayList;
import java.util.List;




import javax.naming.ReferralException;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;
import com.mfly.vo.PassengerContactInformation;
import com.mfly.vo.Reference;

public class NotificationServiceImpl implements NotificationService{
	
	// The SENDER_ID here is the "Browser Key" that was generated when I
	// created the API keys for my Google APIs project.
	private static final String SENDER_ID = "AIzaSyBwgDylmvNVSB6dNRE81hswyyTpdbPq8ZU";

	// This is a *cheat* It is a hard-coded registration ID from an Android
	// device
	// that registered itself with GCM using the same project id shown above.
	private static final String ANDROID_DEVICE = "APA91bE_lB2gKDm6yy0s32XrzTbw9TQE2eKQiYuBe7qJ17VqWBfNNTodlJFTOeQvubsA1W4NmCi4XP3jrP2IcnNnHDQP_Sr090L8Cr0Tn8lKGISwPSk4Ri8orJlYHe3DSPAARakMnCpENPf7ep6tcYgJQ7BuMOOlBVR16vAJoWa4tikGDPIPWPg";
	
	public void sendPushNotification(PassengerContactInformation passenger) {
		// / We'll collect the "CollapseKey" and "Message" values from our JSP
		// page
		String collapseKey = "";
		String endpoint = "";
		String subscriptionId = "";
		List<String> androidTargets = new ArrayList<String>();	
		for(Reference reference:passenger.getReferences()){
			
			androidTargets.add(reference.getSubscriberId()) ;
		}

		// Instance of com.android.gcm.server.Sender, that does the
		// transmission of a Message to the Google Cloud Messaging service.
		Sender sender = new Sender(SENDER_ID);

		// This Message object will hold the data that is being transmitted
		// to the Android client devices. For this demo, it is a simple text
		// string, but could certainly be a JSON object.
		Message message = new Message.Builder()

				// If multiple messages are sent using the same .collapseKey()
				// the android target device, if it was offline during earlier
				// message
				// transmissions, will only receive the latest message for that
				// key when
				// it goes back on-line.
				.collapseKey(collapseKey).timeToLive(30).delayWhileIdle(true)
				.addData("message", "This is a message from Nirav Barot")
				.build();

		try {
			// use this for multicast messages. The second parameter
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
