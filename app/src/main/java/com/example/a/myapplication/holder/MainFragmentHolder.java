package com.example.a.myapplication.holder;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.a.myapplication.R;
import com.example.a.myapplication.activity.RecommendListActivity;
import com.example.a.myapplication.bean.MainFragmentModel;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.ScreenUtils;
import com.example.a.myapplication.util.UIUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/27.
 */
public class MainFragmentHolder extends BaseHolder<MainFragmentModel.OBean> {

    @InjectView(R.id.r1)
    protected RelativeLayout r1;

    @InjectView(R.id.text)
    protected TextView mTextView;

    @InjectView(R.id.img)
    protected ImageView img;

    RelativeLayout.LayoutParams layoutParams;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_main_list, null);
        layoutParams = new RelativeLayout.LayoutParams(ScreenUtils.getScreenW(), ScreenUtils.getScreenH() / 3 * 2);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void refreshUI(final MainFragmentModel.OBean data) {
        r1.setLayoutParams(layoutParams);
        mTextView.setText(data.getName());
        Glide.with(UIUtils.getContext()).load(Config.hostImgString + data.getImg())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.drawable.default_main)
                .crossFade().into(img);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("name", data.getName());
                bundle.putString("id", data.getId());
                CommonUtils.startIntent(UIUtils.getContext(), RecommendListActivity.class, bundle);
            }
        });
    }
}
