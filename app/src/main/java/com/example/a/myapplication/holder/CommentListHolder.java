package com.example.a.myapplication.holder;

import android.view.View;

import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.CommentListModel;
import com.example.a.myapplication.bean.ShopModel;
import com.example.a.myapplication.util.UIUtils;

import butterknife.ButterKnife;

/**
 * Created by a on 2016/12/24.
 */

public class CommentListHolder  extends BaseHolder<CommentListModel.OBean> {
    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.itme_shop_content_list,null);
        ButterKnife.inject(this,view);
        return view;
    }

    @Override
    protected void refreshUI(CommentListModel.OBean data) {

    }
}
