package com.example.a.myapplication.activity;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.OrederAdapter;
import com.example.a.myapplication.bean.OrderDetailModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.view.CustomListView;
import com.example.a.myapplication.view.FavorableNotView;
import com.example.a.myapplication.view.FavorableView;
import com.example.a.myapplication.view.TitleView1;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/15.
 */
public class OrederDetailsActivity extends BaseActivity {

    @InjectView(R.id.listView)
    protected CustomListView listView;
    OrderDetailModel model = new OrderDetailModel();
    OrederAdapter adapter;

    private boolean isShow = true;

    @InjectView(R.id.rl1)
    protected RelativeLayout linearLayout;

    @InjectView(R.id.arrow)
    protected ImageView arrow;

    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;
    FavorableNotView favorableNotView;
    FavorableView favorableView;

    String id;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_orderdetails;
    }

    @Override
    protected void initView() {
        initTitle();
//        linearLayout.setVisibility(View.GONE);
//        setShow(isShow);


    }

    @Override
    protected void initData() {
//        id = getIntent().getExtras().getString("id");
//        getData();

    }

    /**
     * 标题初始化
     */
    private void initTitle() {
        TitleView1 view = new TitleView1(this);
        titleView.addView(view.getView());
        view.setTitleText("订单详情", "编辑");
    }

    private void getData() {

        Map<String, String> par = CommonUtils.getMapParm();
        par.put("uid", 2 + "");

        par.put("cart_ids", id);

        OkHttpUtil.getInstance().addRequestPost(Config.submit, par, new OkHttpUtil.HttpCallBack<OrderDetailModel>() {

            @Override
            public void onSuccss(OrderDetailModel shopModel) {
                EventBus.getDefault().post(shopModel);
            }


            @Override
            public void onFailure(String error) {

            }
        });

    }


    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);

        if (obj instanceof OrderDetailModel) {
            OrderDetailModel shopModel = (OrderDetailModel) obj;

            if (shopModel.getC() == 1) {
                model = shopModel;
                adapter = new OrederAdapter(shopModel.getO());
                listView.setAdapter(adapter);

                Log.e("test", shopModel.getO().toString());
            } else {

            }

        }
    }

    @OnClick({R.id.favorable_layout, R.id.yes})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.favorable_layout://
                setShow(isShow);
                break;
            case R.id.yes:

                CommonUtils.startIntent(this, ConfirmAnOrderActivity.class);
                break;
        }
    }


    /**
     * 显示优惠
     */
    private void setShow(boolean isShow) {
        if (isShow) {
            arrow.setImageResource(R.drawable.icon_up_arrow);
            linearLayout.setVisibility(View.VISIBLE);
            this.isShow = !isShow;
        } else {
            arrow.setImageResource(R.drawable.icon_down_arrow);
            linearLayout.setVisibility(View.GONE);
            this.isShow = !isShow;
        }
    }
}
