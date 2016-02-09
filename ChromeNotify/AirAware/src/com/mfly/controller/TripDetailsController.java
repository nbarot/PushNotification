package com.mfly.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.mfly.services.NotificationService;
import com.mfly.services.NotificationServiceImpl;
import com.mfly.services.UpdateFlightDelayInformationImpl;
import com.mfly.vo.FFPDetails;
import com.mfly.vo.FlightDetails;
import com.mfly.vo.PassengerContactInformation;


@Controller
public class TripDetailsController {
	
	
	@RequestMapping(value = "/tripDetails", method = RequestMethod.POST)
	public ModelAndView callTripAdvicerAPI(HttpServletRequest request, 
	        HttpServletResponse response) {
		
		String output = null;
		try {
			
			StringBuilder sb = new StringBuilder();
			sb.append("http://104.131.181.101:8983/solr/airaware4/select?q=FLIGHT_NUMBER:") ;
			sb.append(request.getParameter("tripNumber"));
			sb.append("&wt=json");
			URL url = new URL(sb.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
	 
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
	 
			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
	 
			
			System.out.println("Output from Server .... \n");			

			output = br.readLine();
			System.out.println(output);
			conn.disconnect();
	 
		  } catch (MalformedURLException e) {
	 
			e.printStackTrace();
	 
		  } catch (IOException e) {
	 
			e.printStackTrace();
	 
		  } 
		
			
		Gson gson = new Gson();
		
		FFPDetails ffpDetails = null ; 
		if(output != null){
			ffpDetails = gson.fromJson(output, FFPDetails.class);
				
		}	
		
		sendNotification(request.getParameter("tripNumber"), ffpDetails);
		
		return new ModelAndView("flightUpdate","Hello", ffpDetails);
		
	}

	private void sendNotification(String freqFlyerNo, FFPDetails ffpFlightList) {	
		

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

		try {
			for (FlightDetails flight : ffpFlightList.getResponse().getFlightList()) {
				
					if(flight.getOLD_FARE()[0] != flight.getNEW_FARE()[0]) {
						triggerPush(freqFlyerNo) ;
					}		

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	private void triggerPush(String flightNo) {
		
			NotificationService notification = new NotificationServiceImpl() ;
			UpdateFlightDelayInformationImpl impl = new UpdateFlightDelayInformationImpl();
			Collection<PassengerContactInformation> passengers = impl
					.acceptFlightndReturnPassengerToBeNotified(flightNo);
			// publish notification
			for(PassengerContactInformation passenger : passengers) {
				notification.sendPushNotification(passenger);
			}			
		
	}

}
