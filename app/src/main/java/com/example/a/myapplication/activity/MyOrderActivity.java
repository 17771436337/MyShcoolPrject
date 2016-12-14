package com.example.a.myapplication.activity;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.MyOrderAdapter;
import com.example.a.myapplication.bean.MyOrderModer;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/7.
 * 我的订单
 */
public class MyOrderActivity extends BaseActivity {


    @InjectView(R.id.pull_layout)
    protected PullToRefreshListView pullListView;

    MyOrderModer moder= new MyOrderModer();

    MyOrderAdapter adapter;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_myorder;
    }

    @Override
    protected void initView() {
        getData();
        adapter = new MyOrderAdapter(pullListView,moder.getData());
        pullListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullListView.getRefreshableView().setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }


    public void  getData() {
        List<MyOrderModer.Order> list = new ArrayList<MyOrderModer.Order>();
        for (int i = 0 ; i < 10 ;i++){
            MyOrderModer.Order  Order = new MyOrderModer.Order();


            list.add(Order);
        }
        moder.setData(list);
    }
}
