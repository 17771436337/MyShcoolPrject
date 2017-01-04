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
public class MyOrderHolder extends BaseHolder<MyOrderModer.Order> {

    @InjectView(R.id.title_img)
    protected ImageView logo;

    @InjectView(R.id.name)
    protected TextView name;

    @InjectView(R.id.title_text)
    protected TextView brand;

    @InjectView(R.id.order)
    protected TextView order;

    @InjectView(R.id.img)
    protected ImageView img;

    @InjectView(R.id.price)
    protected TextView price;

    @InjectView(R.id.sum)
    protected TextView sum;

    @InjectView(R.id.time)
    protected TextView time;

    @InjectView(R.id.audit)
    protected TextView audit;

    @InjectView(R.id.order_monny)
    protected TextView totalprice;


    @Override
    protected View initView() {

        View view = View.inflate(UIUtils.getContext(), R.layout.item_myorder_list, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void refreshUI(MyOrderModer.Order data) {
        Glide.with(UIUtils.getContext()).load(Config.hostImgString + data.getImg())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.drawable.default_img)
                .crossFade().into(img);

        Glide.with(UIUtils.getContext()).load(Config.hostImgString + data.getLogo()).asBitmap().centerCrop().into(new BitmapImageViewTarget(logo) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(UIUtils.getContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                logo.setImageDrawable(circularBitmapDrawable);
            }
        });

        name.setText(data.getName());

        brand.setText(data.getBrand());

        order.setText("订单编号：" + data.getOrder());

        price.setText("￥" + data.getPrice());

        sum.setText("x" + data.getSum());


        totalprice.setText("￥" + data.getTotalprice());

        long l = Long.parseLong(data.getTime());
        Log.e("time", l + "");
//        time.setText(DateUtils.getShortTime(data.getTime()));
        time.setText(TimeUtils.getTime(l));

        Log.e("Audit", data.getAudit());
        switch (Integer.parseInt(data.getAudit())) {
            case 0://0待支付
                audit.setText("待支付");
                break;
            case 1://1已支付-待发货/
                audit.setText("待发货");
                break;
            case 2://2待收货，/
                audit.setText("待收货");
                break;
            case 3://3已完成/
                audit.setText("已完成");
                break;
        }


    }
}
