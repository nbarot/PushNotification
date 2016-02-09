package com.mfly.services;

/**
 * Created by Shirishkumar.Shetty on 21/06/2015.
 */
public interface SubscriptionService {

    SubscriptionStatusCode subscriberUserForPushNotifications(String frequentFlyerNumber, String deviceID);

}
