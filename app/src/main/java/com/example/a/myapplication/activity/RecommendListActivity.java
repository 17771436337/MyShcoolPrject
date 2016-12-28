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
    private String[] titles = new String[]{"求单品"};
    @Override
    protected int getLayoutID() {
        return R.layout.activity_recommend;
    }
    @Override
    protected void initView() {
        initTitleView();
    }

    private void initTitleView() {
        titleText.setText(getIntent().getExtras().getString("name"));
        right.setImageResource(R.drawable.icon_screen);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), this,titles);
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

           // ((StylistFragment)getFragmentManager().getBackStackEntryAt(0)).onDataChagne();
        }
    }

    @Override
    protected void initData() {

    }


    @Override
    public void onRefresh() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
