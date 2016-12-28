package com.example.a.myapplication.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.OrderFragmentPagerAdapter;
import com.example.a.myapplication.view.TitleView1;

import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/7.
 * 我的订单
 */
public class MyOrderActivity extends BaseActivity {


    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    @InjectView(R.id.fragment_find_tal)
    protected TabLayout fragment_find_tal;

    @InjectView(R.id.fragment_find_vp)
    protected ViewPager fragment_find_vp;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_myorder;
    }

    @Override
    protected void initView() {
        initTitle();


        OrderFragmentPagerAdapter adapter = new OrderFragmentPagerAdapter(this.getSupportFragmentManager(), this);
        fragment_find_vp.setAdapter(adapter);
        fragment_find_tal.setupWithViewPager(fragment_find_vp);
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
        view.setTitleText("我的订单", "");
    }


}
