package com.geoffledak.winterize.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Geoff Ledak on 7/6/2017.
 */

public class VisualUtils {

    private static VisualUtils mInstance;
    private ProgressDialog mDialog;

    public VisualUtils() { }


    public static VisualUtils getInstance() {

        if( mInstance == null )
            mInstance = new VisualUtils();

        return mInstance;

    }

    public void showLoadingDialog(Context context) {

            if (mDialog == null)
                mDialog = new ProgressDialog(context);

            mDialog.setMessage("Please wait");
            mDialog.setCancelable(false);
            mDialog.show();
    }


    public void dismissLoadingDialog() {

            if (mDialog != null)
                mDialog.dismiss();
    }


    public void destroyLoadingDialog() {
        mDialog = null;
    }
}
