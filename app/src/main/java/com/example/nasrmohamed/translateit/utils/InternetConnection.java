package com.example.nasrmohamed.translateit.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Mahmoud on 1/18/2018.
 */

public class InternetConnection {


    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
