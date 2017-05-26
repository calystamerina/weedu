package com.telkom.weedu.view.mail;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.claudiodegio.msv.MaterialSearchView;
import com.claudiodegio.msv.OnSearchViewListener;
import com.telkom.weedu.R;
import com.telkom.weedu.base.BaseActivity;
import com.telkom.weedu.base.BaseFragment;
import com.telkom.weedu.utils.AppConstants;
import com.telkom.weedu.view.adapters.InboxTabAdapter;
import com.telkom.weedu.view.mailbox.MailboxFragment;
import com.telkom.weedu.view.searchmail.SearchMailActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindColor;
import butterknife.BindView;

public class MailActivity extends BaseActivity implements MailView,
        OnSearchViewListener{

    @Inject
    IMailPresenter<MailView> presenter;

    //inject butterknife
    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar toolbar;
    @BindView(R.id.tab_inbox)
    TabLayout mTabInbox;
    @BindView(R.id.vp_inbox)
    ViewPager vpInbox;
    @BindView(R.id.mv_search)
    MaterialSearchView mvSearchView;

    @BindColor(R.color.colorPrimary)
    int textActivated;
    @BindColor(R.color.textGray)
    int textDeactivated;

    private InboxTabAdapter mTabAdapter;
    private ArrayList<BaseFragment> mFragment;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_mail;
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
        setupToolbar(toolbar, getString(R.string.activity_title_string), false);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupViewPager();
        mTabInbox.setupWithViewPager(vpInbox);
        setupTab();

    }

    @Override
    protected void initAction() {
        mTabInbox.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView tvTab = (TextView) tab.getCustomView().findViewById(R.id.tab);
                tvTab.setTextColor(textActivated);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView tvTab = (TextView) tab.getCustomView().findViewById(R.id.tab);
                tvTab.setTextColor(textDeactivated);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void initProcess() {

    }

    private void setupViewPager(){
        mFragment = new ArrayList<>();
        mFragment.add(MailboxFragment.newInstance(AppConstants.EDUMAIL_TYPE_INBOX));
        mFragment.add(MailboxFragment.newInstance(AppConstants.EDUMAIL_TYPE_STARRED));
        mFragment.add(MailboxFragment.newInstance(AppConstants.EDUMAIL_TYPE_SENT));
        mFragment.add(MailboxFragment.newInstance(AppConstants.EDUMAIL_TYPE_DRAFT));
        mFragment.add(MailboxFragment.newInstance(AppConstants.EDUMAIL_TYPE_TRASH));

        mTabAdapter = new InboxTabAdapter(getSupportFragmentManager(), mFragment);
        vpInbox.setAdapter(mTabAdapter);
    }

    private void setupTab(){
        TextView tabInbox = (TextView) LayoutInflater.from(this).inflate(R.layout.tab_custom,null);
        tabInbox.setText(R.string.tab_inbox);
        tabInbox.setTextColor(textActivated);
        mTabInbox.getTabAt(0).setCustomView(tabInbox);

        TextView tab2 = (TextView) LayoutInflater.from(this).inflate(R.layout.tab_custom,null);
        tab2.setText(R.string.tab_starred);
        tab2.setTextColor(textDeactivated);
        mTabInbox.getTabAt(1).setCustomView(tab2);

        TextView tab3 = (TextView) LayoutInflater.from(this).inflate(R.layout.tab_custom,null);
        tab3.setText(R.string.tab_sent);
        tab3.setTextColor(textDeactivated);
        mTabInbox.getTabAt(2).setCustomView(tab3);

        TextView tab4 = (TextView) LayoutInflater.from(this).inflate(R.layout.tab_custom,null);
        tab4.setText(R.string.tab_draft);
        tab4.setTextColor(textDeactivated);
        mTabInbox.getTabAt(3).setCustomView(tab4);

        TextView tab5 = (TextView) LayoutInflater.from(this).inflate(R.layout.tab_custom,null);
        tab5.setText(R.string.tab_trash);
        tab5.setTextColor(textDeactivated);
        mTabInbox.getTabAt(4).setCustomView(tab5);


    }

    public static void start(Context context) {
        Intent starter = new Intent(context, MailActivity.class);
        context.startActivity(starter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inbox, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        mvSearchView.setMenuItem(item);
        mvSearchView.setOnSearchViewListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            // finish the activity
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSearchViewShown() {

    }

    @Override
    public void onSearchViewClosed() {

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        SearchMailActivity.start(MailActivity.this, s);
        mvSearchView.closeSearch();
        return true;
    }

    @Override
    public void onQueryTextChange(String s) {

    }

    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}
