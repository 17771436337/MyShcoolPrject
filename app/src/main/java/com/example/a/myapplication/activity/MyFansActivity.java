package com.example.a.myapplication.activity;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.MyFansAdapter;
import com.example.a.myapplication.bean.FansModel;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/7.
 * 我的粉丝
 */
public class MyFansActivity extends BaseActivity {

    @InjectView(R.id.pull_layout)
    protected PullToRefreshListView pullListView;
    FansModel data = new FansModel();
    MyFansAdapter adapter;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_myfans;
    }

    @Override
    protected void initView() {
        getData();
        adapter = new MyFansAdapter(pullListView,data.getFans());
        pullListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullListView.getRefreshableView().setAdapter(adapter);

    }

    @Override
    protected void initData() {
        getData();
        adapter.notifyDataSetChanged();
    }


    public void  getData() {
        List<FansModel.MyFans> list = new ArrayList<FansModel.MyFans>();
        for (int i = 0 ; i < 10 ;i++){
            FansModel.MyFans  fans = new FansModel.MyFans();
            fans.setImg("");
            fans.setName("武汉");

            if (i % 2 == 0) {
                fans.setType(0);
            }else{
                fans.setType(1);
            }

            list.add(fans);
        }
        data.setFans(list);
    }
}
