package com.tutcugil.core.interfaces;

import android.app.FragmentTransaction;

import com.tutcugil.core.base.BaseFragment;

/**
 * Created by Muhammet TUTCUGIL on 23.09.2017.
 * http://www.tutcugil.com
 */

public interface IFragmentReplace {
    void onBefore(FragmentTransaction fragmentTransaction, BaseFragment fragment);
}
