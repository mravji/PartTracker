package org.sheridan.scrapyard.messaging


import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class MyFirebaseInstanceIdService : FirebaseInstanceIdService() {

    val TAG = "PushNotifService"
    lateinit var name: String

    override fun onTokenRefresh() {
        // Allows our app to receive notifications. DO NOT CHANGE THIS!! IS MANDATORY FOR FCM TO WORK!!
        val token = FirebaseInstanceId.getInstance().token
        Log.d(TAG, "Refreshed Token: $token")
    }

}