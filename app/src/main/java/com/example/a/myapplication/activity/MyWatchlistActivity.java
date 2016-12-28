package com.example.a.myapplication.activity;

import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.MyWatchlistAdapter;
import com.example.a.myapplication.bean.WatchlistModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.Preference;
import com.example.a.myapplication.view.TitleView1;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/22.
 */
public class MyWatchlistActivity extends BaseActivity {
    @InjectView(R.id.pull_layout)
    protected PullToRefreshListView pullListView;
    WatchlistModel data = new WatchlistModel();
    MyWatchlistAdapter adapter;

    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;
    int page = 1;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_myfans;
    }

    @Override
    protected void initView() {
        initTitle();
        pullListView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new MyWatchlistAdapter(pullListView, data.getO());
        pullListView.getRefreshableView().setAdapter(adapter);

        pullListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                getData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                page = 1;
                getData();
            }
        });

    }

    @Override
    protected void initData() {
        getData();
    }

    /**
     * 标题初始化
     */
    private void initTitle() {
        TitleView1 view = new TitleView1(this);
        titleView.addView(view.getView());
        view.setTitleText("我的关注", "");
    }


    private void getData() {
        Map<String, String> par = CommonUtils.getMapParm();
        par.put("nid", Preference.get(Config.ID, ""));
        par.put("pagination", page + "");
        par.put("pagelen", Config.listCount);
        OkHttpUtil.getInstance().addRequestPost(Config.getfocuson, par, new OkHttpUtil.HttpCallBack<WatchlistModel>() {

            @Override
            public void onSuccss(WatchlistModel watchlistModel) {
                EventBus.getDefault().post(watchlistModel);
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }


    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);

        if (obj instanceof WatchlistModel) {
            WatchlistModel watchlistModel = (WatchlistModel) obj;
            data = watchlistModel;
            pullListView.onRefreshComplete();
            if (page == 1) {
                adapter.getmDatas().clear();
            }
            adapter.addData(data.getO());
            adapter.notifyDataSetChanged();
        }
    }
}
