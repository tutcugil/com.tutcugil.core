package com.tutcugil.core.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tutcugil.core.base.BaseFragment;
import com.tutcugil.core.helper.HelperView;

/**
 * Created by Muhammet TUTCUGIL on 25.09.2017.
 * http://www.tutcugil.com
 */

public class FragmentBase extends BaseFragment {
    @Override
    public String getTAG() {
        return "FragmentBase";
    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public int getLayout() {
        return 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return HelperView.inflate(inflater, getLayout(), container);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getTitle() != null)
            getActivity().setTitle(getTitle());
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
