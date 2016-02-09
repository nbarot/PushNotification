package com.mfly.vo;

/**
 * Created by Shirishkumar.Shetty on 18/06/2015.
 */
public class FlightDelayNotification {

    private PassengerContactInformation passengerContactInformation;
    private FlightDelayInformation flightDelayInformation;

    public PassengerContactInformation getPassengerContactInformation() {
        return passengerContactInformation;
    }

    public void setPassengerContactInformation(PassengerContactInformation passengerContactInformation) {
        this.passengerContactInformation = passengerContactInformation;
    }

    public FlightDelayInformation getFlightDelayInformation() {
        return flightDelayInformation;
    }

    public void setFlightDelayInformation(FlightDelayInformation flightDelayInformation) {
        this.flightDelayInformation = flightDelayInformation;
    }


}
