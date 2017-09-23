package com.tutcugil.core.base;

import android.app.Activity;
import android.content.Context;

import com.tutcugil.core.io.Logger;

/**
 * Created by Muhammet TUTCUGIL on 23.09.2017.
 * http://www.tutcugil.com
 */

public abstract class BaseClass {
    protected transient Context mContext;

    protected abstract String getTAG();

    public void setContext (Context context){
        this.mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    public Activity getActivity() {
        if (getContext() == null) {
            Logger.trace("context is null");
            return null;
        }

        return (Activity) getContext();
    }
}
