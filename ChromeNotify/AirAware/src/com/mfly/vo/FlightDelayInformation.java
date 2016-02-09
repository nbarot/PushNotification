package com.mfly.vo;

import java.util.Date;

public class FlightDelayInformation {

    private String flightNumber;
    private String flightSource;
    private String flightDestination;
    private String flightStatus;
    private Date scheduledDepartureTime;

    public FlightDelayInformation() {
    }


    public FlightDelayInformation(String flightDestination, String flightNumber, String flightSource, String flightStatus, Date scheduledDepartureTime) {
        this.flightDestination = flightDestination;
        this.flightNumber = flightNumber;
        this.flightSource = flightSource;
        this.flightStatus = flightStatus;
        this.scheduledDepartureTime = scheduledDepartureTime;
    }

    public String getFlightNumber() {

        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFlightSource() {
        return flightSource;
    }

    public void setFlightSource(String flightSource) {
        this.flightSource = flightSource;
    }

    public String getFlightDestination() {
        return flightDestination;
    }

    public void setFlightDestination(String flightDestination) {
        this.flightDestination = flightDestination;
    }

    public String getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(String flightStatus) {
        this.flightStatus = flightStatus;
    }

    public Date getScheduledDepartureTime() {
        return scheduledDepartureTime;
    }

    public void setScheduledDepartureTime(Date scheduledDepartureTime) {
        this.scheduledDepartureTime = scheduledDepartureTime;
    }

}