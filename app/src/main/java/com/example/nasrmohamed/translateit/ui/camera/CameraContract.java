package com.example.nasrmohamed.translateit.ui.camera;

import android.graphics.Bitmap;

public class CameraContract {

    interface CameraView {
        void showTransatedText(String text);
        void showError(String title,String body, boolean isCancel);
    }

    interface CameraPresenter{
        void translateTextPhoto(Bitmap photo,String target);
    }

}
