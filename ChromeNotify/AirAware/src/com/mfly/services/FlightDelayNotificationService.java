package com.mfly.services;

import com.mfly.vo.FlightDelayNotification;

import java.util.List;

/**
 * Created by Shirishkumar.Shetty on 18/06/2015.
 */
public interface FlightDelayNotificationService {

    List<FlightDelayNotification> retrieveFlightDelayNotifications();

}
