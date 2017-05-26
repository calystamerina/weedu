package com.telkom.weedu.view.webview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.telkom.weedu.R;
import com.telkom.weedu.base.BaseActivity;
import com.telkom.weedu.utils.BundleKeys;

import javax.inject.Inject;

import butterknife.BindView;


public class WebViewActivity extends BaseActivity implements WebViewView {

    @Inject
    IWebViewPresenter<WebViewView> presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.wv_content)
    WebView wvContent;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    private String url;
    private String title;

    public static void start(Context context, String url, String title) {
        Intent starter = new Intent(context, WebViewActivity.class);
        starter.putExtra(BundleKeys.KEY_WEBVIEW_URL, url);
        starter.putExtra(BundleKeys.KEY_WEBVIEW_TITLE, title);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initLib() {
        // TODO implements injection here
        getActivityComponent().inject(WebViewActivity.this);
        presenter.onAttach(this);
    }

    @Override
    protected void initIntent() {
        title = getIntent().getStringExtra(BundleKeys.KEY_WEBVIEW_TITLE);
        url = getIntent().getStringExtra(BundleKeys.KEY_WEBVIEW_URL);
    }

    @Override
    protected void initUI() {
        setupToolbar(toolbar, title, true);
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initProcess() {
        wvContent.loadUrl(url);
        wvContent.getSettings().setJavaScriptEnabled(true);
        wvContent.getSettings().setAllowFileAccess(true);
        wvContent.getSettings().setAllowContentAccess(true);
        wvContent.getSettings().setAppCacheEnabled(true);
        wvContent.getSettings().setDatabaseEnabled(true);
        wvContent.setWebViewClient(new CustomeWebViewClient());
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

    private class CustomeWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if (progressBar != null) {
                progressBar.setVisibility(View.VISIBLE);
            }
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (progressBar != null) {
                if (progressBar.getVisibility() == View.VISIBLE) {
                    progressBar.setVisibility(View.GONE);
                }
            }
            super.onPageFinished(view, url);
        }
    }

    @Override
    protected void onDestroy() {
        destroyWebView();
        super.onDestroy();
    }

    public void destroyWebView() {

        wvContent.removeAllViews();

        if (wvContent != null) {
            wvContent.loadUrl("about:blank");
            wvContent.setWebViewClient(null);
            wvContent.freeMemory();
            wvContent.pauseTimers();
            wvContent.destroy();
            wvContent = null;
        }

    }
}
