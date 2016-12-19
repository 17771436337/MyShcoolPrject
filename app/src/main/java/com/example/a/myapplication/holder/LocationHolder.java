package com.example.a.myapplication.holder;

import android.view.View;

import com.example.a.myapplication.R;
import com.example.a.myapplication.activity.EditAddressActivity;
import com.example.a.myapplication.bean.LocationMoldel;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.UIUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/14.
 */
public class LocationHolder extends BaseHolder<LocationMoldel.Location> {
    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_location_list, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void refreshUI(LocationMoldel.Location data) {

    }


    @OnClick({R.id.compile_text})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.compile_text:  //编辑
                CommonUtils.startIntent(UIUtils.getContext(),EditAddressActivity.class);
                break;
        }
    }
}
