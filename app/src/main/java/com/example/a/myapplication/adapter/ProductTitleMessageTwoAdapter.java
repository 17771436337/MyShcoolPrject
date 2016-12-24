package com.example.a.myapplication.adapter;

import com.example.a.myapplication.bean.MessageModel;
import com.example.a.myapplication.bean.ProductTitleMessageModel;
import com.example.a.myapplication.holder.BaseHolder;
import com.example.a.myapplication.holder.MessageHolder;
import com.example.a.myapplication.holder.ProductTitleMessageTwoHolder;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.List;

/**
 * Created by Administrator on 2016/12/9.
 */
public class ProductTitleMessageTwoAdapter extends  SuperBaseAdapter<ProductTitleMessageModel.OBean.DetailsBean>{
    public ProductTitleMessageTwoAdapter(PullToRefreshBase listView, List<ProductTitleMessageModel.OBean.DetailsBean> datas) {
        super(listView, datas);
    }

    @Override
    protected BaseHolder<ProductTitleMessageModel.OBean.DetailsBean> getItemHolder(int position) {
        return new ProductTitleMessageTwoHolder( position) ;
    }
}
