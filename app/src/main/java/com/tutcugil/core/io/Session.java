package com.tutcugil.core.io;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Muhammet TUTCUGIL on 23.09.2017.
 * http://www.tutcugil.com
 */

public class Session {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static Session mSession;

    private Session(){

    }

    public static Session getInstance(){
        if (mSession == null)
            mSession = new Session();

        return mSession;
    }

    public static Session getInstance(Context context){
        if (mSession == null) {
            mSession = new Session();
            mSession.setContext(context);
        }

        return mSession;
    }

    public void setContext(Context context) {
        sharedPreferences = context.getSharedPreferences(context.getPackageName(), 0); // PRIVATE MODE
    }

    @SuppressLint("ApplySharedPref")
    public void set(String key, String value) {
        editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String get(String key) {
        return sharedPreferences.getString(key, "");
    }
}
