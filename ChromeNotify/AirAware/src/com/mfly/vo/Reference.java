package com.mfly.vo;

import java.io.Serializable;
import java.util.Date;

public class Reference implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7815420070444844268L;
	
	private String flightNumber;
	private String ffpNumber;
	
//	private FlightDetails flightDetails;
	
	
	private String eventType;
	private String isActive;
	private Date expiryTime;
	private String subscriberId; // is it same as device id
	private String endPoint;
	private String serviceClass;
	
	
//	public FlightDetails getFlightDetails() {
//		return flightDetails;
//	}
//	public void setFlightDetails(FlightDetails flightDetails) {
//		this.flightDetails = flightDetails;
//	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getFfpNumber() {
		return ffpNumber;
	}
	public void setFfpNumber(String ffpNumber) {
		this.ffpNumber = ffpNumber;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public Date getExpiryTime() {
		return expiryTime;
	}
	public void setExpiryTime(Date expiryTime) {
		this.expiryTime = expiryTime;
	}
	public String getSubscriberId() {
		return subscriberId;
	}
	public void setSubscriberId(String subcriberId) {
		this.subscriberId = subcriberId;
	}
	public String getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	public String getServiceClass() {
		return serviceClass;
	}
	public void setServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
	}
	@Override
	public String toString() {
		return "Reference [flightNumber=" + flightNumber + ", ffpNumber="
				+ ffpNumber + ", flightDetails=" 
				+ ", eventType=" + eventType + ", isActive=" + isActive
				+ ", expiryTime=" + expiryTime + ", subscriberId="
				+ subscriberId + ", endPoint=" + endPoint + ", serviceClass="
				+ serviceClass + "]";
	}
	
	
	
}
