package com.wattabyte.materialdesigntraining;

import android.app.Application;

/**
 * Created by Vinayagam on 10/15/15.
 */
public class MyApplication extends Application {

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static MyApplication getInstance(){
        return  mInstance;
    }
}
