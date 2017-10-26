package com.tutcugil.core.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Base64;

import com.tutcugil.core.io.Logger;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Muhammet TUTCUGIL on 23.09.2017.
 * http://www.tutcugil.com
 */

public class HelperBitmap {
    public static Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public static Drawable getImageFromUrl(String url) {
        try {
            Logger.trace(url);
            InputStream is = (InputStream) new URL(url).getContent();
            return Drawable.createFromStream(is, "src name");
        } catch (Exception e) {
            Logger.error(e);
            return null;
        }
    }

    public static Bitmap combine(Bitmap bmp1, Bitmap bmp2, int x, int y){
        if (bmp1 == null || bmp2 == null)
            return null;

        Bitmap bmCombined = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bmCombined);
        canvas.drawBitmap(bmp1, 0, 0, null);
        canvas.drawBitmap(bmp2, x, y, null);

        return bmCombined;
    }

    // Read bitmap
    public static Bitmap read(String imagePath) {
        BitmapFactory.Options
                options = new BitmapFactory.Options();
        options.inSampleSize = 4;

        return BitmapFactory.decodeFile(imagePath, options);
    }

    // Read bitmap
    public static Bitmap read(String imagePath, int w) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options
                options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(imagePath, options);

        int inSampleSize = 1;
        if (w > 0) {
            int ratio = options.outWidth / w;
            if (ratio > 1)
                inSampleSize = ratio - (ratio % 2);
        }

        options.inSampleSize = inSampleSize;

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(imagePath, options);
    }

    public static void clear(Bitmap bm) {
        bm.recycle();
        System.gc();
    }

    public static String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        return Base64.encodeToString(byteArray, Base64.NO_WRAP);
    }
}
