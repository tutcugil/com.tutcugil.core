package com.tutcugil.core.holders;

import android.app.FragmentTransaction;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.tutcugil.core.adapter.ListAdapter;
import com.tutcugil.core.base.BaseFragment;
import com.tutcugil.core.base.BaseViewHolder;
import com.tutcugil.core.base.BaseViewScheme;
import com.tutcugil.core.fragment.FragmentBase;
import com.tutcugil.core.helper.HelperFragment;
import com.tutcugil.core.helper.HelperJson;
import com.tutcugil.core.helper.HelperKeyboard;
import com.tutcugil.core.interfaces.IFragmentReplace;
import com.tutcugil.core.interfaces.IListInterface;
import com.tutcugil.core.io.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammet TUTCUGIL on 25.09.2017.
 * http://www.tutcugil.com
 */

public abstract class ViewHolderFragmentList extends BaseViewHolder {
    public EditText etSearch;
    public ListView lvList;

    private List<Object> mListVisible;

    private BaseViewScheme mViewScheme;
    private Class mClassFragent;
    private IListInterface mListInterface;

    public int mAnimationEnter;
    public int mAnimationExit;
    public int mAnimationPopEnter;
    public int mAnimationPopExit;

    public abstract int getFragmentContainer();
    public abstract void setFragmentAnimation();

    public ViewHolderFragmentList(BaseViewScheme viewScheme, Class fragmentClass) {
        mClassFragent = fragmentClass;
        mViewScheme   = viewScheme;
    }

    public ViewHolderFragmentList(BaseViewScheme viewScheme, IListInterface listInterface) {
        mViewScheme    = viewScheme;
        mListInterface = listInterface;
    }

    @Override
    public int getLayout() {
        return 0;
    }

    @Override
    public void init(View view) {

    }

    @Override
    public void viewOperations(final Context context, int position, final Object object) {
        final List<Object> result = (List<Object>) object;

        mListVisible = result;

        final ListAdapter[] listAdapter = { new ListAdapter(context, 0, result, mViewScheme) };

        lvList.setAdapter(listAdapter[0]);
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mListVisible == null || mListVisible.size() <= position)
                    return;

                HelperKeyboard.hide(context);

                if (mListInterface != null) {
                    mListInterface.onClick(mListVisible.get(position));
                    return;
                }

                if (mClassFragent == null)
                    return;

                FragmentBase fragment = null;
                try {
                    fragment = (FragmentBase) mClassFragent.newInstance();
                } catch (Exception e) {
                    Logger.error(e);
                }

                if (fragment == null)
                    return;

                fragment.setData(mListVisible.get(position));
                HelperFragment.replace(context, fragment, getFragmentContainer(), true, new IFragmentReplace() {
                    @Override
                    public void onBefore(FragmentTransaction fragmentTransaction, BaseFragment fragment) {
                        fragmentTransaction.setCustomAnimations(
                                mAnimationEnter,
                                mAnimationExit,
                                mAnimationPopEnter,
                                mAnimationPopExit);
                    }
                });
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s == null)
                    return;

                List<Object> newList     = new ArrayList<>();
                List<Object> newListBase = new ArrayList<>();

                for (int i = 0; i < result.size(); i++) {
                    Object item = result.get(i);

                    if (!HelperJson.stringify(item).toLowerCase().contains(s.toString().toLowerCase()))
                        continue;

                    newList.add(item);
                    newListBase.add(item);
                }

                mListVisible = newList;

                listAdapter[0] = new ListAdapter(context, 0, newListBase, mViewScheme);

                lvList.setAdapter(listAdapter[0]);
                lvList.invalidateViews();
            }
        });
    }
}
