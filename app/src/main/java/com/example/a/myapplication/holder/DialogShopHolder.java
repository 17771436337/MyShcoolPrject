package com.example.a.myapplication.holder;

import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.MyOrderModer;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.TimeUtils;
import com.example.a.myapplication.util.UIUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/8.
 */
public class DialogShopHolder extends BaseHolder<String> {

    @InjectView(R.id.dialog_shop_s)
    public  TextView logo;
    public int  a;
    public DialogShopHolder(int a) {
        this.a=a;
    }

    @Override
    protected View initView() {

        View view = View.inflate(UIUtils.getContext(), R.layout.dialog_tv, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void refreshUI(String data) {
        if(0==a){
            logo.setBackgroundColor(UIUtils.getColor(R.color.font_color));
            logo.setTextColor(UIUtils.getColor(R.color.white));
        }
        logo.setText(data);
    }
}
