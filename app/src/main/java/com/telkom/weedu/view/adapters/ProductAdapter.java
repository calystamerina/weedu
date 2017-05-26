package com.telkom.weedu.view.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.telkom.weedu.R;
import com.telkom.weedu.base.adapter.BaseRecyclerAdapter;
import com.telkom.weedu.base.adapter.viewholder.BaseItemViewHolder;
import com.telkom.weedu.data.api.model.Product;
import com.telkom.weedu.utils.ImageUtils;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ghiyatshanif on 3/28/17.
 */

public class ProductAdapter
        extends BaseRecyclerAdapter<Product, ProductAdapter.ProductViewHolder> {

    public ProductAdapter(Context context) {
        super(context);
    }

    public ProductAdapter(Context context, LinkedList<Product> data) {
        super(context, data);
    }

    @Override
    protected int getItemResourceLayout(int viewType) {
        return R.layout.item_products;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductViewHolder(mContext, getView(parent, viewType), mItemClickListener, mLongItemClickListener);
    }

    public class ProductViewHolder extends BaseItemViewHolder<Product> {


        @BindView(R.id.img_product)
        ImageView imgProduct;
        @BindView(R.id.tv_product_name)
        TextView tvProductName;
        @BindView(R.id.tv_product_description)
        TextView tvProductDescription;

        public ProductViewHolder(Context mContext, View itemView, OnItemClickListener itemClickListener, OnLongItemClickListener longItemClickListener) {
            super(mContext, itemView, itemClickListener, longItemClickListener);
        }

        @Override
        public void bind(Product product) {
            tvProductName.setText(product.getName());
            tvProductDescription.setText(product.getDescription());
            ImageUtils.setImage(mContext, product.getImage(),imgProduct);
        }
    }
}


