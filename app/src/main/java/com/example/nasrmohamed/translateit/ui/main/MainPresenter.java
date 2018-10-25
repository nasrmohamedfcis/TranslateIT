package com.example.nasrmohamed.translateit.ui.main;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nasrmohamed.translateit.networking.ApiManger;
import com.example.nasrmohamed.translateit.utils.Constants;
import com.example.nasrmohamed.translateit.utils.ValidationHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainPresenter implements MainContract.mainPresenter {

    private Context context;
    private MainContract.mainView mainView;
    private ApiManger apiManger;

    public MainPresenter(Context context, MainContract.mainView mainView) {
        this.context = context;
        this.mainView = mainView;
        apiManger = new ApiManger(context);
    }

    @Override
    public void translateText(String text,String source, String target) {
        if(ValidationHelper.haveNetworkConnection(context)){
            TranslateText(text,source,target);
        }
        else {
            mainView.showNoInternetConnection();
        }
    }


    @Override
    public void detectText(String text,String target) {
        if(ValidationHelper.haveNetworkConnection(context)){
            DetectText(text,target);
        }
        else {
            mainView.showNoInternetConnection();
        }
    }


    /**
     *
     * Api Methods
     *
     */

    public void TranslateText(final String text, String source, String target){

        String finalUrl= Constants.BASE_URL+Constants.TRANSLATE_ENDPOINT;
        RequestQueue queue = Volley.newRequestQueue(context);
        final String lang = source+"-"+target;

        StringRequest postRequest = new StringRequest(Request.Method.POST, finalUrl,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("trans.response",response);
                        try {
                            JSONObject all = new JSONObject(response);
                            JSONArray text = all.getJSONArray("text");
                            for(int i=0;i<text.length();i++){
                                Log.d("text",text.getString(i));
                                mainView.showTranslatedText(text.getString(i));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("trans_parse","parsing error");
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("trans.response",error.toString());
                    }
                }
        ){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> pars = new HashMap<String, String>();
                pars.put("Content-Type", "application/x-www-form-urlencoded");
                return pars;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> pars = new HashMap<String, String>();
                pars.put("key",Constants.API_KEY);
                pars.put("text",text);
                pars.put("lang",lang);
                return pars;
            }
        };


        // handles the slow connection, i.e connection timeout //
        int socketTimeout = 30000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(
                socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        postRequest.setRetryPolicy(policy);
        queue.add(postRequest);

    }

    /**
     * used for detecting and translating
     * @param text
     * @param target
     */
    public void DetectText(final String text, final String target){

        String finalUrl= Constants.BASE_URL+Constants.TRANSLATE_ENDPOINT;
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest postRequest = new StringRequest(Request.Method.POST, finalUrl,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("det.response",response);
                        try {
                            JSONObject all = new JSONObject(response);

                            JSONArray text = all.getJSONArray("text");

                            for(int i=0;i<text.length();i++){
                                Log.d("text",text.getString(i));
                                mainView.showTranslatedText(text.getString(i));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("det_parse","detectionParse errror");
                        }


                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("det.response",error.toString());
                    }
                }
        ){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> pars = new HashMap<String, String>();
                pars.put("Content-Type", "application/x-www-form-urlencoded");
                return pars;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> pars = new HashMap<String, String>();
                pars.put("key",Constants.API_KEY);
                pars.put("text",text);
                pars.put("lang",target);
                return pars;
            }
        };


        // handles the slow connection, i.e connection timeout //
        int socketTimeout = 30000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(
                socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        postRequest.setRetryPolicy(policy);
        queue.add(postRequest);

    }
}
