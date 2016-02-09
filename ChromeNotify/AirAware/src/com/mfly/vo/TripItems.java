package com.mfly.vo;

public class TripItems {
	
	private int id ;
	
	private String type ;
	
	private AirReservation air_reservation ;
	
	private Flight flight ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public AirReservation getAir_reservation() {
		return air_reservation;
	}

	public void setAir_reservation(AirReservation air_reservation) {
		this.air_reservation = air_reservation;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	

}
