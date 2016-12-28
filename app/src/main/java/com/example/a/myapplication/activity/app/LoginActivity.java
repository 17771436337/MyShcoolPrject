package com.example.a.myapplication.activity.app;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.MainActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.LoginModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.MD5Util;
import com.example.a.myapplication.util.Preference;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/9.
 * 登录
 */
public class LoginActivity extends BaseActivity {

    @InjectView(R.id.phone_edit)
    protected EditText phoneEditText;

    @InjectView(R.id.password_edit)
    protected EditText passEditText;

    private long exitTime = 0;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        isLogin();
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
                String phone = phoneEditText.getText().toString().trim();
                if (TextUtils.isEmpty(phone) || phone.length() < 11) {
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }

                String password = passEditText.getText().toString();
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                Map<String, String> param = new HashMap<String, String>();
                param.put("phone", phone);

                param.put("password", MD5Util.MD5(password));
                param.put("device", CommonUtils.getSzimei(this));

                OkHttpUtil.getInstance().addRequestPost(Config.login, param, new OkHttpUtil.HttpCallBack<LoginModel>() {

                    @Override
                    public void onSuccss(LoginModel loginModel) {
                        EventBus.getDefault().post(loginModel);

                    }

                    @Override
                    public void onFailure(String error) {
//                     ;
                        EventBus.getDefault().post(error);
                    }
                });

                break;


        }
    }

    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);

        if (obj instanceof LoginModel) {
            LoginModel loginModel = (LoginModel) obj;

            if (loginModel.getC() == 1) {
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                Preference.put(Config.ID, loginModel.getO().getId() + "");
                Preference.put(Config.HEAD, loginModel.getO().getHead());
                Preference.put(Config.NAME, loginModel.getO().getName());
                Preference.put(Config.PASSWORD, loginModel.getO().getPassword());
                Preference.put(Config.PHONE, loginModel.getO().getPhone());
                Preference.put(Config.SEX, loginModel.getO().getSex());
                Preference.put(Config.AGE, loginModel.getO().getAge());
                CommonUtils.startIntent(LoginActivity.this, MainActivity.class);
                finish();
            } else {
                Toast.makeText(this, loginModel.getM() + "", Toast.LENGTH_SHORT).show();
            }
        } else if (obj instanceof String) {
            Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 是否登录
     */
    private void isLogin() {
        if (!TextUtils.isEmpty(Preference.get(Config.ID, ""))) {
            CommonUtils.startIntent(this, MainActivity.class);
            finish();
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                exitApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
