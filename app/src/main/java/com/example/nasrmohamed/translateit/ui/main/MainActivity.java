package com.example.nasrmohamed.translateit.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nasrmohamed.translateit.R;
import com.example.nasrmohamed.translateit.ui.camera.CameraActivity;
import com.example.nasrmohamed.translateit.utils.IntentManager;
import com.example.nasrmohamed.translateit.utils.LoadingDialogues;
import com.example.nasrmohamed.translateit.utils.ValidationHelper;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.mainView {

    //presenter
    private MainPresenter presenter;
    //views
    private EditText etTextToTranslate;
    private TextView tvTextTranslated,tvTranslatedBy;
    private NiceSpinner fromLanguageSpinner, toLanguageSpinner;

    private LoadingDialogues dialogues;

    private String[] languages;
    private String[] langCodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializer();
    }

    /**
     * Initializes the variables
     */
    private void initializer() {
        presenter = new MainPresenter(this,this);

        etTextToTranslate = findViewById(R.id.et_text_to_translate);
        tvTextTranslated = findViewById(R.id.tv_translated_text);
        tvTranslatedBy = findViewById(R.id.tv_translated_by);

        dialogues = new LoadingDialogues(this);


        initSpinners();


    }

    /**
     * initializes the spinners values
     */
    private void initSpinners() {

        //ToDo initialize the language spinners

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

        fromLanguageSpinner = findViewById(R.id.spinner_from_language);
        toLanguageSpinner = findViewById(R.id.spinner_to_language);

        List<String> lang = Arrays.asList(languages);


        fromLanguageSpinner.attachDataSource(lang);
        toLanguageSpinner.attachDataSource(lang);

        fromLanguageSpinner.setSelectedIndex(0);
        toLanguageSpinner.setSelectedIndex(0);
    }

    // related to contract
    @Override
    public void showTranslatedText(String text) {
        tvTextTranslated.setText(text) ;
        tvTextTranslated.setVisibility(View.VISIBLE);
        tvTranslatedBy.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoInternetConnection() {
        //ToDo show no internet dialogue
        dialogues.showErrorDialogue(getString(R.string.no_internet),getString(R.string.no_internet_message),false);
    }

    @Override
    public void showLoading() {
        //ToDo implement the loading dialogue
        dialogues.showLoadingDialogue(getString(R.string.loading),false);
    }

    @Override
    public void dismissLoading() {
        dialogues.removeLoadingDialogue();
    }
    //*********//

    /**
     * Translate Text Button
     * @param view
     */
    public void btnTranslate(View view) {
        int fromIndex = fromLanguageSpinner.getSelectedIndex();
        int toIndex = toLanguageSpinner.getSelectedIndex();

        if(ValidationHelper.checkEditText(etTextToTranslate,MainActivity.this));{
           presenter.translateText(etTextToTranslate.getText().toString(),
                    langCodes[fromIndex],langCodes[toIndex]);
        }
    }


    /**
     * Detect Text Button
     * @param view
     */
    public void btnDetect(View view) {
        int fromIndex = fromLanguageSpinner.getSelectedIndex();
        int toIndex = toLanguageSpinner.getSelectedIndex();

        if(ValidationHelper.checkEditText(etTextToTranslate,MainActivity.this)==true);{
            presenter.detectText(etTextToTranslate.getText().toString(),
                    langCodes[toIndex]);
        }
    }

    /**
     * Shows the history layout
     * @param view
     */
    public void showHistory(View view) {
        //ToDo add the history layout and its list view
    }

    /**
     * starts the camera activity
     * @param view
     */
    public void btnDetectPhoto(View view) {
        IntentManager.startNewActivity(this,CameraActivity.class,null);
    }
    // last one of them

}
