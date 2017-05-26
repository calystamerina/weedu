package com.telkom.weedu.view.adapters;

import android.support.v4.app.FragmentManager;

import com.telkom.weedu.base.BaseFragment;
import com.telkom.weedu.base.adapter.BasePagerAdapter;

import java.util.List;

/**
 * Created by sidiqpermana on 4/5/17.
 */

public class IntroductionPagerAdapter extends BasePagerAdapter<BaseFragment> {
    public IntroductionPagerAdapter(FragmentManager fm, List<BaseFragment> baseFragments) {
        super(fm, baseFragments);
    }

    public IntroductionPagerAdapter(FragmentManager fm, List<BaseFragment> baseFragments, List<String> titles) {
        super(fm, baseFragments, titles);
    }

    @Override
    public BaseFragment getItem(int position) {
        return mFragments.get(position);
    }
}
