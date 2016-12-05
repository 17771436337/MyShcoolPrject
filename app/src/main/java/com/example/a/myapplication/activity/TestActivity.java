package com.example.a.myapplication.activity;


import android.view.View;
import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.view.LoadingPagerActivity;

public class TestActivity extends BaseActivity {
    private LoadingPagerActivity mLoadingPager;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
        mTitleView.setTitle("test");
    /*    mLoadingPager = new LoadingPagerActivity(this) {
            @Override
            protected View initSuccessView() {
                //返回成功的页面
                return onSucccessView();
            }
            @Override
            protected LoadedResult onLoadData() {
                return performLoadingData();
            }
        };
        setContentView(mLoadingPager);*///测试分支
    }

    @Override
    protected void initData() {

    }
}
