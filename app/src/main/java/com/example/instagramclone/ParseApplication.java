package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("LqWNNFnHnoGBegS6RoN3kfmU1K1UgbE0oIo75ywB")
                .clientKey("n1xuPxi42ECYu4BrJq3dJnns7KD2m1Ximg2S8cXC")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
