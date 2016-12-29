package com.example.a.myapplication.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.ScreenCategoryOneModel;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.UIUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/12.
 */
public class ScreenCategoryOneHolder extends BaseHolder<ScreenCategoryOneModel.Category> {

    @InjectView(R.id.icon)
    protected ImageView icon;

    @InjectView(R.id.text)
    protected TextView text;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_categoryone, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void refreshUI(ScreenCategoryOneModel.Category data) {

        Glide.with(UIUtils.getContext()).load(Config.hostImgString + data.getImg())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.drawable.default_img)
                .crossFade().into(icon);

        text.setText(data.getName());

    }
}
