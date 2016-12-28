package com.example.a.myapplication.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.ExpandableListViewAdapter;
import com.example.a.myapplication.adapter.OrederAdapter;
import com.example.a.myapplication.bean.OrderDetailModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.lib.CustomExpandableListView;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.view.CustomListView;
import com.example.a.myapplication.view.TitleView1;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
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

    public static boolean isShow = true;

    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;


    @InjectView(R.id.ExpandableListView)
    protected CustomExpandableListView expandableListView;
    ExpandableListViewAdapter adapterExpandableListView;
    ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();

    @InjectView(R.id.price_text)
    protected TextView price;

    @InjectView(R.id.favorable)
    protected TextView favorable;

    @InjectView(R.id.freight)
    protected TextView freight;

    String id;
    public static int orderId;

    private double priceSum;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_orderdetails;
    }

    @Override
    protected void initView() {
        initTitle();

        list.add(new ArrayList<String>());
        list.get(0).add("aaa");
        expandableListView.setGroupIndicator(null);//去箭头
        adapterExpandableListView = new ExpandableListViewAdapter(this, list);
        expandableListView.setAdapter(adapterExpandableListView);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (expandableListView.isGroupExpanded(groupPosition)) {
                    parent.collapseGroup(groupPosition);
                    isShow = true;
                } else {
                    parent.expandGroup(groupPosition);
                    isShow = false;
                }
                return true;
            }
        });
//
    }

    @Override
    protected void initData() {
        id = getIntent().getExtras().getString("id");
        getData();

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

                if (TextUtils.isEmpty(shopModel.getE().get(0).getFreight())) {
                    freight.setText("￥0.0");
                } else {
                    freight.setText("￥" + shopModel.getE().get(0).getFreight());
                }

                orderId = shopModel.getE().get(0).getOrderid();
                priceSum = Double.parseDouble(shopModel.getE().get(0).getPriceSum());
                setTextPrice(0);
            } else {

            }

        }
    }

    @OnClick({R.id.yes})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.yes:

                if (orderId != 0) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("oid", orderId);
                    bundle.putString("orderPrice", priceSum + "");
                    CommonUtils.startIntent(this, ConfirmAnOrderActivity.class, bundle);
                }


                break;
        }
    }


    public void setTextPrice(double price) {
        priceSum = priceSum - price;
        this.price.setText("￥" + priceSum);
        favorable.setText("￥" + price);
    }

}
