package com.example.a.myapplication.activity;


import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
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

    @Override
    protected int getLayoutID() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
        mTitleView.setTitle("test");

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
