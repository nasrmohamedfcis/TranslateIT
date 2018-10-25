package com.example.nasrmohamed.translateit.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.nasrmohamed.translateit.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidationHelper {


    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean isMobileNumber(String mobileNumber){

        if(mobileNumber.startsWith("0")||mobileNumber.startsWith("1")||mobileNumber.startsWith("2")
                ||mobileNumber.startsWith("3") ||mobileNumber.startsWith("4")||mobileNumber.startsWith("5")
                ||mobileNumber.startsWith("6") ||mobileNumber.startsWith("7")||mobileNumber.startsWith("8")
                ||mobileNumber.startsWith("9") ||mobileNumber.contains("+")){
            return true;
        }
        else
            return false;
    }

    public static boolean isEmailValid(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    /**
     * @param str
     * @return 0 for less than 6 characters <P>
     * 1 for more than 20 characters <P>
     * 2 for meet
     */
    public static boolean meetLengthValidation(String str) {
        final int length = str.length();
        if (length >= 6 && length <= 20)
            return true;
        return false;
    }

    public static boolean isMobileNumberValid(String mobileNumber) {

        if (mobileNumber.length() < 7) {
            return false;
        }
        else if(mobileNumber.length() > 11){
            return false;
        }
        else
            return true;
    }

    public static boolean checkEditText(EditText editText, Context context) {
        String temp = editText.getText().toString().trim();
        boolean result = false;

        if (!TextUtils.isEmpty(temp)) {
            result = true;
        } else {
            editText.setError(context.getString(R.string.this_field_is_required));
            editText.requestFocus();
            result = false;
        }
        return result;
    }

    public static void clearEditText(EditText editText)
    {
        editText.setText("");
    }


    public static String removeFirstCharacter(String number){
        return number.substring(1);
    }

    public static boolean haveNetworkConnection(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

}
