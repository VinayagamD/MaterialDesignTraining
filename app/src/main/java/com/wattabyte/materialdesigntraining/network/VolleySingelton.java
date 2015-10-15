package com.wattabyte.materialdesigntraining.network;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.wattabyte.materialdesigntraining.MyApplication;

/**
 * Created by Vinayagam on 10/15/15.
 */
public class VolleySingelton {
    private static VolleySingelton instance = null;

    private RequestQueue mRequestQueue;
    private ImageLoader imageLoader;

    public static VolleySingelton getInstance() {

        if (instance == null)
            instance = new VolleySingelton();
        return instance;
    }

    private VolleySingelton() {
        mRequestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
        imageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {

            private LruCache<String , Bitmap > cache = new LruCache<>((int) ((Runtime.getRuntime().maxMemory()/1024)/8));
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url,bitmap);
            }
        });
    }

    public RequestQueue getRequestQueue(){
        return  mRequestQueue;
    }

    public ImageLoader getImageLoader(){
        return imageLoader;
    }
}
