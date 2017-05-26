package com.telkom.weedu.view.intro;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.telkom.weedu.R;
import com.telkom.weedu.base.BaseActivity;
import com.telkom.weedu.base.BaseFragment;
import com.telkom.weedu.view.adapters.IntroductionPagerAdapter;
import com.telkom.weedu.view.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import me.relex.circleindicator.CircleIndicator;

public class IntroductionActivity extends BaseActivity implements IntroductionView,
        View.OnClickListener {

    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.circle_indicator)
    CircleIndicator circleIndicator;
    @BindView(R.id.btn_skip)
    Button btnSkip;
    @BindView(R.id.rl_intro_background)
    RelativeLayout rlBackground;
    @BindView(R.id.btn_prev)
    ImageButton btnPrev;
    @BindView(R.id.ln_navigation_prev)
    LinearLayout lnNavigationPrev;
    @BindView(R.id.btn_next)
    ImageButton btnNext;
    @BindView(R.id.ln_navigation_next)
    LinearLayout lnNavigationNext;

    @Inject
    IIntroductionPresenter<IntroductionView> introductionPresenter;

    IntroductionPagerAdapter adapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_introduction;
    }

    @Override
    protected void initLib() {
        getActivityComponent().inject(IntroductionActivity.this);
        introductionPresenter.onAttach(this);
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initAction() {
        btnSkip.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnPrev.setOnClickListener(this);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                applyStatusbarColor(position);
                applyBackgroundColor(position);
                applyViewPagerCurrentPosition(position);
                applyShowHideNavigationPanel(position);
                applyButtonTextChange(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initProcess() {
        List<BaseFragment> listFragment = new ArrayList<>();

        IntroItemFragment introItemFragmentA = new IntroItemFragment();

        introItemFragmentA.setTitle("Nikmati Pengalaman Baru Dengan Aplikasi Weedu");
        introItemFragmentA.setDescription(getString(R.string.lorem_ipsum));
        introItemFragmentA.setImage(R.drawable.onboard_1);
        introItemFragmentA.setMainColor(ContextCompat.getColor(this, R.color.colorPrimary));

        IntroItemFragment introItemFragmentB = new IntroItemFragment();

        introItemFragmentB.setTitle("Kemudahan Dalam Satu Genggaman Ke Berbagai Platform");
        introItemFragmentB.setDescription(getString(R.string.lorem_ipsum));
        introItemFragmentB.setImage(R.drawable.onboard_2);
        introItemFragmentB.setMainColor(ContextCompat.getColor(this, R.color.amber600));

        IntroItemFragment introItemFragmentC = new IntroItemFragment();

        introItemFragmentC.setTitle("Dapatkan Akun EduMail Anda Secara Gratis");
        introItemFragmentC.setDescription(getString(R.string.lorem_ipsum));
        introItemFragmentC.setImage(R.drawable.onboard_3);
        introItemFragmentC.setMainColor(ContextCompat.getColor(this, R.color.red600));

        listFragment.add(introItemFragmentA);
        listFragment.add(introItemFragmentB);
        listFragment.add(introItemFragmentC);

        adapter = new IntroductionPagerAdapter(getSupportFragmentManager(), listFragment);
        pager.setAdapter(adapter);
        circleIndicator.setViewPager(pager);
    }

    @Override
    public void toLoginActivity() {
        LoginActivity.start(IntroductionActivity.this);
        finishActivity();
    }

    public void applyStatusbarColor(int position) {
        int color = 0;
        switch (position) {
            case 0:
                color = ContextCompat.getColor(this, R.color.colorPrimaryDark);
                break;

            case 1:
                color = ContextCompat.getColor(this, R.color.amber800);
                break;

            case 2:
                color = ContextCompat.getColor(this, R.color.red800);
                break;
        }

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(color);
        }
    }

    public void applyBackgroundColor(int position) {
        int color = 0;
        switch (position) {
            case 0:
                color = ContextCompat.getColor(this, R.color.colorPrimary);
                break;

            case 1:
                color = ContextCompat.getColor(this, R.color.amber600);
                break;

            case 2:
                color = ContextCompat.getColor(this, R.color.red600);
                break;
        }

        rlBackground.setBackgroundColor(color);
    }

    public void applyButtonTextChange(int position) {
        switch (position) {
            case 0:
                btnSkip.setText(R.string.btn_skip);
                break;

            case 1:
                btnSkip.setText(R.string.btn_skip);
                break;

            case 2:
                btnSkip.setText(R.string.btn_ready);
                break;
        }
    }

    public void applyShowHideNavigationPanel(int position) {
        switch (position) {
            case 0:
                lnNavigationPrev.setVisibility(View.INVISIBLE);
                lnNavigationNext.setVisibility(View.VISIBLE);
                break;

            case 1:
                lnNavigationPrev.setVisibility(View.VISIBLE);
                lnNavigationNext.setVisibility(View.VISIBLE);
                break;

            case 2:
                lnNavigationPrev.setVisibility(View.VISIBLE);
                lnNavigationNext.setVisibility(View.INVISIBLE);
                break;
        }
    }

    public void applyViewPagerCurrentPosition(int position) {
        pager.setCurrentItem(position, true);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_skip:
                if (pager.getCurrentItem() < 2) {
                    pager.setCurrentItem(2);
                } else {
                    introductionPresenter.openLoginActivity();
                }
                break;

            case R.id.btn_prev:
                applyViewPagerCurrentPosition(pager.getCurrentItem() - 1);
                break;

            case R.id.btn_next:
                applyViewPagerCurrentPosition(pager.getCurrentItem() + 1);
                break;
        }
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, IntroductionActivity.class);
        context.startActivity(starter);
    }
}
