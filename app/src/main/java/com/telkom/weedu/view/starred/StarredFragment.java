package com.telkom.weedu.view.starred;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.telkom.weedu.R;
import com.telkom.weedu.base.BaseFragment;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class StarredFragment extends BaseFragment implements StarredView {

    @Inject
    IStarredPresenter<StarredView> presenter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_starred;
    }

    @Override
    protected void initLib() {
        getActivityComponent().inject(this);
        presenter.onAttach(this);
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initProcess() {

    }


    public static StarredFragment newInstance(){
        Bundle args = new Bundle();
        StarredFragment fragment = new StarredFragment();
        fragment.setArguments(args);

        return fragment;
    }
}
