package com.mfly.services;

import com.mfly.dao.AirAwareDatabaseImpl;

/**
 * Created by Shirishkumar.Shetty on 21/06/2015.
 */

public class SubscriptionServiceImpl implements SubscriptionService{
    private AirAwareDatabase airAwareDatabase;

    public SubscriptionServiceImpl() {
        this.airAwareDatabase = new AirAwareDatabaseImpl();
    }

    public SubscriptionStatusCode subscriberUserForPushNotifications(String frequentFlyerNumber, String deviceID){

        return this.airAwareDatabase.registerDevice(frequentFlyerNumber,deviceID);
    }

}
