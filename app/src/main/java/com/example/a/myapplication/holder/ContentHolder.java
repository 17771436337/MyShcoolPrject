package com.example.a.myapplication.holder;

import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.ShopModel;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.UIUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/8.
 */
public class ContentHolder extends BaseHolder<ShopModel.Shop.Content> {

    @InjectView(R.id.img)
    protected ImageView img;

    @InjectView(R.id.name)
    protected TextView name;

    @InjectView(R.id.color)
    protected TextView color;

    @InjectView(R.id.size)
    protected TextView size;

    @InjectView(R.id.price)
    protected TextView price;

    @InjectView(R.id.orprice)
    protected TextView orprice;

    @InjectView(R.id.sum)
    protected TextView sum;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.itme_shop_content_list, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void refreshUI(ShopModel.Shop.Content data) {
        Glide.with(UIUtils.getContext()).load(Config.hostImgString + data.getImg())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .crossFade().into(img);

        name.setText(data.getName());
        color.setText(data.getColor());
        price.setText(data.getPrice());
        orprice.setText(data.getOrprice());
        size.setText(data.getSize());
        sum.setText(data.getSum());
        orprice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);    // 设置中划线并加清晰 

    }
}
