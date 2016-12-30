package com.example.a.myapplication.holder;

import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.ScreenStyleModel;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.UIUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/28.
 */
public class ScreenStyleHolder extends BaseHolder<ScreenStyleModel.Brand> {
    @InjectView(R.id.icon)
    protected ImageView icon;

    @InjectView(R.id.text)
    protected TextView text;

    @InjectView(R.id.chexk)
    protected ImageView chexk;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_brand_list, null);
        ButterKnife.inject(this, view);
        return view;
    }


    @Override
    protected void refreshUI(ScreenStyleModel.Brand data) {

        if (TextUtils.isEmpty(data.getImg())) {
            icon.setImageResource(R.drawable.default_head);
        } else {

            Glide.with(UIUtils.getContext()).load(Config.hostImgString + data.getImg()).asBitmap().centerCrop().into(new BitmapImageViewTarget(icon) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(UIUtils.getContext().getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    icon.setImageDrawable(circularBitmapDrawable);
                }
            });

        }
        text.setText(data.getName());
        if (data.is()) {
            chexk.setVisibility(View.VISIBLE);
        } else {
            chexk.setVisibility(View.GONE);
        }
    }
}
