package com.mfly.services;

import com.mfly.dao.AirAwareDatabaseImpl;
import com.mfly.vo.FlightDelayInformation;
import com.mfly.vo.PassengerContactInformation;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Shirishkumar.Shetty on 18/06/2015.
 */
public class UpdateFlightDelayInformationImpl implements UpdateFlightDelayInformation {

    private AirAwareDatabase airAwareDatabase;

    public UpdateFlightDelayInformationImpl() {
        this.airAwareDatabase = new AirAwareDatabaseImpl();
    }

    public FlightDelayAcknowledgementStatusCode persistAndAcknowledgeFlightDelayInformation(FlightDelayInformation flightDelayInformation) {
        return airAwareDatabase.insertFlightDelayInformationToDB(flightDelayInformation.getFlightNumber(), flightDelayInformation.getScheduledDepartureTime());
    }

    public Collection<PassengerContactInformation> acceptFlightDelayInfoAndReturnListOfPassengersToBeNotified(String flightNumber, Date newDepartureTime) {
        airAwareDatabase.insertFlightDelayInformationToDB(flightNumber, newDepartureTime);
        return airAwareDatabase.getListOfPassengersForFlight(flightNumber);
    }

	@Override
	public Collection<PassengerContactInformation> acceptFlightndReturnPassengerToBeNotified(
			String flightNo) {
		
		return airAwareDatabase.getListOfPassengersForFlight(flightNo);
	}
}
