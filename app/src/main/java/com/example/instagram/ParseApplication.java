package com.example.instagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("29HbYtYugIikRqfySdK7QiLqFjLvGZrATGUhpqBG")
                .clientKey("isjncuvXTrX2TeltmXmdUx6KpOPFWUd1vskcBx0k")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}