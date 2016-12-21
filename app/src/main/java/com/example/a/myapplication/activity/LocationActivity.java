package com.example.a.myapplication.activity;

import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.LocationAdapter;
import com.example.a.myapplication.bean.LocationMoldel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.Preference;
import com.example.a.myapplication.util.UIUtils;
import com.example.a.myapplication.view.TitleView1;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/14.
 */
public class LocationActivity extends BaseActivity {
    @InjectView(R.id.list)
    protected ListView listView;
    LocationAdapter adapter;
    LocationMoldel model = new LocationMoldel();

    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_location;
    }

    @Override
    protected void initView() {
        initTitle();

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    /**
     * 标题初始化
     */
    private void initTitle() {
        TitleView1 view = new TitleView1(this);
        titleView.addView(view.getView());
        view.setTitleText("地址列表", "新增");
        view.setTitleOnClickListeneRight(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.startIntent(UIUtils.getContext(), EditAddressActivity.class);
            }
        });
    }

    private void getData() {

        Map<String, String> par = new HashMap<String, String>();
        par.put("uid", Preference.get(Config.ID, ""));
        OkHttpUtil.getInstance().addRequestPost(Config.getaddress, par, new OkHttpUtil.HttpCallBack<LocationMoldel>() {

            @Override
            public void onSuccss(LocationMoldel locationMoldel) {
                EventBus.getDefault().post(locationMoldel);
            }

            @Override
            public void onFailure(String error) {

            }
        });


    }


    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);
        if (obj instanceof LocationMoldel) {
            LocationMoldel locationMoldel = (LocationMoldel) obj;
            if (locationMoldel.getC() == 1) {
                adapter = new LocationAdapter(locationMoldel.getO());
                listView.setAdapter(adapter);
            } else {

            }
        }
    }
}
