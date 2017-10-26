package com.tutcugil.core.base;

import android.content.Context;
import android.view.View;

import com.tutcugil.core.helper.HelperView;
import com.tutcugil.core.io.Logger;

/**
 * Created by Muhammet TUTCUGIL on 23.09.2017.
 * http://www.tutcugil.com
 */

public abstract class BaseViewScheme extends BaseClass {
    private transient Class mHolderClass;

    public BaseViewScheme(Context context, Class holderClass){
        setContext(context);
        setHolderClass(holderClass);
    }

    public View bind(int position, View view, Object object) {
        if (getHolderClass() == null){
            Logger.warning("Scheme objesi icin holder nesnesi set edilmelidir.");
            return view;
        }

        final BaseViewHolder holder;

        if (view == null) {
            try {
                holder = (BaseViewHolder) getHolderClass().newInstance();
            } catch (Exception e) {
                Logger.error(e);
                return null;
            }

            view = HelperView.inflate(getContext(), holder.getLayout());

            if (view == null) {
                Logger.warning("View inflate edilemedi");
                return null;
            }

            holder.init(view);

            view.setTag(holder);
        } else holder = (BaseViewHolder) view.getTag();

        if (holder == null)
            return view;

        if (object == null)
            return view;

        holder.viewOperations(getContext(), position, object);

        return view;
    }

    public Class getHolderClass() {
        return mHolderClass;
    }

    public void setHolderClass(Class cls) {
        this.mHolderClass = cls;
    }
}
