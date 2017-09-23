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
    private transient BaseViewHolder holder;

    public BaseViewScheme(Context context, BaseViewHolder holder){
        setContext(context);
        setHolder(holder);
    }

    public View bind(int position, View view, Object object) {
        if (holder == null){
            Logger.warning("Scheme objesi icin holder nesnesi set edilmelidir.");
            return view;
        }

        if (view == null) {
            view = HelperView.inflate(getContext(), holder.getLayout());

            if (view == null) {
                Logger.warning("View inflate edilemedi");
                return null;
            }

            holder = holder.init(view);

            view.setTag(holder);
        } else holder = (BaseViewHolder) view.getTag();

        if (holder == null)
            return view;

        if (object == null)
            return view;

        holder.viewOperations(getContext(), position, object);

        return view;
    }

    public BaseViewHolder getHolder() {
        return holder;
    }

    public void setHolder(BaseViewHolder holder) {
        holder = holder;
    }
}
