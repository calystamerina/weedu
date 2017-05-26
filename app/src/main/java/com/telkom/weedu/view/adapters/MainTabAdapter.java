package com.telkom.weedu.view.adapters;

import android.support.v4.app.FragmentManager;

import com.telkom.weedu.base.BaseFragment;
import com.telkom.weedu.base.adapter.BasePagerAdapter;

import java.util.List;

/**
 * Created by ghiyatshanif on 3/28/17.
 */

public class MainTabAdapter extends BasePagerAdapter<BaseFragment> {

    public MainTabAdapter(FragmentManager fm, List<BaseFragment> baseFragments, List<String> titles) {
        super(fm, baseFragments, titles);
    }

    public MainTabAdapter(FragmentManager fm, List<BaseFragment> baseFragments) {
        super(fm, baseFragments);
    }

    @Override
    public BaseFragment getItem(int position) {
        return mFragments.get(position);
    }
}
