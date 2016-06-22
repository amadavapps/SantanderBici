package com.santanderbici.amadav.santanderbici.lib;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by dgago on 14/06/2016.
 */
public class GoogleVolley {
    private static GoogleVolley myGoogleVolley = null;
    private RequestQueue myRequestQueue;

    private GoogleVolley(Context context) {
        myRequestQueue = Volley.newRequestQueue(context);
    }

    public static GoogleVolley getInstance(Context context) {
        if (myGoogleVolley == null) {
            myGoogleVolley = new GoogleVolley(context);
        }
        return myGoogleVolley;
    }

    public RequestQueue getRequestQueue() {
        return myRequestQueue;
    }
}
