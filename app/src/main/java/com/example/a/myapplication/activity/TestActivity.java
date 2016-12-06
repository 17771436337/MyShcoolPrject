package com.example.a.myapplication.activity;


import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;

public class TestActivity extends BaseActivity {


    @Override
    protected int getLayoutID() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
        mTitleView.setTitle("test");
    }

    @Override
    protected void initData() {

    }
}
