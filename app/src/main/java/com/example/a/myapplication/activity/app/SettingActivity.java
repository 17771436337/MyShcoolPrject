package com.example.a.myapplication.activity.app;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.BaseModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.DataCleanManager;
import com.example.a.myapplication.util.Preference;
import com.example.a.myapplication.view.TitleView1;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.InjectView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/8.
 * 设置
 */
public class SettingActivity extends BaseActivity {

    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    @InjectView(R.id.cache)
    protected TextView cacheText;

    @InjectView(R.id.remind)
    protected ToggleButton remind;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        initTitle();

        if (Preference.get(Config.REMIND, "1").equals("0")) {
            remind.setChecked(true);
        } else {
            remind.setChecked(false);
        }


        try {
            cacheText.setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void initData() {

    }

    @OnCheckedChanged(R.id.remind)
    protected void onCheckeChanged(boolean is) {
        final String remind;
        if (is) {
            remind = "0";
        } else {
            remind = "1";
        }


        Map<String, String> par = new HashMap<String, String>();
        par.put("id", Preference.get(Config.ID, ""));
        par.put("remind", remind);
        final String str = remind;
        OkHttpUtil.getInstance().addRequestPost(Config.messageremind, par, new OkHttpUtil.HttpCallBack<BaseModel>() {

            @Override
            public void onSuccss(BaseModel baseModel) {
                Preference.put(Config.REMIND, str);
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }


    @OnClick({R.id.cache_layout, R.id.binding_phone_text, R.id.chang_password_text, R.id.feed_back_text, R.id.out})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cache_layout://清理缓存
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
                CommonUtils.startIntent(this, BingDingPhoneActivty.class);
                break;
            case R.id.chang_password_text://修改密码
                CommonUtils.startIntent(this, ChangPassWordActivty.class);
                break;
            case R.id.feed_back_text:
                CommonUtils.startIntent(this, FeedBackActivty.class);
                break;
            case R.id.out://退出登录
                OkHttpUtil.getInstance().addRequestGet(Config.logout, new OkHttpUtil.HttpCallBack<BaseModel>() {

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
     * 标题初始化
     */
    private void initTitle() {
        TitleView1 view = new TitleView1(this);
        titleView.addView(view.getView());
        view.setTitleText("个人设置", "");
    }

    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);
        if (obj instanceof BaseModel) {
            BaseModel data = (BaseModel) obj;

            if (data.getC() == 1) {
                Toast.makeText(this, "退出成功", Toast.LENGTH_SHORT).show();
                Preference.clearAllFlag();
                CommonUtils.startIntent(this, LoginActivity.class);
                finish();
            } else {
                Toast.makeText(this, data.getM() + "", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
