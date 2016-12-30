package com.example.a.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.activity.screen.ScreenActivity;
import com.example.a.myapplication.adapter.MyFragmentPagerAdapter;
import com.example.a.myapplication.adapter.MyRecyclerCommonAdapter;
import com.example.a.myapplication.bean.RecommendModel;
import com.example.a.myapplication.fragment.StylistFragment;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/9.
 * 推荐列表
 */
public class RecommendListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {


    @InjectView(R.id.message_icon)
    ImageView messageIcon;
    @InjectView(R.id.message_test)
    TextView messageTest;
    @InjectView(R.id.title_text)
    TextView titleText;
    @InjectView(R.id.right)
    ImageView right;
    @InjectView(R.id.activity_recommend_tal)
    TabLayout activityRecommendTal;
    @InjectView(R.id.activity_recommend_vp)
    ViewPager activityRecommendVp;
    private RecommendModel entity = new RecommendModel();
    private MyRecyclerCommonAdapter<RecommendModel.GankEntity> adapter;
    private int startIndex = 2;   //下标
    private int screenWidth;
    public String id;
    private String[] titles = new String[]{"求单品"};
    @Override
    protected int getLayoutID() {
        return R.layout.activity_recommend;
    }
    @Override
    protected void initView() {
        initTitleView();
    }
    String url= Config.getExclusiveItems;
    private void initTitleView() {
        titleText.setText(getIntent().getExtras().getString("name"));
        right.setImageResource(R.drawable.icon_screen);
        id=getIntent().getStringExtra("id");
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), this,titles, url,id);
        activityRecommendVp.setAdapter(adapter);
        activityRecommendTal.setupWithViewPager(activityRecommendVp);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonUtils.startIntent(RecommendListActivity.this, ScreenActivity.class,1000);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000){
            Map<String,String> parm=CommonUtils.getMapParm();
            if(null==data){
                return;
            }
            if(null!=data.getStringExtra("brand")){
                parm.put("brands",data.getStringExtra("brand"));
            }
            if(null!=data.getStringExtra("categorys")){
                parm.put("categorys",data.getStringExtra("categorys"));
            }
            if(null!=data.getStringExtra("colors")){
                parm.put("colors",data.getStringExtra("colors"));
            }
            if(null!=data.getStringExtra("populars")){
                parm.put("populars",data.getStringExtra("populars"));
            }
            if(null!=data.getStringExtra("idols")){
                parm.put("idols",data.getStringExtra("idols"));
            }
            StylistFragment.mCurrentFragment.onDataChagne(parm);
        }
    }

    @Override
    protected void initData() {

    }


    @Override
    public void onRefresh() {

    }


}
