package com.example.keisuke.myapplication;

import android.app.Application;
import android.content.res.Configuration;

/**
 * Created by keisuke on 2015/03/22.
 */
public class AppController extends Application{
    @Override
    public void onCreate(){
        super.onCreate();
        Configuration.Builder builder = new Configuration.Builder(getBaseContext());
        builder.setCacheSize(1024*1024*4);
        builder.setDatabaseName("sqlite.db");
        builder.setDatabaseVersion(1);
        ActiveAndroid.initialize(builder.create(), true);

        mInstance = this;
    }

    @Override
    public void onTerminate(){
        super.onTerminate();
        ActiveAndroid.dispose();
    }
}


