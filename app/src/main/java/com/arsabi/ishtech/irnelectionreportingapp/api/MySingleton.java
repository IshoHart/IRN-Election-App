package com.arsabi.ishtech.irnelectionreportingapp.api;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Isho on 2/10/2018.
 */

public class MySingleton {

    private  static MySingleton mInstance;
    private static RequestQueue requestQueue;
    private Context context;

    public MySingleton(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }

        return requestQueue;
    }

    public static synchronized MySingleton getInstance(Context context){
        if (mInstance == null){
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }

    public <T>void addToRequestQueue(Request<T> request){
        requestQueue.add(request);
    }
}
