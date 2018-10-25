package com.example.nasrmohamed.translateit.networking;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nasrmohamed.translateit.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ApiManger {

    private Context context;
    private String detectedLanguage;

    public ApiManger(Context context){
        this.context = context;
    }

    public String TranslateText(String text, String source, String target){

        return null;
    }



}
