package com.mfly.vo;

import java.io.Serializable;
import java.util.Set;

public class PassengerContactInformation implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2109966579966182929L;
	
	private String passengerFirstName;
    private String passengerLastName;
    private String passengerEmailAddress;
    private String deviceID;
    
    private String pnr;
    private String ffp;
    private String phone;
    private String email;
    
    private Set<Reference> references;

    public PassengerContactInformation() {
    }

    public String getPassengerFirstName() {
        return passengerFirstName;
    }

    public void setPassengerFirstName(String passengerFirstName) {
        this.passengerFirstName = passengerFirstName;
    }

    public String getPassengerLastName() {
        return passengerLastName;
    }

    public void setPassengerLastName(String passengerLastName) {
        this.passengerLastName = passengerLastName;
    }

    public String getPassengerEmailAddress() {
        return passengerEmailAddress;
    }

    public void setPassengerEmailAddress(String passengerEmailAddress) {
        this.passengerEmailAddress = passengerEmailAddress;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public String getFfp() {
		return ffp;
	}

	public void setFfp(String ffp) {
		this.ffp = ffp;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Reference> getReferences() {
		return references;
	}

	public void setReferences(Set<Reference> references) {
		this.references = references;
	}

	@Override
	public String toString() {
		return "PassengerContactInformation [passengerFirstName="
				+ passengerFirstName + ", passengerLastName="
				+ passengerLastName + ", passengerEmailAddress="
				+ passengerEmailAddress + ", deviceID=" + deviceID + ", pnr="
				+ pnr + ", ffp=" + ffp + ", phone=" + phone + ", email="
				+ email + ", references=" + (references!=null?references:"[]") + "]";
	}

	
}