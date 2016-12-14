package com.example.a.myapplication.activity.screen;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.ScreenFashionAdapter;
import com.example.a.myapplication.bean.ScreenFashionModel;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/12.
 */
public class ScreenFashionActivity extends BaseActivity{

    @InjectView(R.id.pull_layout)
    protected PullToRefreshListView pullListView;

    ScreenFashionModel model = new ScreenFashionModel();

    ScreenFashionAdapter adapter;
    @Override
    protected int getLayoutID() {
        return R.layout.activity_fashion;
    }

    @Override
    protected void initView() {
        getData();
        adapter = new ScreenFashionAdapter(pullListView,model.getList());
        pullListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullListView.getRefreshableView().setAdapter(adapter);

    }

    @Override
    protected void initData() {

    }


    private  void getData(){
        ArrayList<ScreenFashionModel.Fashion> list = new ArrayList<ScreenFashionModel.Fashion>();
        for (int i = 0 ;i < 10 ; i ++){
            ScreenFashionModel.Fashion fashion = new ScreenFashionModel.Fashion();
            list.add(fashion);
        }
        model.setList(list);
    }


}
