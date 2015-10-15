package com.wattabyte.materialdesigntraining.network;

import com.android.volley.RequestQueue;

/**
 * Created by Vinayagam on 10/15/15.
 */
public class VolleySingelton {
    private static VolleySingelton instance = null;

    private RequestQueue mRequestQueue;

    public static VolleySingelton getInstance() {

        if (instance == null)
            instance = new VolleySingelton();
        return instance;
    }

    private VolleySingelton() {

    }
}
