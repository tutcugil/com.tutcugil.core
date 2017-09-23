package com.tutcugil.core.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tutcugil.core.io.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammet TUTCUGIL on 23.09.2017.
 * http://www.tutcugil.com
 */

public class HelperView {
    public static List<View> getByTag(ViewGroup root, String tag){
        ArrayList<View> views = new ArrayList<>();
        final int childCount = root.getChildCount();

        for (int i = 0; i < childCount; i++) {
            final View child = root.getChildAt(i);

            if (child instanceof ViewGroup)
                views.addAll(getByTag((ViewGroup) child, tag));

            final Object tagObj = child.getTag();
            if (tagObj != null && tagObj.equals(tag))
                views.add(child);
        }

        return views;
    }

    public static View inflate(Context context, int layout) {
        View view;

        if (context == null)
            return null;

        try {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            if (layoutInflater == null)
                return null;

            view = layoutInflater.inflate(layout, null);
        } catch (Exception ex){
            Logger.error(ex);
            return null;
        }

        return view;
    }
}
