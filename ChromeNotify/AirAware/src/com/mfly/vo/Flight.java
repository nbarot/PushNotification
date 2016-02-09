package com.mfly.vo;

public class Flight {
	
	private boolean isCompleted ;
	
	private boolean isCancelled ;
	
	private String departure_terminal; 
	
	private String departure_gate;
	
	private String arrival_terminal; 
	
	private String arrival_gate;
	
	private String scheduled_departure ;
	
	private String estimated_departure ;

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public boolean isCancelled() {
		return isCancelled;
	}

	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	public String getDeparture_terminal() {
		return departure_terminal;
	}

	public void setDeparture_terminal(String departure_terminal) {
		this.departure_terminal = departure_terminal;
	}

	public String getDeparture_gate() {
		return departure_gate;
	}

	public void setDeparture_gate(String departure_gate) {
		this.departure_gate = departure_gate;
	}

	public String getArrival_terminal() {
		return arrival_terminal;
	}

	public void setArrival_terminal(String arrival_terminal) {
		this.arrival_terminal = arrival_terminal;
	}

	public String getArrival_gate() {
		return arrival_gate;
	}

	public void setArrival_gate(String arrival_gate) {
		this.arrival_gate = arrival_gate;
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

}
