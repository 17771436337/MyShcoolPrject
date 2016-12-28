package com.example.a.myapplication.adapter;

import com.example.a.myapplication.bean.CommentListModel;
import com.example.a.myapplication.bean.ProductTitleMessageModel;
import com.example.a.myapplication.holder.BaseHolder;
import com.example.a.myapplication.holder.CommentListHolder;
import com.example.a.myapplication.holder.ProductTitleMessageItmeHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */
public class CommentListAdapter extends SuperBaseAdapter<CommentListModel.OBean> {
    public CommentListAdapter(List<CommentListModel.OBean> datas) {
        super(datas);
    }

    @Override
    protected BaseHolder<CommentListModel.OBean>  getItemHolder(int position) {
        return new CommentListHolder();
    }
}
