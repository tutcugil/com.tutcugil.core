package com.tutcugil.core.io;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Muhammet TUTCUGIL on 23.09.2017.
 * http://www.tutcugil.com
 */

public class Session {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private static Session mSession;

    private Session(){

    }

    public static Session getInstance(Context context){
        if (mSession == null) {
            mSession = new Session();
            mSession.setContext(context);
        }

        return mSession;
    }

    public void setContext(Context context) {
        mSharedPreferences = context.getSharedPreferences(context.getPackageName(), 0); // PRIVATE MODE
    }

    @SuppressLint("ApplySharedPref")
    public void set(String key, String value) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(key, value);
        mEditor.commit();
    }

    public String get(String key) {
        return mSharedPreferences.getString(key, "");
    }
}
