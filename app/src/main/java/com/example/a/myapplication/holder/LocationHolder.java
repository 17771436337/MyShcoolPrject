package com.example.a.myapplication.holder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.a.myapplication.R;
import com.example.a.myapplication.activity.EditAddressActivity;
import com.example.a.myapplication.activity.LocationActivity;
import com.example.a.myapplication.bean.LocationMoldel;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.UIUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/14.
 */
public class LocationHolder extends BaseHolder<LocationMoldel.Location> {

    @InjectView(R.id.name)
    protected TextView name;

    @InjectView(R.id.phone)
    protected TextView phone;

    @InjectView(R.id.address)
    protected TextView address;

    LocationMoldel.Location data;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_location_list, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void refreshUI(LocationMoldel.Location data) {
        this.data = data;
        name.setText("姓名：" + data.getName());
        phone.setText("电话：" + data.getPhone());
        address.setText(data.getProvince() + "    " + data.getCity() + "     " + data.getArea() + "\r\n" + data.getAddress());
    }


    @OnClick({R.id.compile_text, R.id.chexk})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.compile_text:  //编辑
                Bundle b = new Bundle();
                b.putSerializable("data", data);
                CommonUtils.startIntent(UIUtils.getContext(), EditAddressActivity.class, b);
                break;
            case R.id.chexk://选中框

                Intent intent = new Intent();
                intent.putExtra("data", data);
                LocationActivity.locationActivity.setResult(LocationActivity.locationActivity.resultCcode, intent);
                LocationActivity.locationActivity.finish();
                break;
        }
    }
}
