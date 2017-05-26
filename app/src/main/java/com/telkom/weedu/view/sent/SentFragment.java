package com.telkom.weedu.view.sent;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.telkom.weedu.R;
import com.telkom.weedu.base.BaseFragment;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class SentFragment extends BaseFragment implements SentView {

    @Inject
    ISentPresenter<SentView> presenter;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_sent;
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


    public static SentFragment newInstance(){
        Bundle args = new Bundle();
        SentFragment fragment = new SentFragment();
        fragment.setArguments(args);

        return fragment;
    }
}
