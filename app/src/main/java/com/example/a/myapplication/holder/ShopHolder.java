package com.example.a.myapplication.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.ContentAdapter;
import com.example.a.myapplication.bean.ShopModel;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.UIUtils;
import com.example.a.myapplication.view.CustomListView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/8.
 */
public class ShopHolder extends BaseHolder<ShopModel.Shop> {

    @InjectView(R.id.lv_product)
    protected CustomListView listView;

    @InjectView(R.id.title_img)
    protected ImageView img;

    @InjectView(R.id.tv_content)
    protected TextView text;

    ContentAdapter adaper;


    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_shop_list, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void refreshUI(ShopModel.Shop data) {
        adaper = new ContentAdapter(data.getShops());
        listView.setAdapter(adaper);

        Glide.with(UIUtils.getContext()).load(Config.hostImgString + data.getLogo())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .crossFade().into(img);

        text.setText(data.getBname());
    }
}
