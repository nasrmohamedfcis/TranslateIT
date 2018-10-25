package com.example.nasrmohamed.translateit.user;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.nasrmohamed.translateit.utils.Constants;

import java.util.Locale;

public class User {

    //for user data
    private SharedPreferences userData;
    private SharedPreferences.Editor editor;

    //Activity Context
    private Context context;

    private Boolean introSeen;
    private String appLanguage;

    public User(Context context) {
        this.context = context;
        initSharedPref();
    }

    /**
     * Initiat the shared prefrence
     */
    private void initSharedPref() {
        userData = context.getSharedPreferences(Constants.SHARED_PREFRENCE_NAME,Context.MODE_PRIVATE);
        editor = userData.edit();
    }

    public Boolean getIntroSeen() {
        return userData.getBoolean(Constants.KEY_FIRST_LOOK,false);
    }

    public void setIntroSeen(Boolean introSeen) {
        this.introSeen = introSeen;
        editor.putBoolean(Constants.KEY_FIRST_LOOK,true);
        editor.apply();
    }

    public String getAppLanguage() {
        return userData.getString(Constants.KEY_APP_LANGUAGE,getLocalLanguage());
    }

    public void setAppLanguage(String appLanguage) {
        this.appLanguage = appLanguage;
        editor.putString(Constants.KEY_APP_LANGUAGE,appLanguage);
        editor.apply();
    }


    /**
     * returns the device language
     * @return
     */
    private String getLocalLanguage(){
        return Locale.getDefault().getLanguage();
    }
}
