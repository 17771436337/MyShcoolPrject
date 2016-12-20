package com.example.a.myapplication.activity.app;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.BaseModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.view.TitleView1;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/9.
 */
public class BingDingPhoneActivty extends BaseActivity {

    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    @InjectView(R.id.code)
    protected TextView codeText;

    @InjectView(R.id.phone_edit)
    protected EditText phoneEditText;

    @InjectView(R.id.code_edit)
    protected EditText codeEditText;

    Timer timer = new Timer();

    @Override
    protected int getLayoutID() {
        return R.layout.activity_bingdingphone;
    }

    @Override
    protected void initView() {
        initTitle();


    }

    /**
     * 标题初始化
     */
    private void initTitle() {
        TitleView1 view = new TitleView1(this);
        titleView.addView(view.getView());
        view.setTitleText("绑定手机号", "");
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.code, R.id.ok})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.code://发送验证码
                String phone = phoneEditText.getText().toString();
                if (TextUtils.isEmpty(phone) || phone.length() < 11) {
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }


                OkHttpUtil.getInstance().addRequestGet(Config.hostString + "App/User/sendPhoneCode/phone/" + phone + "/type/3", new OkHttpUtil.HttpCallBack<BaseModel>() {


                    @Override
                    public void onSuccss(BaseModel baseModel) {
                        timer.start();
                    }

                    @Override
                    public void onFailure(String error) {

                    }
                });

                break;
            case R.id.ok:
                phone = phoneEditText.getText().toString();
                if (TextUtils.isEmpty(phone) || phone.length() < 11) {
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }

                String code = codeEditText.getText().toString();
                if (TextUtils.isEmpty(code)) {
                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                }


                break;
        }
    }


    /**
     * 倒计时
     */
    private class Timer extends CountDownTimer {

        public Timer() {
            super(60 * 1000, 1000);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Log.i("test", "??");
            codeText.setText(millisUntilFinished / 1000 + "秒后重发");
        }

        @Override
        public void onFinish() {
            codeText.setText("发送验证码");

        }

    }

    @Override
    public void finish() {
        super.finish();
        timer.cancel();
    }
}
