package com.mfly.services;

import com.mfly.dao.AirAwareDatabaseImpl;
import com.mfly.vo.FlightDelayInformation;
import com.mfly.vo.FlightDelayNotification;
import com.mfly.vo.PassengerContactInformation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Shirishkumar.Shetty on 18/06/2015.
 */
public class FlightDelayNotificationServiceImpl implements FlightDelayNotificationService {


    private AirAwareDatabase airAwareDatabase;

    public FlightDelayNotificationServiceImpl() {
        this.airAwareDatabase = new AirAwareDatabaseImpl();
    }

    public List<FlightDelayNotification> retrieveFlightDelayNotifications() {

        List<FlightDelayNotification> flightDelayNotificationList = new ArrayList<FlightDelayNotification>();

        Collection<FlightDelayInformation> flightDelayInformationList = this.airAwareDatabase.getListOfAllDelayedFlights();

        if (null == flightDelayInformationList) {
            return null;
        }

        if (flightDelayInformationList.size() > 0) {
            for (FlightDelayInformation flightDelayInformation : flightDelayInformationList) {

                Collection<PassengerContactInformation> passengerContactInformationList = this.airAwareDatabase.getListOfPassengersForFlight(flightDelayInformation.getFlightNumber());

                for (PassengerContactInformation passengerContactInformation : passengerContactInformationList) {

                    FlightDelayNotification flightDelayNotification = new FlightDelayNotification();

                    PassengerContactInformation passengerContactInformationFromDB = new PassengerContactInformation();
                    passengerContactInformationFromDB.setPassengerFirstName(passengerContactInformation.getPassengerFirstName());
                    passengerContactInformationFromDB.setPassengerLastName(passengerContactInformation.getPassengerLastName());
                    passengerContactInformationFromDB.setPassengerEmailAddress(passengerContactInformation.getPassengerEmailAddress());
                    passengerContactInformationFromDB.setDeviceID(passengerContactInformation.getDeviceID());

                    flightDelayNotification.setPassengerContactInformation(passengerContactInformationFromDB);

                    FlightDelayInformation flightDelayInformationFromDB = new FlightDelayInformation();
                    flightDelayInformationFromDB.setFlightNumber(flightDelayInformation.getFlightNumber());
                    flightDelayInformationFromDB.setScheduledDepartureTime(flightDelayInformation.getScheduledDepartureTime());

                    flightDelayNotification.setFlightDelayInformation(flightDelayInformationFromDB);

                    flightDelayNotificationList.add(flightDelayNotification);

                }


            }
        }

        return flightDelayNotificationList;

    }
}
