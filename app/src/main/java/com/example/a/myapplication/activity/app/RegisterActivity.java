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
 * 注册
 */
public class RegisterActivity extends BaseActivity {
    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    @InjectView(R.id.code)
    protected TextView codeText;

    @InjectView(R.id.phone_edit)
    protected EditText phoneEditText;

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


    @OnClick({R.id.code})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.code://发送验证码
                String phone = phoneEditText.getText().toString();
                if (TextUtils.isEmpty(phone) || phone.length() < 11) {
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(this, "手机号" + phone, Toast.LENGTH_SHORT).show();
                OkHttpUtil.getInstance().addRequestGet(Config.hostString + "wardrobe/code/wardrode/index.php?s=/App/User/sendPhoneCode/phone/" + phone + "/type/1", new OkHttpUtil.HttpCallBack<BaseModel>() {


                    @Override
                    public void onSuccss(BaseModel baseModel) {
                        Toast.makeText(RegisterActivity.this, "手机号", Toast.LENGTH_SHORT).show();
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
            super(120 * 1000, 1000);
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

//    @Override
//    public void onEventMainThread(Object obj) {
//        super.onEventMainThread(obj);
//        Toast.makeText(RegisterActivity.this, "手机号", Toast.LENGTH_SHORT).show();
//        timer.start();
//
//    }

    @Override
    public void finish() {
        super.finish();
        timer.cancel();
    }
}
