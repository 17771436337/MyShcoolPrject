package com.example.a.myapplication.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.ExpandableListViewAdapter;
import com.example.a.myapplication.adapter.OrederAdapter;
import com.example.a.myapplication.bean.BaseModel;
import com.example.a.myapplication.bean.OrderDetailModel;
import com.example.a.myapplication.bean.UserCouponModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.lib.CustomExpandableListView;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.Preference;
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

    public static double priceSum = 0.00;

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

    String string;

    @Override
    protected void initData() {
        id = getIntent().getExtras().getString("id");

        adapterExpandableListView.addTextChange(new ExpandableListViewAdapter.AddTextChange() {
            @Override
            public void addTextChangedListener(CharSequence s) {
                string = s.toString();

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    /**
     * 标题初始化
     */
    private void initTitle() {
        TitleView1 view = new TitleView1(this);
        titleView.addView(view.getView());
        view.setTitleText("订单详情", "");
    }

    private void getData() {

        Map<String, String> par = CommonUtils.getMapParm();
        par.put("uid", Preference.get(Config.ID, ""));

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


        if (obj instanceof UserCouponModel) {
            userCouponModel = (UserCouponModel) obj;
            if (userCouponModel.getC() == 1) {
                if (userCouponModel.getO() != null) {
                    double v = Double.parseDouble(userCouponModel.getO().getPrice());
                    setTextPrice(v);
                    adapterExpandableListView.notifyDataSetChanged();
                }

                if (orderId != 0) {
                    if (userCouponModel != null && userCouponModel.getC() == 1) {
                        sureOrder();
                    } else if (userCouponModel == null) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("oid", orderId);
                        bundle.putString("orderPrice", priceSum + "");
                        CommonUtils.startIntent(this, ConfirmAnOrderActivity.class, bundle);

                    } else {
                        Toast.makeText(OrederDetailsActivity.this, userCouponModel.getM() + "", Toast.LENGTH_SHORT).show();
                    }
                }

            } else {
                Toast.makeText(OrederDetailsActivity.this, userCouponModel.getM() + "", Toast.LENGTH_SHORT).show();
            }
        }

        if (obj instanceof String) {
            Bundle bundle = new Bundle();
            bundle.putInt("oid", orderId);
            bundle.putString("orderPrice", priceSum + "");
            CommonUtils.startIntent(this, ConfirmAnOrderActivity.class, bundle);
        }
    }

    UserCouponModel userCouponModel;

    @OnClick({R.id.yes})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.yes:

                Map<String, String> par = CommonUtils.getMapParm();
                par.put("uid", Preference.get(Config.ID, ""));
                if (!TextUtils.isEmpty(string)) {
                    par.put("coupon_no", string);
                }
                par.put("oid", orderId + "");
                OkHttpUtil.getInstance().addRequestPost(Config.useCoupon, par, new OkHttpUtil.HttpCallBack<UserCouponModel>() {

                    @Override
                    public void onSuccss(UserCouponModel userCouponModel) {
                        EventBus.getDefault().post(userCouponModel);

                    }

                    @Override
                    public void onFailure(String error) {

                    }
                });


                break;
        }

    }


    public void setTextPrice(double price) {
        priceSum = priceSum - price;
        this.price.setText("￥" + priceSum);
        favorable.setText("-￥" + price);
        adapterExpandableListView.price = price + "";
        adapterExpandableListView.notifyDataSetChanged();

    }


    private void sureOrder() {
        Map<String, String> par = CommonUtils.getMapParm();
        par.put("uid", Config.ID);
        par.put("oid", orderId + "");
        par.put("fid", userCouponModel.getO().getId());
        par.put("favprice", userCouponModel.getO().getPrice());
        OkHttpUtil.getInstance().addRequestPost(Config.sureOrder, par, new OkHttpUtil.HttpCallBack<BaseModel>() {

            @Override
            public void onSuccss(BaseModel baseModel) {

                EventBus.getDefault().post("onSuccss");
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }
}
