package com.example.a.myapplication.activity.app;

import android.view.View;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.MainActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.util.CommonUtils;

import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/9.
 * 登录
 */
public class LoginActivity extends BaseActivity {


    @Override
    protected int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.register_text, R.id.forget_password_text, R.id.login})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_text: //注册
                CommonUtils.startIntent(this, RegisterActivity.class);
                break;
            case R.id.forget_password_text://忘记密码
                CommonUtils.startIntent(this, ForgetPassWordActivity.class);
                break;
            case R.id.login:  //登录
                CommonUtils.startIntent(this, MainActivity.class);
                break;
        }
    }


}
