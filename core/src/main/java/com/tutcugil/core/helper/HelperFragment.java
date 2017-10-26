package com.tutcugil.core.helper;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;

import com.tutcugil.core.base.BaseFragment;
import com.tutcugil.core.interfaces.IFragmentReplace;
import com.tutcugil.core.io.Logger;

/**
 * Created by Muhammet TUTCUGIL on 23.09.2017.
 * http://www.tutcugil.com
 */

public class HelperFragment {
    public static void replace (Context context, BaseFragment fragment, int container) {
        replace(context, fragment, container, true, null);
    }

    public static void replace (Context context, BaseFragment fragment, int container, IFragmentReplace fragmentReplaceInterface) {
        replace(context, fragment, container, true, fragmentReplaceInterface);
    }

    public static void replace (Context context, BaseFragment fragment, int container, boolean addToBackStack, IFragmentReplace fragmentReplaceInterface) {
        if (context == null || !(context instanceof Activity))
            return;

        FragmentManager fragmentManager = ((Activity) context).getFragmentManager();

        if (fragmentManager == null) {
            Logger.warning("FragmentManager NULL");
            return;
        }

        BaseFragment currentFragment = (BaseFragment) fragmentManager.findFragmentByTag(fragment.getTAG());
        if (currentFragment != null
                && currentFragment.isVisible())
            return;

        try {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (fragmentTransaction == null) {
                Logger.warning("FragmentTransaction NULL");
                return;
            }

            if (fragmentReplaceInterface != null)
                fragmentReplaceInterface.onBefore(fragmentTransaction, fragment);

            if (addToBackStack)
                fragmentTransaction.addToBackStack(fragment.getTAG());

            fragmentTransaction.replace(container, fragment, fragment.getTAG());

            fragmentTransaction.commit();

            if (fragment.getTitle() != null)
              ((Activity) context).setTitle(fragment.getTitle());
        } catch (Exception ex){
            Logger.error(ex);
        }
    }
}
