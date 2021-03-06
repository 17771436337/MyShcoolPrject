package com.example.a.myapplication.holder;

import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.MyOrderDetailChildAdapter;
import com.example.a.myapplication.bean.MyOrderDetailModel;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.UIUtils;
import com.example.a.myapplication.view.CustomListView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/1/3.
 */
public class MyOrderDetailGroupHolder extends BaseHolder<MyOrderDetailModel.OrderDetails> {

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

    MyOrderDetailChildAdapter adaper;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_shop_list, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void refreshUI(MyOrderDetailModel.OrderDetails data) {
        double price = 0;
        select.setVisibility(View.GONE);
        adaper = new MyOrderDetailChildAdapter(data.getShops());
        listView.setAdapter(adaper);
        Glide.with(UIUtils.getContext()).load(Config.hostImgString + data.getLogo()).asBitmap().centerCrop().into(new BitmapImageViewTarget(img) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(UIUtils.getContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                img.setImageDrawable(circularBitmapDrawable);
            }
        });

        text.setText(data.getBname());

        for (int i = 0; i < data.getShops().size(); i++) {
            double num = Double.parseDouble(data.getShops().get(i).getPrice());
            int sum = Integer.parseInt(data.getShops().get(i).getSum());
            price += (num * sum);
        }

        String text = "总价：" + price;
        SpannableStringBuilder style = new SpannableStringBuilder(text);
        style.setSpan(new ForegroundColorSpan(UIUtils.getContext().getResources().getColor(R.color.green_text)), 3, text.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //设置指定位置文字的颜色
        style.setSpan(new ForegroundColorSpan(UIUtils.getContext().getResources().getColor(R.color.black_transparency_text)), 0, 3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //设置指定位置文字的颜色
        priceTextView.setText(style);
        shopNum.setText(data.getShops().size() + "");
    }
}
