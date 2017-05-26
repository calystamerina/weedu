package com.telkom.weedu.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by ghiyatshanif on 2/21/17.
 * purpose : inflate image to ImageView(or its child)
 * notes : dependant to Glide library
 */

public class ImageUtils {

    public static <T extends ImageView> void setImage(Context context, int imageRes, T t) {
        Glide.with(context)
                .load(imageRes)
                .into(t);
    }

    public static <T extends ImageView> void setImage(Context context, int imageRes, int placeholderResourceId, T t) {
        Glide.with(context)
                .load(imageRes)
                .error(placeholderResourceId)
                .into(t);
    }

    public static <T extends ImageView> void setImage(Context context, int imageRes, int placeHolderResourceId, int errorResourceId, T t) {
        Glide.with(context)
                .load(imageRes)
                .placeholder(placeHolderResourceId)
                .error(errorResourceId)
                .into(t);
    }

    public static <T extends ImageView> void setImage(Context context, int imageRes, int placeHolderDrawable, Drawable errorDrawable, T t) {
        Glide.with(context)
                .load(imageRes)
                .placeholder(placeHolderDrawable)
                .error(errorDrawable)
                .into(t);
    }

    public static <T extends ImageView> void setImage(Context context, int imageRes, final ProgressBar progressBar, T t) {
        progressBar.setVisibility(VISIBLE);
        Glide.with(context)
                .load(imageRes)
                .listener(new RequestListener<Integer, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, Integer model, Target<GlideDrawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, Integer model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                }).into(t);
    }

    public static <T extends ImageView> void setImageUrl(Context context, int imageRes, final ProgressBar progressBar, int errorResourceId, T t) {
        progressBar.setVisibility(VISIBLE);
        Glide.with(context)
                .load(imageRes)
                .listener(new RequestListener<Integer, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, Integer model, Target<GlideDrawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, Integer model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .error(errorResourceId)
                .into(t);
    }

    public static <T extends ImageView> void setImage(Context context, int imageRes, final ProgressBar progressBar, Drawable errorDrawable, T t) {
        progressBar.setVisibility(VISIBLE);
        Glide.with(context)
                .load(imageRes)
                .listener(new RequestListener<Integer, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, Integer model, Target<GlideDrawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, Integer model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        progressBar.setVisibility(GONE);
                        return false;
                    }
                })
                .error(errorDrawable)
                .into(t);
    }


    public static <T extends ImageView> void setImageUrl(Context context, String url, T t) {
        Glide.with(context)
                .load(url)
                .into(t);
    }

    public static <T extends ImageView> void setImageUrl(Context context, String url, int placeholderResourceId, T t) {
        Glide.with(context)
                .load(url)
                .error(placeholderResourceId)
                .into(t);
    }

    public static <T extends ImageView> void setImageUrl(Context context, String url, int placeHolderResourceId, int errorResourceId, T t) {
        Glide.with(context)
                .load(url)
                .placeholder(placeHolderResourceId)
                .error(errorResourceId)
                .into(t);
    }

    public static <T extends ImageView> void setImageUrl(Context context, String url, int placeHolderDrawable, Drawable errorDrawable, T t) {
        Glide.with(context)
                .load(url)
                .placeholder(placeHolderDrawable)
                .error(errorDrawable)
                .into(t);
    }

    public static <T extends ImageView> void setImageUrl(Context context, String url, final ProgressBar progressBar, T t) {
        progressBar.setVisibility(VISIBLE);
        Glide.with(context)
                .load(url)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        progressBar.setVisibility(GONE);
                        return false;
                    }
                })
                .into(t);
    }

    public static <T extends ImageView> void setImageUrl(Context context, String url, final ProgressBar progressBar, int errorResourceId, T t) {
        progressBar.setVisibility(VISIBLE);
        Glide.with(context)
                .load(url)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        progressBar.setVisibility(GONE);
                        return false;
                    }
                })
                .error(errorResourceId)
                .into(t);
    }

    public static <T extends ImageView> void setImageUrl(Context context, String url, final ProgressBar progressBar, Drawable errorDrawable, T t) {
        progressBar.setVisibility(VISIBLE);
        Glide.with(context)
                .load(url)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        progressBar.setVisibility(GONE);
                        return false;
                    }
                })
                .error(errorDrawable)
                .into(t);
    }
}
