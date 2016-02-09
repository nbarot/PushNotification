package com.mfly.services;


import com.mfly.vo.PassengerContactInformation;

public interface NotificationService {
	
	void sendPushNotification(PassengerContactInformation passengre);

}
