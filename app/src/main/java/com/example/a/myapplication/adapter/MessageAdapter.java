package com.example.a.myapplication.adapter;

import com.example.a.myapplication.bean.MessageModel;
import com.example.a.myapplication.holder.BaseHolder;
import com.example.a.myapplication.holder.MessageHolder;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.List;

/**
 * Created by Administrator on 2016/12/9.
 */
public class MessageAdapter extends  SuperBaseAdapter<MessageModel.Message>{
    public MessageAdapter(PullToRefreshBase listView, List<MessageModel.Message> datas) {
        super(listView, datas);
    }

    @Override
    protected BaseHolder<MessageModel.Message> getItemHolder(int position) {
        return new MessageHolder() ;
    }
}
