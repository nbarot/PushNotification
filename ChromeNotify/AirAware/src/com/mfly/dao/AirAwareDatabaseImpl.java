package com.mfly.dao;


import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.mfly.services.AirAwareDatabase;
import com.mfly.services.FlightDelayAcknowledgementStatusCode;
import com.mfly.services.SubscriptionStatusCode;
import com.mfly.vo.FlightDelayInformation;
import com.mfly.vo.FlightDetails;
import com.mfly.vo.PassengerContactInformation;
import com.mfly.vo.Reference;

public class AirAwareDatabaseImpl implements AirAwareDatabase {

	@Override
	public FlightDelayAcknowledgementStatusCode insertFlightDelayInformationToDB(
			String flightNumber, Date newDepartureTime) {
		Session session = HibernateSessionManager.getSessionFactory().openSession();
		session.beginTransaction();
		FlightDetails details = (FlightDetails)session.get(FlightDetails.class, new String(flightNumber));
		/*if(details!=null) {
			//Date convertToDate = Date.from(newDepartureTime.atZone(ZoneId.systemDefault()).toInstant());			
			details.set(newDepartureTime);
			if(details.getScheduledDeapatureTime().before(details.getActualDepartureTime())){
				details.setStatus("DELAYED");
			}			
		}
		session.update(details);
		session.flush();
		session.getTransaction().commit();
		System.out.println("Status:"+details.getStatus());
		if(details.getStatus()!=null) {
			switch (details.getStatus()) {
			case "DELAYED":
				return FlightDelayAcknowledgementStatusCode.DLY;
			case "CANCELLED":
				return FlightDelayAcknowledgementStatusCode.CAN;	
			default:
				return FlightDelayAcknowledgementStatusCode.OT;
			}
		}		*/
		return FlightDelayAcknowledgementStatusCode.OT;
	}

	@Override
	public Collection<FlightDelayInformation> getListOfAllDelayedFlights() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<PassengerContactInformation> getListOfPassengersForFlight(
			String flightNumber) {
		Session session = HibernateSessionManager.getSessionFactory().openSession();
		session.beginTransaction();
		Collection<PassengerContactInformation> passengers = session.createQuery("select p from PassengerContactInformation as p, Reference r where r.ffpNumber = p.ffp and r.flightNumber=?").setString(0, flightNumber).list();
		session.getTransaction().commit();
		return passengers;
	}

	@Override
	public SubscriptionStatusCode registerDevice(String ffp, String subcriberId) {
		Session session = HibernateSessionManager.getSessionFactory().openSession();
		session.beginTransaction();
		//get Passanger
		PassengerContactInformation passenger = (PassengerContactInformation)session.get(PassengerContactInformation.class, new String(ffp));
		if(passenger!=null && passenger.getReferences()!=null) {
			for(Reference reference:passenger.getReferences()) {
				System.out.println("Seting the subscriberId:"+subcriberId);
				reference.setSubscriberId(subcriberId);
			}
		}
		session.update(passenger);
		session.flush();
		session.getTransaction().commit();
		return SubscriptionStatusCode.SUCCESS;
	}
	
	@Override
	public PassengerContactInformation getPassengerForFFP(String freqFlyerNo) {
		Session session = HibernateSessionManager.getSessionFactory().openSession();
		session.beginTransaction();
		List<PassengerContactInformation> passenger = session.createQuery("select p from PassengerContactInformation as p, Reference r where r.ffpNumber = p.ffp and r.ffpNumber=?").setString(0, freqFlyerNo).list();
		session.getTransaction().commit();
		if(passenger != null && passenger.size() > 0) {
			return passenger.get(0) ;
		}
		return null ;
	}
	

}
