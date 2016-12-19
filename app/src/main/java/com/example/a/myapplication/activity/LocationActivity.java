package com.example.a.myapplication.activity;

import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.LocationAdapter;
import com.example.a.myapplication.bean.LocationMoldel;
import com.example.a.myapplication.view.TitleView1;

import java.util.ArrayList;

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
        getData();
        adapter = new LocationAdapter(model.getList());
        listView.setAdapter(adapter);
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
        view.setTitleText("地址列表", "新增");
    }

    private void getData() {

        ArrayList<LocationMoldel.Location> list = new ArrayList<LocationMoldel.Location>();
        for (int i = 0; i < 10; i++) {

            LocationMoldel.Location location = new LocationMoldel.Location();
            list.add(location);
        }

        model.setList(list);
    }
}
