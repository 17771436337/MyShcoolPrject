package com.example.a.myapplication.activity.app;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.BaseModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.MD5Util;
import com.example.a.myapplication.util.Preference;
import com.example.a.myapplication.view.TitleView1;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/12.
 */
public class ChangPassWordActivty extends BaseActivity {
    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;


    @InjectView(R.id.phone_edit)
    protected EditText phoneEditText;

    @InjectView(R.id.opassword_edit)
    protected EditText opasswordEditText;

    @InjectView(R.id.repassword_edit)
    protected EditText repasswordEditText;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_changpassword;
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
        view.setTitleText("修改密码", "");
    }

    @OnClick(R.id.chang_password_text)
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.chang_password_text://修改密码

                String phone = phoneEditText.getText().toString();
                if (TextUtils.isEmpty(phone) || phone.length() < 11) {
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }

                String opassword = opasswordEditText.getText().toString();
                if (TextUtils.isEmpty(opassword)) {
                    Toast.makeText(this, "请输入原密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                String repassword = repasswordEditText.getText().toString();
                if (TextUtils.isEmpty(repassword)) {
                    Toast.makeText(this, "请输入新密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                Map<String, String> param = new HashMap<String, String>();
                param.put("phone", phone);
                param.put("opassword", MD5Util.MD5(opassword));
                param.put("password", MD5Util.MD5(repassword));
                param.put("id", Preference.get(Config.ID, ""));

                OkHttpUtil.getInstance().addRequestPost(Config.hostString + "App/User/editPwd", param, new OkHttpUtil.HttpCallBack<BaseModel>() {

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


    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);
        if (obj instanceof BaseModel) {
            BaseModel data = (BaseModel) obj;

            if (data.getC() == 1) {
                Toast.makeText(this, "密码修改成功", Toast.LENGTH_SHORT).show();
                Preference.clearFlag(Config.PASSWORD);
                CommonUtils.startIntent(ChangPassWordActivty.this, LoginActivity.class);
                finish();
            } else {
                Toast.makeText(this, data.getM() + "", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
