package com.tutcugil.core.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.tutcugil.core.base.BaseViewScheme;
import com.tutcugil.core.io.Logger;

import java.util.List;

/**
 * Created by Muhammet TUTCUGIL on 23.09.2017.
 * http://www.tutcugil.com
 */

public class ListAdapter extends ArrayAdapter<Object> {
    private Context mContext;
    private BaseViewScheme mScheme;

    public ListAdapter(Context context, int resource, List<Object> objects) {
        super(context, resource, objects);

        setContext(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mScheme == null) {
            Logger.warning("Scheme nesnesi list adapter a set edilmeli.");
            return convertView;
        }

        Object obj = getItem(position);
        if (obj == null)
            return convertView;

        return  mScheme.bind(position, convertView, obj);
    }

    public BaseViewScheme getScheme() {
        return mScheme;
    }

    public void setScheme(BaseViewScheme mScheme) {
        this.mScheme = mScheme;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }
}