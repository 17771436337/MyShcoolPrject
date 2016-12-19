package com.example.a.myapplication.adapter;

import com.example.a.myapplication.bean.AllCommentModel;
import com.example.a.myapplication.holder.AllCommentHolder;
import com.example.a.myapplication.holder.BaseHolder;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.List;

/**
 * Created by Administrator on 2016/12/15.
 */
public class AllCommentAdapter extends SuperBaseAdapter<AllCommentModel.Comment>{
    public AllCommentAdapter(PullToRefreshBase listView, List<AllCommentModel.Comment> datas) {
        super(listView, datas);
    }

    @Override
    protected BaseHolder<AllCommentModel.Comment> getItemHolder(int position) {
        return new AllCommentHolder();
    }
}
