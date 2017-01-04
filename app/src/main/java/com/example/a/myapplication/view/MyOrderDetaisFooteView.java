package com.example.a.myapplication.view;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.example.a.myapplication.BaseView;
import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.MyOrderDetailModel;
import com.example.a.myapplication.util.UIUtils;

import butterknife.InjectView;

/**
 * Created by Administrator on 2017/1/3.
 */
public class MyOrderDetaisFooteView extends BaseView {

    @InjectView(R.id.freight)
    protected TextView freight;

    @InjectView(R.id.favprice)
    protected TextView favprice;

    @InjectView(R.id.coupon)
    protected TextView coupon;

    @InjectView(R.id.priceSum)
    protected TextView priceSum;

    @InjectView(R.id.order)
    protected TextView order;

    @InjectView(R.id.time)
    protected TextView time;

    public MyOrderDetaisFooteView(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.view_myorderdetails;
    }

    @Override
    protected void initView() {

    }

    /**
     * 设置数据
     */
    public void setData(MyOrderDetailModel.EBean data) {
        String text = "订单实付：￥" + data.getPriceSum();
        SpannableStringBuilder style = new SpannableStringBuilder(text);
        style.setSpan(new ForegroundColorSpan(UIUtils.getContext().getResources().getColor(R.color.green_text)), 5, text.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //设置指定位置文字的颜色
        style.setSpan(new ForegroundColorSpan(UIUtils.getContext().getResources().getColor(R.color.black_transparency_text)), 0, 5, Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //设置指定位置文字的颜色
        priceSum.setText(style);

        time.setText("创建时间：" + data.getTime());

        favprice.setText("-￥" + data.getFavprice());

        freight.setText("￥" + data.getFreight());
        order.setText("订单编号：" + data.getOrder());


        if (!TextUtils.isEmpty(data.getCoupon())) {
            coupon.setText(data.getCoupon());
        } else {
            coupon.setText("无");
        }
    }
}
