package com.example.a.myapplication.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.BaseApplication;
import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.BaseModel;
import com.example.a.myapplication.bean.LocationMoldel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.Preference;
import com.example.a.myapplication.view.TitleView1;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/15.
 * 编辑地址
 */
public class EditAddressActivity extends BaseActivity {

    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    @InjectView(R.id.name_edit)
    protected EditText nameEditText;

    @InjectView(R.id.phone_edit)
    protected EditText phoneEditText;

    @InjectView(R.id.code_edit)
    protected EditText codeEditText;

    @InjectView(R.id.city)
    protected TextView Location;

    @InjectView(R.id.address)
    protected EditText addressEditText;

    private String province = "湖北省";//省

    private String city = "武汉市";//市

    private String area = "武昌区";//区

    private LocationMoldel.Location data;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_editaddress;
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
        view.setTitleText("收货地址", "保存");

        view.setTitleOnClickListeneRight(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveaddress();


            }
        });
    }

    @Override
    protected void initData() {
        Bundle b = getIntent().getExtras();
        if (b != null)
            data = (LocationMoldel.Location) b.getSerializable("data");
        if (data != null) {
            nameEditText.setText(data.getName());
            phoneEditText.setText(data.getPhone());
            codeEditText.setText(data.getCode());
            Location.setText(data.getProvince() + data.getCity() + data.getArea());
            addressEditText.setText(data.getAddress());

        }
    }

    /**
     * 编辑地址
     */
    private void saveaddress() {
        String name = nameEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String code = codeEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入收件人姓名", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(code)) {
            Toast.makeText(this, "请输入邮政编码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(address)) {
            Toast.makeText(this, "请输入详细地址", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, String> par = new HashMap<String, String>();

        par.put("name", name);
        par.put("phone", phone);
        par.put("code", code);
        par.put("province", province);
        par.put("city", city);
        par.put("area", area);
        par.put("address", address);


        String url;
        if (data != null) {
            url = Config.saveaddress;//编辑地址 /
            par.put("id", data.getId());
        } else {
            url = Config.addaddress;//新增地址
            par.put("uid", Preference.get(Config.ID, ""));
        }

        OkHttpUtil.getInstance().addRequestPost(url, par, new OkHttpUtil.HttpCallBack<BaseModel>() {

            @Override
            public void onSuccss(BaseModel baseModel) {
                EventBus.getDefault().post(baseModel);
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);
        if (obj instanceof BaseModel) {
            BaseModel baseModel = (BaseModel) obj;
            if (baseModel.getC() == 1) {
                if (data != null) {
                    Toast.makeText(this, "编辑成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                }


                finish();
            } else {
                Toast.makeText(BaseApplication.getInstance(), baseModel.getM() + "", Toast.LENGTH_SHORT).show();
            }
        }
    }
}



