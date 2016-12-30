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
import com.example.a.myapplication.util.MD5Util;
import com.example.a.myapplication.view.TitleView1;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/9.
 * 注册
 */
public class RegisterActivity extends BaseActivity {
    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    @InjectView(R.id.code)
    protected TextView codeText;

    @InjectView(R.id.phone_edit)
    protected EditText phoneEditText;

    @InjectView(R.id.code_edit)
    protected EditText codeEditText;

    @InjectView(R.id.password_edit)
    protected EditText passEditText;

    Timer timer = new Timer();

    @Override
    protected int getLayoutID() {
        return R.layout.activity_register;
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
        view.setTitleText("注册", "");
    }


    @OnClick({R.id.code, R.id.register})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.code://发送验证码
                String phone = phoneEditText.getText().toString();
                if (TextUtils.isEmpty(phone) || phone.length() < 11) {
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }

                OkHttpUtil.getInstance().addRequestGet(Config.sendCode(phone, 1), new OkHttpUtil.HttpCallBack<BaseModel>() {

                    @Override
                    public void onSuccss(BaseModel baseModel) {
                        timer.start();
                    }

                    @Override
                    public void onFailure(String error) {

                    }
                });

                break;

            case R.id.register://注册
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

                String password = passEditText.getText().toString();
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(this, "请输入6位数以上密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                Map<String, String> param = new HashMap<String, String>();
                param.put("phone", phone);
                param.put("password", MD5Util.MD5(password));
                param.put("verify", code);
                param.put("teltype", 1 + "");

                OkHttpUtil.getInstance().addRequestPost(Config.register, param, new OkHttpUtil.HttpCallBack<BaseModel>() {

                    @Override
                    public void onSuccss(BaseModel baseModel) {
                        EventBus.getDefault().post(baseModel);


                    }

                    @Override
                    public void onFailure(String error) {

                    }
                });

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
            codeText.setEnabled(false);
        }

        @Override
        public void onFinish() {
            codeText.setText("发送验证码");
            codeText.setEnabled(true);
        }

    }

    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);

        if (obj instanceof BaseModel) {//注册成功
            BaseModel data = (BaseModel) obj;

            if (data.getC() == 1) {
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, data.getM() + "", Toast.LENGTH_SHORT).show();
            }
        }


    }

    @Override
    public void finish() {
        super.finish();
        timer.cancel();
    }
}
