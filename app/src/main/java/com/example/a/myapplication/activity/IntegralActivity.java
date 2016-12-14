package com.example.a.myapplication.activity;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.IntegralAdapter;
import com.example.a.myapplication.bean.IntegralModel;
import com.example.a.myapplication.view.IntegralHeadView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/8
 * 积分记录
 */
public class IntegralActivity extends BaseActivity{

    @InjectView(R.id.pull_layout)
    protected PullToRefreshListView pullListView;
    IntegralModel model = new IntegralModel();
    IntegralAdapter adapter;
    IntegralHeadView headView;
    @Override
    protected int getLayoutID() {
        return R.layout.activity_integral;
    }

    @Override
    protected void initView() {
        getData();
        adapter = new IntegralAdapter(pullListView,model.getIntegral());
        pullListView.setMode(PullToRefreshBase.Mode.BOTH);
        headView = new IntegralHeadView(this);
        pullListView.getRefreshableView().addHeaderView(headView.getView());
        pullListView.getRefreshableView().setAdapter(adapter);


    }

    @Override
    protected void initData() {

    }

    private void getData(){
        ArrayList<IntegralModel.Integral> list = new ArrayList<IntegralModel.Integral>();
        for (int i = 0; i < 30 ; i++){
            IntegralModel.Integral data = new IntegralModel.Integral();
            list.add(data);
        }
        model.setIntegral(list);
    }
}
