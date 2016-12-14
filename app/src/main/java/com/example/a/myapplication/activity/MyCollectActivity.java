package com.example.a.myapplication.activity;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.MyCollecAdapter;
import com.example.a.myapplication.bean.MyCollecModer;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.ArrayList;

import butterknife.InjectView;

import static com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.BOTH;

/**
 * Created by Administrator on 2016/12/7.
 * 我的收藏
 */
public class MyCollectActivity extends BaseActivity{

    @InjectView(R.id.pull_layout)
    protected PullToRefreshGridView pullGridView;

    MyCollecModer moder = new MyCollecModer();

    MyCollecAdapter adapter;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_mycollect;
    }

    @Override
    protected void initView() {
        getData();
        adapter = new MyCollecAdapter(pullGridView,moder.getCollect() );
        pullGridView.setMode(BOTH);
        pullGridView.getRefreshableView().setAdapter(adapter);

    }

    @Override
    protected void initData() {

    }


    private void getData(){
        ArrayList<MyCollecModer.Colledt> list = new ArrayList<MyCollecModer.Colledt>();
        for (int i = 0; i < 30 ; i++){
            MyCollecModer.Colledt data = new MyCollecModer.Colledt();
            list.add(data);
        }
        moder.setCollect(list);
    }
}
