package com.mfly.services;

import java.util.Collection;
import java.util.Date;

import com.mfly.vo.FlightDelayInformation;
import com.mfly.vo.PassengerContactInformation;

/**
 * Created by Shirishkumar.Shetty on 18/06/2015.
 */
public interface UpdateFlightDelayInformation {

    FlightDelayAcknowledgementStatusCode persistAndAcknowledgeFlightDelayInformation(FlightDelayInformation flightDelayInformation);

    Collection<PassengerContactInformation> acceptFlightDelayInfoAndReturnListOfPassengersToBeNotified(String flightNumber, Date newDepartureTime);
    
    	Collection<PassengerContactInformation> acceptFlightndReturnPassengerToBeNotified(
			String flightNo);

}
