package com.example.a.myapplication.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.MyOrderDetailAdapter;
import com.example.a.myapplication.bean.BaseModel;
import com.example.a.myapplication.bean.MyOrderDetailModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.Preference;
import com.example.a.myapplication.view.CustomListView;
import com.example.a.myapplication.view.MyOrderDetaisFooteView;
import com.example.a.myapplication.view.TitleView1;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/1/3.
 * 我的订单的订单详情
 */
public class MyOrderDetailsActivity extends BaseActivity {
    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    @InjectView(R.id.listView)
    protected CustomListView listView;
    MyOrderDetailModel model = new MyOrderDetailModel();
    MyOrderDetailAdapter adapter;
    MyOrderDetaisFooteView myOrderDetaisFooteView;


    @InjectView(R.id.cancel)
    protected TextView cancel;//取消订单

    @InjectView(R.id.goPay)
    protected TextView goPay;//去支付

    @InjectView(R.id.yesPay)
    protected TextView yesPay;//确认支付


    @InjectView(R.id.location_layout)
    protected RelativeLayout location_layout;


    @InjectView(R.id.name)
    protected TextView name;

    @InjectView(R.id.phone)
    protected TextView phone;

    @InjectView(R.id.address)
    protected TextView address;

    @InjectView(R.id.state)
    protected TextView state;


    private String id;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_myoderdetails;
    }

    @Override
    protected void initView() {

        id = getIntent().getExtras().getString("id");

        initTitle();
        myOrderDetaisFooteView = new MyOrderDetaisFooteView(this);
        listView.addFooterView(myOrderDetaisFooteView.getView());
        adapter = new MyOrderDetailAdapter(model.getO());
        listView.setAdapter(adapter);
        getData();

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
        view.setTitleText("订单详情", "");
    }


    @OnClick({R.id.goPay, R.id.yesPay, R.id.cancel})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.goPay:
                if (model != null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("oid", Integer.parseInt(id));
                    bundle.putString("orderPrice", model.getE().getPriceSum() + "");
                    CommonUtils.startIntent(this, ConfirmAnOrderActivity.class, bundle);
                }
                break;
            case R.id.yesPay:

                Map<String, String> par = CommonUtils.getMapParm();
                par.put("orderid", id);
                OkHttpUtil.getInstance().addRequestPost(Config.sureGet, par, new OkHttpUtil.HttpCallBack<BaseModel>() {

                    @Override
                    public void onSuccss(BaseModel baseModel) {
                        EventBus.getDefault().post(baseModel.getM());
                    }

                    @Override
                    public void onFailure(String error) {

                    }
                });

                break;
            case R.id.cancel: //取消
                Map<String, String> parm = CommonUtils.getMapParm();
                parm.put("id", id);
                parm.put("uid", Preference.get(Config.ID, ""));
                OkHttpUtil.getInstance().addRequestPost(Config.cancel, parm, new OkHttpUtil.HttpCallBack<BaseModel>() {

                    @Override
                    public void onSuccss(BaseModel baseModel) {
                        EventBus.getDefault().post(baseModel.getM());
                    }

                    @Override
                    public void onFailure(String error) {

                    }
                });
                break;
        }
    }


    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);
        if (obj instanceof MyOrderDetailModel) {
            MyOrderDetailModel myOrderDetailModel = (MyOrderDetailModel) obj;
            if (myOrderDetailModel.getC() == 1) {
                model = myOrderDetailModel;
                adapter.getmDatas().clear();
                adapter.addData(myOrderDetailModel.getO());
                adapter.notifyDataSetChanged();
                myOrderDetaisFooteView.setData(myOrderDetailModel.getE());
                if (TextUtils.isEmpty(myOrderDetailModel.getE().getCity()) || //城市为空
                        TextUtils.isEmpty(myOrderDetailModel.getE().getProvince()) ||//省份为空  /
                        TextUtils.isEmpty(myOrderDetailModel.getE().getAddress()) ||//详情为空/
                        TextUtils.isEmpty(myOrderDetailModel.getE().getArea())) //市区为空
                {

                    location_layout.setVisibility(View.GONE);

                } else {
                    name.setText("姓名：" + myOrderDetailModel.getE().getName());
                    phone.setText("电话：" + myOrderDetailModel.getE().getPhone());
                    address.setText(myOrderDetailModel.getE().getProvince() + "    " + myOrderDetailModel.getE().getCity() + "     "
                            + myOrderDetailModel.getE().getArea() +
                            "\r\n" + myOrderDetailModel.getE().getAddress());
                }

                switch (myOrderDetailModel.getE().getAudit()) {
                    case "0":  // 0待支付，1已支付-待发货，2待收货，3已完成
                        state.setText("待支付");
                        state.setTextColor(this.getResources().getColor(R.color.red_text));

                        cancel.setVisibility(View.VISIBLE);
                        goPay.setVisibility(View.VISIBLE);
                        yesPay.setVisibility(View.GONE);
                        break;
                    case "1":  // 0待支付，1已支付-待发货，2待收货，3已完成
                        state.setText("待发货");
                        state.setTextColor(this.getResources().getColor(R.color.black_text));

                        cancel.setVisibility(View.VISIBLE);
                        goPay.setVisibility(View.VISIBLE);
                        yesPay.setVisibility(View.GONE);
                        break;
                    case "2":  // 0待支付，1已支付-待发货，2待收货，3已完成
                        state.setText("待收货");
                        state.setTextColor(this.getResources().getColor(R.color.black_text));

                        cancel.setVisibility(View.GONE);
                        goPay.setVisibility(View.GONE);
                        yesPay.setVisibility(View.VISIBLE);
                        break;
                    case "3":  // 0待支付，1已支付-待发货，2待收货，3已完成
                        state.setText("已完成");
                        state.setTextColor(this.getResources().getColor(R.color.black_text));

                        cancel.setVisibility(View.GONE);
                        goPay.setVisibility(View.GONE);
                        yesPay.setVisibility(View.GONE);
                        break;
                }


            } else {
                Toast.makeText(this, myOrderDetailModel.getM() + "", Toast.LENGTH_SHORT).show();
            }
        }

        if (obj instanceof String) {
            String baseModel = (String) obj;
            Toast.makeText(this, baseModel + "", Toast.LENGTH_SHORT).show();


        }
    }

    public void getData() {

        Map<String, String> par = CommonUtils.getMapParm();
        par.put("id", id);
        par.put("uid", Preference.get(Config.ID, ""));
        OkHttpUtil.getInstance().addRequestPost(Config.details, par, new OkHttpUtil.HttpCallBack<MyOrderDetailModel>() {

            @Override
            public void onSuccss(MyOrderDetailModel myOrderDetailModel) {
                EventBus.getDefault().post(myOrderDetailModel);
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }
}
