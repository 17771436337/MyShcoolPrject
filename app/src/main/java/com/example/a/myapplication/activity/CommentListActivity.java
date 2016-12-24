package com.example.a.myapplication.activity;

import android.os.Bundle;
import android.view.View;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.CommentListAdapter;
import com.example.a.myapplication.adapter.ProductTitleMessageTwoAdapter;
import com.example.a.myapplication.bean.CommentListModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.view.LoadingPager;
import com.example.a.myapplication.view.LoadingPagerActivity;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CommentListActivity extends BaseActivity {

    LoadingPagerActivity mLoadingPagerActivity;
    @InjectView(R.id.activity_comment_list_pull_layout)
    PullToRefreshListView activityCommentListPullLayout;
    View view;
    CommentListAdapter adapter;
    CommentListModel mCommentListModel;
    @Override
    protected int getLayoutID() {
        return R.layout.activity_comment_list;
    }

    @Override
    protected void initView() {
        mLoadingPagerActivity = new LoadingPagerActivity(this,"评论列表") {
            @Override
            protected View initSuccessView() {
                return initSuccessViewAc();
            }

            @Override
            protected LoadedResult onLoadData() {
                return onLoadDataAc();
            }
        };
        setContentView(mLoadingPagerActivity);

    }

    @Override
    protected void initData() {

    }

    public View initSuccessViewAc() {
        view = View.inflate(this, R.layout.activity_comment_list, null);
        ButterKnife.reset(this);
        ButterKnife.inject(this, view);
        adapter = new CommentListAdapter(mCommentListModel.getO());
        activityCommentListPullLayout.setMode(PullToRefreshBase.Mode.DISABLED);
        activityCommentListPullLayout.getRefreshableView().setAdapter(adapter);
        return view;

    }

    public LoadingPagerActivity.LoadedResult onLoadDataAc() {
        try {
          Map<String,String> parm= CommonUtils.getMapParm();
            parm.put("rid",getIntent().getExtras().getString("rid"));
            String json=OkHttpUtil.getInstance().addRequestNoCallPost(Config.COMMENTLIST,parm);
            mCommentListModel= new Gson().fromJson(json,CommentListModel.class);
            if(null==mCommentListModel||mCommentListModel.getO().size()==0){
                return LoadingPagerActivity.LoadedResult.EMPTY;
            }
        } catch (Exception e) {
            return LoadingPagerActivity.LoadedResult.ERROR;
        }
        return LoadingPagerActivity.LoadedResult.SUCCESS;
    }
}
