package com.example.a.myapplication.activity;


import android.view.View;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.TestAdapter;
import com.example.a.myapplication.bean.TestModel;
import com.example.a.myapplication.view.LoadingPagerActivity;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class TestActivity extends BaseActivity {
    TestModel mTestModel;
    @InjectView(R.id.pull_layout)
    PullToRefreshListView pull_layout;
    TestAdapter mTestAdapter;
    @Override
    protected int getLayoutID() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
        mTitleView.setTitle("test");
        mLoadingPager = new LoadingPagerActivity(this) {
            @Override
            protected LoadedResult onLoadData() {
                return performLoadingData();
            }
        };
    }



    public LoadingPagerActivity.LoadedResult performLoadingData(){
        try{
            getData();
        }catch (Exception e){
            return LoadingPagerActivity.LoadedResult.ERROR;
        }
        return LoadingPagerActivity.LoadedResult.EMPTY;
    }

    @Override
    protected void initData() {
        pull_layout.setMode(PullToRefreshBase.Mode.BOTH);
        mTestAdapter = new TestAdapter(pull_layout, mTestModel.getPeos());
        pull_layout.getRefreshableView().setAdapter(mTestAdapter);
    }
    /**
     * 添加数据
     * @param
     */
    public void getData(){
        mTestModel=new TestModel();
        List<TestModel.Peo> list = new ArrayList<TestModel.Peo>();
        for (int i=0;i<10;i++){
            TestModel.Peo pep=new TestModel.Peo();
            pep.setAge(i+"");
            pep.setName("名字"+i);
            pep.setSex(i+"");
            list.add(pep);
            mTestModel.setPeos(list);
        }
    }
}
