package com.tutcugil.core.helper;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Muhammet TUTCUGIL on 23.09.2017.
 * http://www.tutcugil.com
 */

public class HelperKeyboard {
    public static void hide(Context context){
        if (context == null
                || !(context instanceof Activity))
            return;

        // Check if no view has focus:
        View view = ((Activity)context).getCurrentFocus();
        if  (view == null)
            return;

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
