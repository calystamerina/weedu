package com.telkom.weedu.view.adapters;

import android.support.v4.app.FragmentManager;

import com.telkom.weedu.base.BaseFragment;
import com.telkom.weedu.base.adapter.BasePagerAdapter;

import java.util.List;

/**
 * Created by Dodi Rivaldi on 05/04/2017.
 */

public class InboxTabAdapter extends BasePagerAdapter<BaseFragment> {
    public InboxTabAdapter(FragmentManager fm, List<BaseFragment> baseFragments, List<String> titles) {
        super(fm, baseFragments, titles);
    }

    public InboxTabAdapter(FragmentManager fm, List<BaseFragment> baseFragments) {
        super(fm, baseFragments);
    }

    @Override
    public BaseFragment getItem(int position) {
        return mFragments.get(position);
    }
}
