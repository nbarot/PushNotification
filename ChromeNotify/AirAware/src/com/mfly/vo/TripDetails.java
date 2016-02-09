package com.mfly.vo;

import java.util.ArrayList;
import java.util.List;

public class TripDetails {
	
	private int id ;
	private String name ;
	private String destination_country ;
	private String destination ;
	private String scheduled_departure ;
	private String estimated_departure ;
	private String departure_status ;
	private String iata_code ;
	private List<TripItems> items = new ArrayList<TripItems>() ;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDestination_country() {
		return destination_country;
	}
	public void setDestination_country(String destination_country) {
		this.destination_country = destination_country;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getScheduled_departure() {
		return scheduled_departure;
	}
	public void setScheduled_departure(String scheduled_departure) {
		this.scheduled_departure = scheduled_departure;
	}
	public String getEstimated_departure() {
		return estimated_departure;
	}
	public void setEstimated_departure(String estimated_departure) {
		this.estimated_departure = estimated_departure;
	}
	public String getDeparture_status() {
		return departure_status;
	}
	public void setDeparture_status(String departure_status) {
		this.departure_status = departure_status;
	}
	public String getIata_code() {
		return iata_code;
	}
	public void setIata_code(String iata_code) {
		this.iata_code = iata_code;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<TripItems> getItems() {
		return items;
	}
	public void setItems(List<TripItems> items) {
		this.items = items;
	}	
	
}
