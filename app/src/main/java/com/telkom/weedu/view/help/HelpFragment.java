package com.telkom.weedu.view.help;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import com.telkom.weedu.R;
import com.telkom.weedu.base.BaseFragment;
import com.telkom.weedu.utils.AppConstants;
import com.telkom.weedu.view.about.AboutActivity;
import com.telkom.weedu.view.contactus.ContactUsActivity;
import com.telkom.weedu.view.webview.WebViewActivity;

import javax.inject.Inject;

import butterknife.BindView;
import io.smooch.ui.ConversationActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HelpFragment extends BaseFragment implements HelpView {


    @BindView(R.id.btn_help_chat)
    LinearLayout btnHelpChat;
    @BindView(R.id.btn_help_contact_us)
    LinearLayout btnHelpContactUs;
    @BindView(R.id.btn_help_faq)
    LinearLayout btnHelpFaq;
    @BindView(R.id.btn_help_tnc)
    LinearLayout btnHelpTnc;
    @BindView(R.id.btn_help_about)
    LinearLayout btnHelpAbout;

    @Inject
    IHelpPresenter<HelpView> presenter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_help;
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

        btnHelpChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openChatActivity();
            }
        });

        btnHelpAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openAboutActivity();
            }
        });

        btnHelpContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openContactActivity();
            }
        });

        btnHelpFaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openFaqActivity();
            }
        });

        btnHelpTnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openTncActivity();
            }
        });


    }

    @Override
    protected void initProcess() {

    }

    public static HelpFragment newInstance() {

        Bundle args = new Bundle();

        HelpFragment fragment = new HelpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void toChatActivity() {
        ConversationActivity.show(getBaseActivity());
    }

    @Override
    public void toContactActivity() {
        ContactUsActivity.start(getActivity());
    }

    @Override
    public void toFaqActivity() {
        WebViewActivity.start(getActivity(), AppConstants.FAQ_URL, getString(R.string.activity_title_faq));
    }

    @Override
    public void toTncActivity() {
        WebViewActivity.start(getActivity(), AppConstants.TERMS_AND_CONDITION_URL, getString(R.string.activity_title_terms_and_conditions));
    }

    @Override
    public void toAboutActivity() {
        AboutActivity.start(getActivity());
    }
}
