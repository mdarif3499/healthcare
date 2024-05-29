package com.example.healthcare;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseApp.initializeApp(getApplicationContext(), new FirebaseOptions.Builder()
                .setProjectId("health-care-a204b")
                .setApplicationId("1:827584248852:android:e42e1acff211a974a07b01")
                .setApiKey("AIzaSyDT4Q6XWakyZ5igf8GoNX8EQZf7QZA1QPk")
                .setStorageBucket("health-care-a204b.appspot.com")
                // setDatabaseURL(...)
                // setStorageBucket(...)
                .build());
    }
}
