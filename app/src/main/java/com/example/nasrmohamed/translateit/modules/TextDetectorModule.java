package com.example.nasrmohamed.translateit.modules;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.SparseArray;

import com.example.nasrmohamed.translateit.utils.Constants;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import static android.app.Activity.RESULT_OK;
import static android.support.v4.app.ActivityCompat.startActivityForResult;

public class TextDetectorModule {

    //ToDo write down the text detection algorithm

    private Context context;
    private TextRecognizer textRecognizer;
    private String detectedText;

    public TextDetectorModule(Context context) {
        this.context = context;

        initialize();
    }

    /**
     * Initializes the variables
     */
    private void initialize() {
        textRecognizer = new TextRecognizer.Builder(context).build();
    }

    public String detectText(Bitmap image){

        return startDetecting(image);
    }

    private String startDetecting(Bitmap image){
        StringBuilder stringBuilder = new StringBuilder();
        if(!textRecognizer.isOperational()){
            Log.e("text_det_error","Detector Dependencies are not available yet");
        }
        else {
            Frame frame = new Frame.Builder().setBitmap(image).build();
            SparseArray<TextBlock> items = textRecognizer.detect(frame);


            for (int i=0;i<items.size();++i){
                TextBlock item = items.valueAt(i);
                stringBuilder.append(item.getValue());
                stringBuilder.append("\n");
            }

        }

        return stringBuilder.toString();
    }


}

