package com.example.a.myapplication.activity.app;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.DataCleanManager;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/8.
 * 设置
 */
public class SettingActivity extends BaseActivity {

    @InjectView(R.id.cache)
    protected TextView cacheText;
    @Override
    protected int getLayoutID() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        try {
            cacheText.setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.cache_layout,R.id.binding_phone_text,R.id.chang_password_text})
    public void onClick(View v){
        switch (v.getId()){
            case  R.id.cache_layout://清理缓存
                Toast.makeText(SettingActivity.this, "清除缓存成功",
                        Toast.LENGTH_SHORT).show();
                DataCleanManager.clearAllCache(SettingActivity.this);
                try {
                    cacheText.setText(DataCleanManager
                            .getTotalCacheSize(SettingActivity.this));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                break;
            case R.id.binding_phone_text://绑定手机号
                CommonUtils.startIntent(this,BingDingPhoneActivty.class);
                break;
            case R.id.chang_password_text://修改密码
                CommonUtils.startIntent(this,ChangPassWordActivty.class);
                break;
        }

    }
}
