package com.example.nasrmohamed.translateit.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.nasrmohamed.translateit.R;


public class IntentManager {

    public static void startNewActivity(Activity host, Class<? extends AppCompatActivity> target, Bundle bundle) {
        Intent intent = new Intent(host, target);
        if (bundle != null) {
            intent.putExtra(Constants.EXTRA_BUNDLE, bundle);
        }
        host.startActivity(intent);
        host.overridePendingTransition(R.anim.enter_activity, R.anim.exit_activity);
    }

    public static void exitToNewActivity(Activity host, Class<? extends AppCompatActivity> target, Bundle bundle) {
        Intent intent = new Intent(host, target);
        host.startActivity(intent);
        host.overridePendingTransition(R.anim.rotate_right_to_left, R.anim.exit_activity);
        host.finish();
    }

}
