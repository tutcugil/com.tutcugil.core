package com.tutcugil.core.helper;

import android.text.TextUtils;

/**
 * Created by Muhammet TUTCUGIL on 23.09.2017.
 * http://www.tutcugil.com
 */

public class HelperString {
    public static String crop(String text, int limit){
        if (TextUtils.isEmpty(text))
            return "";

        if (text.length() > limit)
            return text.substring(0, limit) + "...";

        return text;
    }
}
