package com.example.a.myapplication.activity;

import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.BaseModel;
import com.example.a.myapplication.bean.OrderLastFour;
import com.example.a.myapplication.bean.PayModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.Download;
import com.example.a.myapplication.util.Preference;
import com.example.a.myapplication.view.TitleView1;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Created by Administrator on 2016/12/15.
 */
public class QRPayActivity extends BaseActivity {

    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    @InjectView(R.id.order_text)
    protected TextView orderTextView;

    @InjectView(R.id.api_text)
    protected EditText paynumEditText;

    @InjectView(R.id.qr_img)
    protected ImageView img;
    private String oid;
    private String pay;
    private String aid;
    private String ttype;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_qrpay;
    }

    @Override
    protected void initView() {
        initTitle();
        Glide.with(this)
                .load(Config.QR_IMG)
                .asBitmap()
                .placeholder(R.drawable.pic_gray_bg)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img);


    }

    @Override
    protected void initData() {

        oid = getIntent().getExtras().getString("oid");
        pay = getIntent().getExtras().getString("pay");
        aid = getIntent().getExtras().getString("aid");
        ttype = getIntent().getExtras().getString("ttype");
        getOrderLastFour();
    }

    /**
     * 标题初始化
     */
    private void initTitle() {
        TitleView1 view = new TitleView1(this);
        titleView.addView(view.getView());
        view.setTitleText("支付订单", "");
    }


    @OnLongClick(R.id.qr_img)
    protected boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.qr_img:

                final String path = Environment.getExternalStorageDirectory()
                        + File.separator + "ysb/img/";
                Bitmap bitmap = img.getDrawingCache();


                new Download(Config.QR_IMG, path, "pai.jpg").doDownLoad(new Download.BaseCallListener() {
                    @Override
                    public void onSuccess(String pResponse) {
                        EventBus.getDefault().post(path);
//
                    }

                    @Override
                    public void onFail(String pResponse) {
//
                        EventBus.getDefault().post("onFail");
                    }
                });

                break;
        }
        // return true;        // Click事件被onLongClick处理掉
        // return false;    // Click事件不被onLongClick处理，后续仍会触发onClick事件
        return true;

    }

    @OnClick(R.id.yes)
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.yes: //确定

                getData();
                break;
        }
    }


    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);


        if (obj instanceof String) {//图片下载

            String str = (String) obj;
            if (str.equals("onFail")) {//图片下载成功/
                Toast.makeText(QRPayActivity.this, "保存失败", Toast.LENGTH_SHORT).show();

            } else if (str.equals("onSuccss")) {
                pay();
            } else {//图片下载失败
                Toast.makeText(QRPayActivity.this, "保存成功:" + str + "pai.jpg", Toast.LENGTH_SHORT).show();
            }

        }


//        if (obj instanceof BaseModel) {//确定按钮
//            BaseModel baseModel = (BaseModel) obj;
//            if (baseModel.getC() == 1) {
//
//            } else {
//                Toast.makeText(this, baseModel.getM() + "", Toast.LENGTH_SHORT).show();
//            }
//        }

        if (obj instanceof OrderLastFour) { //获取订单后四位
            OrderLastFour orderLastFour = (OrderLastFour) obj;
            if (orderLastFour.getC() == 1) {
                orderTextView.setText(orderLastFour.getO().getOrder());
            } else {
                Toast.makeText(this, orderLastFour.getM() + "", Toast.LENGTH_SHORT).show();
            }

        }

        if (obj instanceof PayModel) {//支付
            PayModel payModel = (PayModel) obj;
            Toast.makeText(this, payModel.getM() + "", Toast.LENGTH_SHORT).show();
            if (payModel.getC() == 1) {
                CommonUtils.startIntent(this, MyOrderActivity.class);
                finish();
            } else {

            }

        }


    }


    /**
     * 获取订单后四位
     */
    public void getOrderLastFour() {
        Map<String, String> par = CommonUtils.getMapParm();
        par.put("uid", Preference.get(Config.ID, ""));
        par.put("oid", oid);
        OkHttpUtil.getInstance().addRequestPost(Config.getOrderLastFour, par, new OkHttpUtil.HttpCallBack<OrderLastFour>() {

            @Override
            public void onSuccss(OrderLastFour orderLastFour) {
                EventBus.getDefault().post(orderLastFour);
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    /**
     * 单击确定按钮
     */
    private void getData() {
        String order = orderTextView.getText().toString().trim();

        String paynum = paynumEditText.getText().toString().trim();


        if (TextUtils.isEmpty(paynum) || paynum.length() < 11) {
            Toast.makeText(this, "请输入支付宝账号", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, String> par = new HashMap<String, String>();
        par.put("uid", "2");
        par.put("order", order);
        par.put("paynum", paynum);
        OkHttpUtil.getInstance().addRequestPost(Config.addPayLog, par, new OkHttpUtil.HttpCallBack<BaseModel>() {

            @Override
            public void onSuccss(BaseModel baseModel) {
                if (baseModel.getC() == 1) {
                    EventBus.getDefault().post("onSuccss");
                }

            }

            @Override
            public void onFailure(String error) {

            }
        });
    }


    /**
     * 支付接口
     */
    private void pay() {
        Map<String, String> par = CommonUtils.getMapParm();
        par.put("uid", "2");
        par.put("oid", oid);
        par.put("pay", pay);
        par.put("aid", aid);
        par.put("ttype", ttype);


        OkHttpUtil.getInstance().addRequestPost(Config.pay, par, new OkHttpUtil.HttpCallBack<PayModel>() {

            @Override
            public void onSuccss(PayModel baseModel) {

                EventBus.getDefault().post(baseModel);
            }

            @Override
            public void onFailure(String error) {

            }
        });

    }
}
