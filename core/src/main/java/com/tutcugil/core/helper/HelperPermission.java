package com.tutcugil.core.helper;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

/**
 * Created by Muhammet TUTCUGIL on 23.09.2017.
 * http://www.tutcugil.com
 */

public class HelperPermission {
    public static boolean isGranted(Context context, String... permissions) {
        if (context == null || permissions == null)
            return false;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true;

        for (String permission : permissions)
            if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
                return false;

        return true;
    }

    public static boolean request(Context context, int code, String... permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true;

        if (context == null || !(context instanceof Activity))
            return false;

        if (!isGranted(context, permissions)) {
            ActivityCompat.requestPermissions((Activity) context, permissions, code);
            return false;
        }

        return true;
    }
}
