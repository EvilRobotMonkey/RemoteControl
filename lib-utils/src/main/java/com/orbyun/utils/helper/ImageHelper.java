package com.orbyun.utils.helper;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class ImageHelper {
    private static ImageHelper imageHelper = new ImageHelper();

    public static ImageHelper getInstance() {
        return imageHelper;
    }

    public void showImage(ImageView imageView, String url) {
        if (TextUtils.isEmpty(url))
            return;
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);

    }

    public void showImage(ImageView imageView, int res) {
        if (res == 0)
            return;
        Glide.with(imageView.getContext())
                .load(res)
                .into(imageView);

    }
}
