package com.example.a.myapplication.activity.app;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

import butterknife.InjectView;
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

    @Override
    protected int getLayoutID() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        initTitle();
        try {
            cacheText.setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void initData() {

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
