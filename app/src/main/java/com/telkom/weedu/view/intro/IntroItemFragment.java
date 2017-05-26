package com.telkom.weedu.view.intro;


import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.telkom.weedu.R;
import com.telkom.weedu.base.BaseFragment;
import com.telkom.weedu.utils.ImageUtils;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroItemFragment extends BaseFragment implements IntroItemView {
    @BindView(R.id.img_into)
    ImageView imgInto;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_description)
    TextView tvDescription;

    private int image;
    private String title;
    private String description;
    private int mainColor;

    public IntroItemFragment() {
        // Required empty public constructor
    }

    public int getMainColor() {
        return mainColor;
    }

    public void setMainColor(int mainColor) {
        this.mainColor = mainColor;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_intro_item;
    }

    @Override
    protected void initLib() {

    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initUI() {
        tvTitle.setTextColor(getMainColor());
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initProcess() {
        ImageUtils.setImage(getActivity(), getImage(), imgInto);
        tvTitle.setText(getTitle());
        tvDescription.setText(getDescription());
    }
}
