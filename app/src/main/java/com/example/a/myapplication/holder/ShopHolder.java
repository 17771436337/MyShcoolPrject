package com.example.a.myapplication.holder;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.a.myapplication.R;
import com.example.a.myapplication.activity.ShopActivity;
import com.example.a.myapplication.adapter.ContentAdapter;
import com.example.a.myapplication.adapter.ShopAdapter;
import com.example.a.myapplication.bean.ShopModel;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.UIUtils;
import com.example.a.myapplication.view.CustomListView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

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

    @InjectView(R.id.cb_select)
    protected CheckBox select;

    @InjectView(R.id.shop_num)
    protected TextView shopNum;

    @InjectView(R.id.price_text)
    protected TextView priceTextView;

    ContentAdapter adaper;

    ShopModel.Shop data;

    ShopAdapter shopAdapter;

    public ShopHolder(ShopAdapter shopAdapter) {
        this.shopAdapter = shopAdapter;
    }

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_shop_list, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void refreshUI(ShopModel.Shop data) {
        this.data = data;
        double price = 0;
        adaper = new ContentAdapter(data.getShops(), shopAdapter);
        listView.setAdapter(adaper);

        Glide.with(UIUtils.getContext()).load(Config.hostImgString + data.getLogo())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .crossFade().into(img);

        text.setText(data.getBname());

        ArrayList<Boolean> booleen = new ArrayList<>();
        for (int i = 0; i < data.getShops().size(); i++) {
            if (data.getShops().get(i).is()) {
                price += Double.parseDouble(data.getShops().get(i).getPrice());
                booleen.add(data.getShops().get(i).is());
            }
        }

        if (booleen.size() == data.getShops().size()) {
            select.setChecked(true);
        } else {
            select.setChecked(false);
        }

        priceTextView.setText("总价：" + price);
        shopNum.setText(booleen.size() + "");


    }

    @OnClick(R.id.cb_select)
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.cb_select:
                boolean is = select.isChecked();
                Log.e("dddddddddddddd", is + "");
                if (is) {

                    for (int i = 0; i < data.getShops().size(); i++) {
                        data.getShops().get(i).setIs(true);
                    }
                    select.setChecked(true);

                } else {

                    for (int i = 0; i < data.getShops().size(); i++) {
                        data.getShops().get(i).setIs(false);
                    }
                    select.setChecked(false);
                }

                notifyDataSetChanged();
                break;
        }


    }


    public interface IsCheckedGroup {

        void isChecked(ShopModel.Shop data);
    }

    /**
     * 数据刷新
     */
    public void notifyDataSetChanged() {
        shopAdapter.notifyDataSetChanged();
        adaper.notifyDataSetChanged();
        ShopActivity.shopActivity.isChecked(data);
    }
}

