package com.sharkBytesLab.DChat.FirebaseServices;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessagingService extends FirebaseMessagingService
{
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.v("FCM", "Token: " + token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.v("FCM", "Message: " + remoteMessage.getNotification().getBody());
    }
}
