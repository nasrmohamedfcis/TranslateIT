package com.example.nasrmohamed.translateit.ui.splash;


import com.daimajia.androidanimations.library.Techniques;
import com.example.nasrmohamed.translateit.R;
import com.example.nasrmohamed.translateit.TestActivity;
import com.example.nasrmohamed.translateit.ui.intro.IntroActivity;
import com.example.nasrmohamed.translateit.ui.main.MainActivity;
import com.example.nasrmohamed.translateit.user.User;
import com.example.nasrmohamed.translateit.utils.Constants;
import com.example.nasrmohamed.translateit.utils.IntentManager;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class SplashActivity extends AwesomeSplash {

    @Override
    public void initSplash(ConfigSplash configSplash) {
        //Customize Circular Reveal
        configSplash.setBackgroundColor(R.color.colorPrimary); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(2000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_LEFT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_TOP); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.drawable.translate_logo); //or any other drawable
        configSplash.setAnimLogoSplashDuration(1500); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.FadeIn); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)


        //Customize Path
        //configSplash.setPathSplash(""); //set path String
        configSplash.setOriginalHeight(400); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(400); //in relation to your svg (path) resource
        configSplash.setAnimPathStrokeDrawingDuration(3000);
        configSplash.setPathSplashStrokeSize(3); //I advise value be <5
        configSplash.setPathSplashStrokeColor(R.color.colorAccent); //any color you want form colors.xml
        configSplash.setAnimPathFillingDuration(2000);
        configSplash.setPathSplashFillColor(R.color.colorAccent); //path object filling color


        //Customize Title
        configSplash.setTitleSplash(getString(R.string.app_name));
        configSplash.setTitleTextColor(R.color.text);
        configSplash.setTitleTextSize(30f); //float value
        configSplash.setAnimTitleDuration(2000);
        configSplash.setAnimTitleTechnique(Techniques.BounceInUp);
        configSplash.setTitleFont(Constants.CHANGA_FONT); //provide string to your font located in assets/fonts/

    }

    @Override
    public void animationsFinished() {
        User user = new User(this);

        if(user.getIntroSeen()){
            toHome();
        }
        else{
            toIntro();
        }

    }

    private void toHome(){
        IntentManager.startNewActivity(this, MainActivity.class,null);
        finish();
    }

    private void toIntro(){
        IntentManager.startNewActivity(this, IntroActivity.class,null);
        finish();
    }
}
