package com.example.a.myapplication.activity;

import android.graphics.Bitmap;
import android.os.Environment;
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
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.Preference;
import com.example.a.myapplication.view.TitleView1;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.InjectView;
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

    @Override
    protected int getLayoutID() {
        return R.layout.activity_qrpay;
    }

    @Override
    protected void initView() {
        initTitle();
        Glide.with(this)
                .load("http://115.28.94.239/wardrobe/code/wardrode/Public/Uploads/images/paycode.png")
                .asBitmap()
                .placeholder(R.drawable.pic_gray_bg)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img);


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
        view.setTitleText("支付订单", "");
    }


    @OnLongClick(R.id.qr_img)
    protected boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.qr_img:

                String path = Environment.getExternalStorageDirectory()
                        + File.separator + "ysb/img/";
                Bitmap bitmap = img.getDrawingCache();

                if (bitmap == null){
                    Toast.makeText(this, "保存成功asdasnull" + path, Toast.LENGTH_SHORT).show();
                }
                try {
                    CommonUtils.saveMyBitmap(path, "qr"+System.currentTimeMillis(), bitmap);
                    Toast.makeText(this, "保存成功" + path, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                e.printStackTrace();
            }


                break;
        }
        // return true;        // Click事件被onLongClick处理掉
        // return false;    // Click事件不被onLongClick处理，后续仍会触发onClick事件
        return true;

    }


    private void getData() {
        String order = orderTextView.getText().toString().trim();

        String paynum = paynumEditText.getText().toString().trim();

        Map<String, String> par = new HashMap<String, String>();
        par.put("uid", Preference.get(Config.ID, ""));
        par.put("order", order);
        par.put("paynum", paynum);
        OkHttpUtil.getInstance().addRequestPost(Config.addPayLog, par, new OkHttpUtil.HttpCallBack<BaseModel>() {

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

        }
    }
}
