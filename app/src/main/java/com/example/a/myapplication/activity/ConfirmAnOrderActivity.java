package com.example.a.myapplication.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.BaseModel;
import com.example.a.myapplication.bean.LocationMoldel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.Preference;
import com.example.a.myapplication.view.TitleView1;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/14.
 * 确认订单
 */
public class ConfirmAnOrderActivity extends BaseActivity {


    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;


    @InjectView(R.id.pai_chexkbox)
    protected CheckBox paiChexkBox;

    @InjectView(R.id.wx_chexkbox)
    protected CheckBox wxChexkBox;


    @InjectView(R.id.qr_chexkbox)
    protected CheckBox qrChexkBox;


    @InjectView(R.id.unlimited)
    protected CheckBox unlimited;

    @InjectView(R.id.workday)
    protected CheckBox workday;


    @InjectView(R.id.holiday)
    protected CheckBox holiday;


    @InjectView(R.id.text)
    protected TextView textView;

    @InjectView(R.id.location_linear)
    protected LinearLayout location_layout;

    @InjectView(R.id.name)
    protected TextView name;

    @InjectView(R.id.phone)
    protected TextView phone;

    @InjectView(R.id.address)
    protected TextView address;


    private String aid; //支付地址

    private String oid;//订单id

    private final int REQUESTCODE = 1;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_confirm_an_order;
    }

    @Override
    protected void initView() {
        initTitle();

        if (TextUtils.isEmpty(aid)) {
            textView.setVisibility(View.VISIBLE);
            location_layout.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initData() {
//        int oid = getIntent().getExtras().getInt("oid");
//        this.oid = oid + "";
    }

    /**
     * 标题初始化
     */
    private void initTitle() {
        TitleView1 view = new TitleView1(this);
        titleView.addView(view.getView());
        view.setTitleText("确认订单", "");
    }

    @OnClick({R.id.location_layout, R.id.pay_text, R.id.pai_layout, R.id.wx_layout,
            R.id.qr_layout, R.id.unlimited_layout, R.id.workday_layout, R.id.holiday_layout})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.location_layout://地址/
                CommonUtils.startIntent(this, LocationActivity.class, REQUESTCODE);
                break;
            case R.id.pay_text://支付
//                order();

                break;

            case R.id.pai_layout: //支付宝支付
                qrChexkBox.setChecked(false);
                wxChexkBox.setChecked(false);
                paiChexkBox.setChecked(true);

                break;

            case R.id.wx_layout://微信支付
                qrChexkBox.setChecked(false);
                wxChexkBox.setChecked(true);
                paiChexkBox.setChecked(false);

                break;

            case R.id.qr_layout:  //二维码支付
//

                qrChexkBox.setChecked(true);
                wxChexkBox.setChecked(false);
                paiChexkBox.setChecked(false);
                break;

            case R.id.unlimited_layout://收货世间不限

                unlimited.setChecked(true);
                workday.setChecked(false);
                holiday.setChecked(false);
                break;
            case R.id.workday_layout:  //工作日收货
                unlimited.setChecked(false);
                workday.setChecked(true);
                holiday.setChecked(false);
                break;
            case R.id.holiday_layout: //节假日
                unlimited.setChecked(false);
                workday.setChecked(false);
                holiday.setChecked(true);
                break;
        }
    }

    /**
     * 下单
     */
    private void order() {

        String pay = new String();//订单类型

        if (qrChexkBox.isChecked()) {//二维码支付
            pay = 4 + "";
            CommonUtils.startIntent(this, QRPayActivity.class);
        }


        if (wxChexkBox.isChecked()) {//微信支付
            pay = 2 + "";
        }

        if (paiChexkBox.isChecked()) {//支付宝支付
            pay = 1 + "";
        }


        //------------------------------------------

        String ttype = new String();
        if (unlimited.isChecked()) {//不限
            ttype = 1 + "";
        }

        if (workday.isChecked()) {//工作日
            ttype = 2 + "";
        }

        if (holiday.isChecked()) {//节假日
            ttype = 3 + "";
        }


        //------------------------------
        if (TextUtils.isEmpty(pay)) {
            Toast.makeText(this, "请选择支付方式", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(aid)) {
            Toast.makeText(this, "请选择收货地址", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pay)) {
            Toast.makeText(this, "请选择收货时间", Toast.LENGTH_SHORT).show();
            return;
        }


        Map<String, String> par = CommonUtils.getMapParm();
        par.put("uid", Preference.get(Config.ID, ""));
        par.put("oid", oid);
        par.put("pay", pay);
        par.put("aid", aid);
        par.put("ttype", ttype);


        OkHttpUtil.getInstance().addRequestPost(Config.pay, par, new OkHttpUtil.HttpCallBack<BaseModel>() {

            @Override
            public void onSuccss(BaseModel baseModel) {
                EventBus.getDefault().post(baseModel);
            }

            @Override
            public void onFailure(String error) {

            }
        });

    }

    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);
        if (obj instanceof BaseModel) {
            BaseModel baseModel = (BaseModel) obj;
            if (baseModel.getC() == 1) {
                Toast.makeText(this, baseModel.getM() + "", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, baseModel.getM() + "", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == REQUESTCODE && resultCode == resultCcode) {
            LocationMoldel.Location location = (LocationMoldel.Location) data.getSerializableExtra("data");
            Log.e("我回来了", "我154554455454454545455555回来了");
            if (location != null) {
                textView.setVisibility(View.GONE);
                location_layout.setVisibility(View.VISIBLE);
                aid = location.getId();
                name.setText("姓名：" + location.getName());
                phone.setText("电话：" + location.getPhone());
                address.setText(location.getProvince() + "    " + location.getCity() + "     "
                        + location.getArea() +
                        "\r\n" + location.getAddress());
            } else {
                textView.setVisibility(View.VISIBLE);
                location_layout.setVisibility(View.GONE);
            }
        }


        Log.e("我回来了", requestCode + "我i回来了" + resultCode);


    }
}
