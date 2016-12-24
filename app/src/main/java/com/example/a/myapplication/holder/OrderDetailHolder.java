package com.example.a.myapplication.holder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.OrederDeatilsContentAdapter;
import com.example.a.myapplication.bean.OrderDetailModel;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.UIUtils;
import com.example.a.myapplication.view.CustomListView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/24.
 */
public class OrderDetailHolder extends BaseHolder<OrderDetailModel.Shop> {

    @InjectView(R.id.lv_product)
    protected CustomListView listView;

    @InjectView(R.id.title_img)
    protected ImageView img;

    @InjectView(R.id.tv_content)
    protected TextView text;

    @InjectView(R.id.cb_select)
    protected CheckBox select;

    @InjectView(R.id.shop_num)
    protected TextView shopNum;

    @InjectView(R.id.price_text)
    protected TextView priceTextView;

    OrederDeatilsContentAdapter adaper;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_shop_list, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void refreshUI(OrderDetailModel.Shop data) {
        double price = 0;
        adaper = new OrederDeatilsContentAdapter(data.getShops());
        listView.setAdapter(adaper);
        select.setVisibility(View.GONE);
        Glide.with(UIUtils.getContext()).load(Config.hostImgString + data.getLogo())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .crossFade().into(img);

        text.setText(data.getBname());

        for (int i = 0; i < data.getShops().size(); i++) {
            price += Double.parseDouble(data.getShops().get(i).getPrice());
        }
        priceTextView.setText("总价：" + price);
        shopNum.setText(data.getShops().size() + "");
    }
}
