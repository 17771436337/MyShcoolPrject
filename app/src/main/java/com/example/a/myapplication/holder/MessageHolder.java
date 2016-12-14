package com.example.a.myapplication.holder;

import android.view.View;

import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.MessageModel;
import com.example.a.myapplication.util.UIUtils;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/9.
 */
public class MessageHolder extends BaseHolder<MessageModel.Message>{
    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(),R.layout.item_message_list,null);
        ButterKnife.inject(view);
        return view;
    }

    @Override
    protected void refreshUI(MessageModel.Message data) {

    }
}
