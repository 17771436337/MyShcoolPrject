package com.example.a.myapplication.adapter;

import com.example.a.myapplication.activity.RecommendListActivity;
import com.example.a.myapplication.bean.MainFragmentModel;
import com.example.a.myapplication.holder.BaseHolder;
import com.example.a.myapplication.holder.MainFragmentHolder;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.UIUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.liuguangqiang.recyclerview.adapter.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/12/27.
 */
public class MainFragmentListAdapter extends SuperBaseAdapter<MainFragmentModel.OBean> implements BaseAdapter.OnItemClickListener{

    public MainFragmentListAdapter(PullToRefreshBase listView, List<MainFragmentModel.OBean> datas) {
        super(listView, datas);
    }

    @Override
    protected BaseHolder getItemHolder(int position) {

        return new MainFragmentHolder();
    }

    @Override
    public void onItemClick(int position) {
        CommonUtils.startIntent(UIUtils.getContext(), RecommendListActivity.class);
    }
}
