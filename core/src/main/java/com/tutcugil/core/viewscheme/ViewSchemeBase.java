package com.tutcugil.core.viewscheme;

import android.content.Context;

import com.tutcugil.core.base.BaseViewScheme;

/**
 * Created by Muhammet TUTCUGIL on 27.09.2017.
 * http://www.tutcugil.com
 */

public class ViewSchemeBase extends BaseViewScheme {
    public ViewSchemeBase(Context context, Class holderClass) {
        super(context, holderClass);
    }

    @Override
    protected String getTAG() {
        return "ViewSchemeBase";
    }
}
