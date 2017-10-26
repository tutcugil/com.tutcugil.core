package com.tutcugil.core.helper;

import android.graphics.Color;

/**
 * Created by Muhammet TUTCUGIL on 23.09.2017.
 * http://www.tutcugil.com
 */

public class HelperColor {
    public static int shade(String color, double fraction) {
        color = color.replace("#", "");

        int red   = (int) Math.round(Math.max(0, Integer.parseInt(color.substring(0, 2), 16) - 255 * fraction));
        int green = (int) Math.round(Math.max(0, Integer.parseInt(color.substring(2, 4), 16) - 255 * fraction));
        int blue  = (int) Math.round(Math.max(0, Integer.parseInt(color.substring(4, 6), 16) - 255 * fraction));

        return Color.rgb(red, green, blue);
    }

    public static int hexToARGB(String color, double opacity){
        color = color.replace("#", "");

        int red   = Integer.parseInt(color.substring(0, 2), 16);
        int green = Integer.parseInt(color.substring(2, 4), 16);
        int blue  = Integer.parseInt(color.substring(4, 6), 16);

        int alpha = (int) (opacity * 255);

        return Color.argb(alpha, red, green, blue);
    }
}
