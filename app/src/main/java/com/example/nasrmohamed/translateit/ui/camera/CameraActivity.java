package com.example.nasrmohamed.translateit.ui.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nasrmohamed.translateit.R;
import com.example.nasrmohamed.translateit.utils.LoadingDialogues;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.List;

import static com.example.nasrmohamed.translateit.utils.Constants.REQUEST_IMAGE_CAPTURE;

public class CameraActivity extends AppCompatActivity implements CameraContract.CameraView {

    private ImageView imgToDetect;
    private TextView tvTranslatedText,tvTranslatedBy;
    private CameraPresenter presenter;
    private Bitmap imageBitmap;
    private NiceSpinner languageSpinner;

    private String[] languages;
    private String[] langCodes;

    private LoadingDialogues dialogues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        initializer();
        //Go to camera
        dispatchTakePictureIntent();
    }

    private void initializer() {
        presenter = new CameraPresenter(this,this);
        imgToDetect = findViewById(R.id.img_captured);
        tvTranslatedText = findViewById(R.id.tv_pic_translated_text);
        tvTranslatedBy = findViewById(R.id.tv_translated_by);
        dialogues = new LoadingDialogues(this);

        languageSpinner = findViewById(R.id.spinner_detect_language);
        initSpinner();
    }

    private void initSpinner() {
        languages = new String[] {"Azerbaijan","Albanian","Amharic","English",
                "Arabic","Armenian","Afrikaans","Basque","Bashkir","Belarusian",
                "Bengali","Burmese","Bulgarian","Bosnian",
                "Welsh","Hungarian","Vietnamese","Haitian (Creole)","Galician",
                "Dutch","Greek","Georgian","Danish","Indonesian",
                "Irish","Italian","Spanish","Chinese","Korean",
                "Latin","German","Nepali","Norwegian","Persian","Polish","Portuguese",
                "Romanian","Russian","Turkish","Uzbek","Ukrainian","French",
                "Hindi","Czech","Swedish","Japanese"};

        langCodes = new String[] {"az","sq","am","en","ar",
                "hy","af","eu","ba","be","bn","my","bg",
                "bs","cy","hu","vi","ht","gl",
                "nl","el","ka","da","id","ga","it","es","zh",
                "ko","la","de","ne","no","fa","pl","pt","ro",
                "ru","tr","uz","uk","fr","hi","cs","sv","ja"};

        List<String> lang = Arrays.asList(languages);

        languageSpinner.attachDataSource(lang);
        languageSpinner.setSelectedIndex(0);
    }

    /**
     * Handling the camera Intent
     */
    private void dispatchTakePictureIntent() {

        Log.d("ph_intent","picture intent is called");

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d("act_result","Activity result is called");
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            assert extras != null;
            imageBitmap = (Bitmap) extras.get("data");
            imgToDetect.setImageBitmap(imageBitmap);
            Log.d("result","image is returned");
        }
    }
    /************************************/


    /**
     * Start Translating Text
     * @param view
     */
    public void btnPicTranslate(View view) {
        int index = languageSpinner.getSelectedIndex();
        presenter.translateTextPhoto(imageBitmap,langCodes[index]);
    }

    @Override
    public void showTransatedText(String text) {
        tvTranslatedText.setText(text);
        tvTranslatedText.setVisibility(View.VISIBLE);
        tvTranslatedBy.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String title, String body, boolean isCancel) {
        dialogues.showErrorDialogue(title,body,isCancel);
    }
}
