package com.example.nasrmohamed.translateit.ui.main;

public class MainContract {

    interface mainView{

        void showTranslatedText(String text);
        void showNoInternetConnection();
        void showLoading();
        void dismissLoading();
    }

    interface  mainPresenter {

        void translateText(String text,String source,String target);
        void detectText(String text,String target);
    }
}
