package com.example.keisuke.myapplication;

import android.app.Application;
//import android.content.res.Configuration;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by keisuke on 2015/03/22.
 */
public class AppController extends Application{

    public static final String TAG = AppController.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private static AppController mInstance;

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

    public static synchronized AppController getInstance(){//synchronizedで排他制御
        return mInstance;
    }

    public ImageLoader getImageLoader() {
        return null;
    }
}


