package com.mfly.vo;

import java.io.Serializable;
import java.util.Date;

public class FlightDetails implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8802664276260476729L;
	
	private String[] FLIGHT_NUMBER;
	private String[] FLEET_NUMBER;
	private String[] ORIGIN;
	private String[] DESTINATION;
	private String[] FLIGHT_STATUS;
	private String[] CARRIER_CD;
	private Date toDate;
	private String[] SCH_DEP_TIME;
	private String[] EST_DEP_TIME;
	private String[] OLD_GATE_NUMBER;
	private String[] NEW_GATE_NUMBER;
	private Double[] OLD_FARE;
	private Double[] NEW_FARE;
	private String[] LAST_UPDATED_DATE;
	public String[] getFLIGHT_NUMBER() {
		return FLIGHT_NUMBER;
	}
	public void setFLIGHT_NUMBER(String[] fLIGHT_NUMBER) {
		FLIGHT_NUMBER = fLIGHT_NUMBER;
	}
	public String[] getFLEET_NUMBER() {
		return FLEET_NUMBER;
	}
	public void setFLEET_NUMBER(String[] fLEET_NUMBER) {
		FLEET_NUMBER = fLEET_NUMBER;
	}
	public String[] getORIGIN() {
		return ORIGIN;
	}
	public void setORIGIN(String[] oRIGIN) {
		ORIGIN = oRIGIN;
	}
	public String[] getDESTINATION() {
		return DESTINATION;
	}
	public void setDESTINATION(String[] dESTINATION) {
		DESTINATION = dESTINATION;
	}
	public String[] getFLIGHT_STATUS() {
		return FLIGHT_STATUS;
	}
	public void setFLIGHT_STATUS(String[] fLIGHT_STATUS) {
		FLIGHT_STATUS = fLIGHT_STATUS;
	}
	public String[] getCARRIER_CD() {
		return CARRIER_CD;
	}
	public void setCARRIER_CD(String[] cARRIER_CD) {
		CARRIER_CD = cARRIER_CD;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	public String[] getOLD_GATE_NUMBER() {
		return OLD_GATE_NUMBER;
	}
	public void setOLD_GATE_NUMBER(String[] oLD_GATE_NUMBER) {
		OLD_GATE_NUMBER = oLD_GATE_NUMBER;
	}
	public String[] getNEW_GATE_NUMBER() {
		return NEW_GATE_NUMBER;
	}
	public void setNEW_GATE_NUMBER(String[] nEW_GATE_NUMBER) {
		NEW_GATE_NUMBER = nEW_GATE_NUMBER;
	}
	public Double[] getOLD_FARE() {
		return OLD_FARE;
	}
	public void setOLD_FARE(Double[] oLD_FARE) {
		OLD_FARE = oLD_FARE;
	}
	public Double[] getNEW_FARE() {
		return NEW_FARE;
	}
	public void setNEW_FARE(Double[] nEW_FARE) {
		NEW_FARE = nEW_FARE;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String[] getSCH_DEP_TIME() {
		return SCH_DEP_TIME;
	}
	public void setSCH_DEP_TIME(String[] sCH_DEP_TIME) {
		SCH_DEP_TIME = sCH_DEP_TIME;
	}
	public String[] getEST_DEP_TIME() {
		return EST_DEP_TIME;
	}
	public void setEST_DEP_TIME(String[] eST_DEP_TIME) {
		EST_DEP_TIME = eST_DEP_TIME;
	}
	public String[] getLAST_UPDATED_DATE() {
		return LAST_UPDATED_DATE;
	}
	public void setLAST_UPDATED_DATE(String[] lAST_UPDATED_DATE) {
		LAST_UPDATED_DATE = lAST_UPDATED_DATE;
	}
		
	
}
