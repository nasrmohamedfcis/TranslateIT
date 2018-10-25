package com.example.nasrmohamed.translateit.ui.intro;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nasrmohamed.translateit.R;
import com.example.nasrmohamed.translateit.ui.main.MainActivity;
import com.example.nasrmohamed.translateit.user.User;
import com.example.nasrmohamed.translateit.utils.Constants;
import com.example.nasrmohamed.translateit.utils.IntentManager;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class IntroActivity extends AppIntro {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = new User(this);

        String font= Constants.CHANGA_FONT;
        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        addSlide(AppIntroFragment.newInstance(getString(R.string.welcome),font, getString(R.string.welcome_info),font, R.drawable.translate_logo , Color.parseColor("#4CAF50")));//green
        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_first_header),font, getString(R.string.intro_first),font, R.drawable.translate_intro  , Color.parseColor("#FF9800"))); //yellow
        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_second_header), font,getString(R.string.intro_second),font, R.drawable.scan_text_intro , Color.parseColor("#0097A7"))); //blue

        setFadeAnimation();

        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor( Color.parseColor("#FFA000"));
        setSeparatorColor(Color.parseColor("#BDBDBD"));

        // Hide Skip/Done button.
        showSkipButton(true);
        setProgressButtonEnabled(true);

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
        setVibrate(false);
        setVibrateIntensity(30);

        setSkipTextTypeface(font);
        setDoneTextTypeface(font);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
        user.setIntroSeen(true);
        IntentManager.startNewActivity(IntroActivity.this, MainActivity.class,null);
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.
        user.setIntroSeen(true);
        IntentManager.startNewActivity(IntroActivity.this, MainActivity.class,null);
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
}
