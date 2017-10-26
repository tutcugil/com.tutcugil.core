package com.tutcugil.core.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
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
    private BaseViewScheme mScheme;

    public ListAdapter(Context context, int resource, List<Object> objects, BaseViewScheme scheme) {
        super(context, resource, objects);

        mScheme = scheme;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mScheme == null) {
            Logger.warning("Scheme nesnesi list adapter a set edilmeli.");
            return convertView;
        }

        Object obj = getItem(position);
        if (obj == null)
            return convertView;

        return mScheme.bind(position, convertView, obj);
    }
}