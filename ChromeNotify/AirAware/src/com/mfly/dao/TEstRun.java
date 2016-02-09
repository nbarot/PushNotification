package com.mfly.dao;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.mfly.services.FlightDelayAcknowledgementStatusCode;
import com.mfly.vo.FlightDetails;
import com.mfly.vo.PassengerContactInformation;
import com.mfly.vo.Reference;

public class TEstRun {

	public static void main2(String[] args) {
		try{
			AirAwareDatabaseImpl impl = new AirAwareDatabaseImpl();
			Collection<PassengerContactInformation> c = impl.getListOfPassengersForFlight("721");
			System.out.println(c);
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	public static void main3(String[] args) {
		try {
			AirAwareDatabaseImpl impl = new AirAwareDatabaseImpl();
			String flightNumber = "721";
			//
			Date newDepartureTime = null;//LocalDateTime newDepartureTime = LocalDateTime.now().plusDays(9);			
			FlightDelayAcknowledgementStatusCode status = impl.insertFlightDelayInformationToDB(flightNumber, newDepartureTime);
			System.out.println("Status is :"+status);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			AirAwareDatabaseImpl impl = new AirAwareDatabaseImpl();
			String ffpNumber = "2222333";
			String subscriberId = "temp";			
			impl.registerDevice(ffpNumber, subscriberId);
			System.out.println("Registration done..");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main1(String[] args) {
		try {
			Session session = HibernateSessionManager.getSessionFactory()
					.openSession();

			session.beginTransaction();

			
			 List<PassengerContactInformation> list =
			  session.createCriteria(PassengerContactInformation.class).list();
			  System.out.println(list);
			  
			  System.out.println("-------------------------");
			 

			List<Reference> refs = session.createCriteria(Reference.class)
					.list();
			System.out.println(refs);

			System.out.println("-------------------------");

			/*
			 * List<FlightDetails> flights =
			 * session.createCriteria(FlightDetails.class).list();
			 * System.out.println(flights);
			 */

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

}
