package com.telkom.weedu.view.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.telkom.weedu.R;
import com.telkom.weedu.base.BaseActivity;
import com.telkom.weedu.base.BaseFragment;
import com.telkom.weedu.view.adapters.MainTabAdapter;
import com.telkom.weedu.view.dashboard.DashboardFragment;
import com.telkom.weedu.view.help.HelpFragment;
import com.telkom.weedu.view.home.HomeFragment;
import com.telkom.weedu.view.myaccount.MyAccountFragment;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindColor;
import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainView {

    @Inject
    IMainPresenter<MainView> mainPresenter;
    @Inject
    FirebaseAnalytics firebaseAnalytics;

    //    region butterknife injections
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_main)
    TabLayout tabMain;
    @BindView(R.id.vp_main)
    ViewPager vpMain;

    @BindColor(R.color.white)
    int textActivated;
    @BindColor(R.color.whiteTransparent)
    int textDeactivated;

//    endregion

    private MainTabAdapter mTabAdapter;
    private ArrayList<BaseFragment> mFragments;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void initLib() {
        getActivityComponent().inject(MainActivity.this);
        mainPresenter.onAttach(this);
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initUI() {
        setupToolbar(toolbar, getString(R.string.app_name), false);

        setupViewPager();
        tabMain.setupWithViewPager(vpMain);
        setupTabIcons();
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initProcess() {
        logEvent();
    }

    private void setupViewPager() {
        mFragments = new ArrayList<>();
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(DashboardFragment.newInstance());
        mFragments.add(HelpFragment.newInstance());
        mFragments.add(MyAccountFragment.newInstance());

        mTabAdapter = new MainTabAdapter(getSupportFragmentManager(), mFragments);

        vpMain.setAdapter(mTabAdapter);
        vpMain.setOffscreenPageLimit(3);

    }

    private void setupTabIcons() {

        TextView tabHome = (TextView) LayoutInflater.from(this).inflate(R.layout.tab_custom, null);
        tabHome.setText("Home");
        tabHome.setTextColor(textActivated);
        tabHome.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_menu_home_active, 0, 0);
        tabMain.getTabAt(0).setCustomView(tabHome);

        TextView tabDashboard = (TextView) LayoutInflater.from(this).inflate(R.layout.tab_custom, null);
        tabDashboard.setText("Dashboard");
        tabDashboard.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_menu_dashboard_noactive, 0, 0);
        tabMain.getTabAt(1).setCustomView(tabDashboard);

        TextView tabHelp = (TextView) LayoutInflater.from(this).inflate(R.layout.tab_custom, null);
        tabHelp.setText("Help");
        tabHelp.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_menu_help_noactive, 0, 0);
        tabMain.getTabAt(2).setCustomView(tabHelp);


        TextView tabAccount = (TextView) LayoutInflater.from(this).inflate(R.layout.tab_custom, null);
        tabAccount.setText("My Account");
        tabAccount.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_menu_myaccount_noactive, 0, 0);
        tabMain.getTabAt(3).setCustomView(tabAccount);


        tabMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView tvTab = (TextView) tab.getCustomView().findViewById(R.id.tab);
                tvTab.setTextColor(textActivated);
                switch (tab.getPosition()) {
                    case 0:
                        tvTab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_menu_home_active, 0, 0);
                        break;
                    case 1:
                        tvTab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_menu_dashboard_active, 0, 0);
                        break;
                    case 2:
                        tvTab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_menu_help_active, 0, 0);
                        break;
                    case 3:
                        tvTab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_menu_myaccount_active, 0, 0);
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView tvTab = (TextView) tab.getCustomView().findViewById(R.id.tab);
                tvTab.setTextColor(textDeactivated);
                switch (tab.getPosition()) {
                    case 0:
                        tvTab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_menu_home_noactive, 0, 0);
                        break;
                    case 1:
                        tvTab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_menu_dashboard_noactive, 0, 0);
                        break;
                    case 2:
                        tvTab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_menu_help_noactive, 0, 0);
                        break;
                    case 3:
                        tvTab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_menu_myaccount_noactive, 0, 0);
                        break;

                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    public TabLayout getTabMain() {
        return tabMain;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        starter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(starter);
    }

    private void logEvent() {
        Bundle bundle = new Bundle();
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, bundle);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.onDetach();
    }
}
