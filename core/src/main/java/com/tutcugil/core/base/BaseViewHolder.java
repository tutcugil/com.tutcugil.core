package com.tutcugil.core.base;

import android.content.Context;
import android.view.View;

/**
 * Created by Muhammet TUTCUGIL on 23.09.2017.
 * http://www.tutcugil.com
 */

public abstract class BaseViewHolder{
    public abstract int getLayout();
    public abstract void init(View view);
    public abstract void viewOperations(Context context, int position, Object object);
}
