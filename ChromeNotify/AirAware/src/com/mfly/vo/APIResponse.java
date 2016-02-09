package com.mfly.vo;

import java.util.ArrayList;
import java.util.List;

public class APIResponse {
	
	List<FlightDetails> docs = new ArrayList<FlightDetails>() ;

	public List<FlightDetails> getFlightList() {
		return docs;
	}

	public void setFlightList(List<FlightDetails> docs) {
		this.docs = docs;
	}	

}
