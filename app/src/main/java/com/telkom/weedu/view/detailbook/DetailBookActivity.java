package com.telkom.weedu.view.detailbook;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.telkom.weedu.R;
import com.telkom.weedu.base.BaseActivity;
import com.telkom.weedu.data.api.model.QBacaBook;
import com.telkom.weedu.utils.BundleKeys;
import com.telkom.weedu.utils.ImageUtils;

import javax.inject.Inject;

import butterknife.BindView;

public class DetailBookActivity extends BaseActivity implements DetailBookView,
        View.OnClickListener{

    @Inject
    IDetailBookPresenter<DetailBookView> presenter;

    @BindView(R.id.img_detailbuku)
    ImageView imgDetailbuku;
    @BindView(R.id.tv_preview)
    TextView tvPreview;
    @BindView(R.id.tv_title_detail_buku)
    TextView tvTitleDetailBuku;
    @BindView(R.id.tv_penulis_detail_buku)
    TextView tvPenulisDetailBuku;
    @BindView(R.id.tv_penerbit_detail_buku)
    TextView tvPenerbitDetailBuku;
    @BindView(R.id.tv_isbn_detail_buku)
    TextView tvIsbnDetailBuku;
    @BindView(R.id.tv_bahasa_detail_buku)
    TextView tvBahasaDetailBuku;
    @BindView(R.id.tv_detail_buku)
    TextView tvDetailBuku;
    @BindView(R.id.activity_detail_buku)
    RelativeLayout activityDetailBuku;
    @BindView(R.id.btn_view_in_qbaca)
    Button btnViewInQbaca;

    private QBacaBook qBacaBook;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_detail_buku;
    }

    @Override
    protected void initLib() {
        getActivityComponent().inject(this);
        presenter.onAttach(this);
    }

    @Override
    protected void initIntent() {
        qBacaBook = getIntent().getParcelableExtra(BundleKeys.KEY_QBACA_BOOK);
    }

    @Override
    protected void initUI() {
        btnViewInQbaca.setOnClickListener(this);
        setupToolbar(qBacaBook.getTitle(), true);
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initProcess() {
        ImageUtils.setImageUrl(DetailBookActivity.this, qBacaBook.getImage(), imgDetailbuku);
        tvTitleDetailBuku.setText(qBacaBook.getTitle());
        tvIsbnDetailBuku.setText(qBacaBook.getIsbn());
        tvBahasaDetailBuku.setText(R.string.label_language_indonesia);
        tvDetailBuku.setText(Html.fromHtml(qBacaBook.getSinopsis()));
        tvPenulisDetailBuku.setText(qBacaBook.getAuthors());
        tvPenerbitDetailBuku.setText(qBacaBook.getPublisherName());
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_view_in_qbaca){

        }
    }

    public static void start(Context context, QBacaBook book) {
        Intent starter = new Intent(context, DetailBookActivity.class);
        starter.putExtra(BundleKeys.KEY_QBACA_BOOK, book);
        context.startActivity(starter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}
