package com.example.a.myapplication.activity.app;

import android.os.Handler;
import android.text.TextUtils;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.MainActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.Preference;

public class WelcomeActivity extends BaseActivity {
    @Override
    protected int getLayoutID() {

        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (TextUtils.isEmpty(Preference.get(Config.ID, ""))) {
                    CommonUtils.startIntent(WelcomeActivity.this, LoginActivity.class);
                    finish();
                } else {
                    CommonUtils.startIntent(WelcomeActivity.this, MainActivity.class);
                    finish();
                }

            }
        }, 3000);

    }

    @Override
    protected void initData() {

    }
}
