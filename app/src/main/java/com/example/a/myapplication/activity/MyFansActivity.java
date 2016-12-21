package com.example.a.myapplication.activity;

import android.widget.RelativeLayout;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.MyFansAdapter;
import com.example.a.myapplication.bean.FansModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.view.TitleView1;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/7.
 * 我的粉丝
 */
public class MyFansActivity extends BaseActivity {

    @InjectView(R.id.pull_layout)
    protected PullToRefreshListView pullListView;
    FansModel data = new FansModel();
    MyFansAdapter adapter;

    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_myfans;
    }

    @Override
    protected void initView() {
        initTitle();
        getData();

        pullListView.setMode(PullToRefreshBase.Mode.BOTH);


    }

    @Override
    protected void initData() {
        getData();
        adapter.notifyDataSetChanged();
    }

    /**
     * 标题初始化
     */
    private void initTitle() {
        TitleView1 view = new TitleView1(this);
        titleView.addView(view.getView());
        view.setTitleText("粉丝", "");
    }


    public void getData() {


        Map<String, String> par = new HashMap<String, String>();
        par.put("fid", "2");
        OkHttpUtil.getInstance().addRequestPost(Config.getfans, par, new OkHttpUtil.HttpCallBack<FansModel>() {

            @Override
            public void onSuccss(FansModel fansModel) {
                EventBus.getDefault().post(fansModel);
            }

            @Override
            public void onFailure(String error) {

            }
        });


    }

    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);
        if (obj instanceof FansModel) {
            FansModel fansModel = (FansModel) obj;
            data = fansModel;
            adapter = new MyFansAdapter(pullListView, data.getO());
            pullListView.getRefreshableView().setAdapter(adapter);
        }
    }
}
