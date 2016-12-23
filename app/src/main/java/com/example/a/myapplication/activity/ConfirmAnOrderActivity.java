package com.example.a.myapplication.activity;

import android.view.View;
import android.widget.RelativeLayout;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.view.TitleView1;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/14.
 * 确认订单
 */
public class ConfirmAnOrderActivity extends BaseActivity {


    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_confirm_an_order;
    }

    @Override
    protected void initView() {
        initTitle();
    }

    @Override
    protected void initData() {

    }

    /**
     * 标题初始化
     */
    private void initTitle() {
        TitleView1 view = new TitleView1(this);
        titleView.addView(view.getView());
        view.setTitleText("确认订单", "");
    }

    @OnClick({R.id.location_layout, R.id.pay_text, R.id.qr_layout})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.location_layout:
                CommonUtils.startIntent(this, LocationActivity.class);
                break;
            case R.id.pay_text://支付
                CommonUtils.startIntent(this, ConfirmAnOrderActivity.class);
                break;
            case R.id.qr_layout:  //二维码支付
                CommonUtils.startIntent(this, QRPayActivity.class);

                break;
        }
    }
}
