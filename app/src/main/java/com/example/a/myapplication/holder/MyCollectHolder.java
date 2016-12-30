package com.example.a.myapplication.holder;

import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.MyCollecModer;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.ScreenUtils;
import com.example.a.myapplication.util.UIUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/7.
 */
public class MyCollectHolder extends BaseHolder<MyCollecModer.Colledt> {

    @InjectView(R.id.img)
    protected ImageView img;

    @InjectView(R.id.head)
    protected ImageView head;

    @InjectView(R.id.shop_num)
    protected TextView shop_num;

    @InjectView(R.id.name)
    protected TextView name;

    @InjectView(R.id.r1)
    protected RelativeLayout layout;

    RelativeLayout.LayoutParams layoutParams;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_mycollect_list, null);

        layoutParams = new RelativeLayout.LayoutParams((ScreenUtils.getScreenW() - ScreenUtils.dip2px(UIUtils.getContext(), 15)) / 3,
                (ScreenUtils.getScreenW() - ScreenUtils.dip2px(UIUtils.getContext(), 15)) / 3 + ScreenUtils.px2dip(UIUtils.getContext(), 70));
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void refreshUI(MyCollecModer.Colledt data) {

        layout.setLayoutParams(layoutParams);

        if (TextUtils.isEmpty(data.getHead())) {
            head.setVisibility(View.GONE);
        } else {
            head.setVisibility(View.VISIBLE);
            Glide.with(UIUtils.getContext()).load(Config.hostImgString + data.getImg()).asBitmap().centerCrop().into(new BitmapImageViewTarget(head) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(UIUtils.getContext().getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    head.setImageDrawable(circularBitmapDrawable);
                }
            });
        }
        //-------
        Glide.with(UIUtils.getContext()).load(Config.hostImgString + data.getImg())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.drawable.default_img)
                .crossFade().into(img);
        if (TextUtils.isEmpty(data.getName())) {
            name.setVisibility(View.GONE);
        } else {
            name.setVisibility(View.VISIBLE);
            name.setText(data.getName());
        }
        shop_num.setText(data.getCollection());

    }
}
