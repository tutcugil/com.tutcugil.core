package com.tutcugil.core.helper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;

/**
 * Created by Muhammet TUTCUGIL on 23.09.2017.
 * http://www.tutcugil.com
 */

public class HelperCompat {
    public static void setBackground(View view, Drawable drawable){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
            return;
        }

        view.setBackgroundDrawable(drawable);
    }

    public static int getColor(Context context, int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(color);
        }

        return context.getResources().getColor(color);
    }

    public static Spanned fromHtml(String html){
        if (TextUtils.isEmpty(html))
            html = "";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);

        return Html.fromHtml(html);
    }
}
