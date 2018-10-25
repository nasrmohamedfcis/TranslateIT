package com.example.nasrmohamed.translateit.ui.camera;

import android.content.Context;
import android.graphics.Bitmap;
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
import com.example.nasrmohamed.translateit.R;
import com.example.nasrmohamed.translateit.modules.TextDetectorModule;
import com.example.nasrmohamed.translateit.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CameraPresenter implements CameraContract.CameraPresenter {

    private Context context;
    private CameraContract.CameraView cameraView;
    private TextDetectorModule detectorModule;

    public CameraPresenter(Context context, CameraContract.CameraView cameraView) {
        this.context = context;
        this.cameraView = cameraView;
        initialize();
    }

    private void initialize() {
        detectorModule = new TextDetectorModule(context);
    }

    @Override
    public void translateTextPhoto(Bitmap photo,String target) {
        String text = detectorModule.detectText(photo);
        if(text.isEmpty()){
            cameraView.showError(context.getString(R.string.detect_error_title),context.getString(R.string.detect_error_body),true);
        }
        DetectText(text,target);
    }

    /**
     * Api Method
     */
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
                                cameraView.showTransatedText(text.getString(i));
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
