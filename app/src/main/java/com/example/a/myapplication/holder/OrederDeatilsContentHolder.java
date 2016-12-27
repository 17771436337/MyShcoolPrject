package com.example.a.myapplication.holder;

import android.graphics.Paint;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.OrderDetailModel;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.UIUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/24.
 */
public class OrederDeatilsContentHolder extends BaseHolder<OrderDetailModel.Shop.ShopsBean> {

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

    @InjectView(R.id.cb_select)
    protected CheckBox select;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.itme_shop_content_list, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void refreshUI(OrderDetailModel.Shop.ShopsBean data) {
        Glide.with(UIUtils.getContext()).load(Config.hostImgString + data.getImg())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .crossFade().into(img);
        select.setVisibility(View.GONE);

        name.setText(data.getName());
        color.setText("款式：" + data.getColor());
        price.setText("￥" + data.getPrice());
        orprice.setText("￥" + data.getOrprice());
        size.setText("尺寸：" + data.getSize());
        sum.setText("x" + data.getSum());
        orprice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);    // 设置中划线并加清晰 
    }
}
