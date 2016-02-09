package com.mfly.services;

import java.util.Collection;
import java.util.Date;

import com.mfly.vo.FlightDelayInformation;
import com.mfly.vo.PassengerContactInformation;

/**
 * Created by Shirishkumar.Shetty on 18/06/2015.
 */
public interface AirAwareDatabase {

    
	FlightDelayAcknowledgementStatusCode insertFlightDelayInformationToDB(String flightNumber, Date newDepartureTime);
    Collection<FlightDelayInformation> getListOfAllDelayedFlights();
    Collection<PassengerContactInformation> getListOfPassengersForFlight(String flightNumber);

    public SubscriptionStatusCode registerDevice(String ffp, String subscriptionId);
	PassengerContactInformation getPassengerForFFP(String freqFlyerNo);
}
