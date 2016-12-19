package com.example.a.myapplication.holder;

import android.view.View;
import android.widget.TextView;

import com.example.a.myapplication.R;
import com.example.a.myapplication.util.UIUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/14.
 */
public class ShopDialogHolder extends BaseHolder<String> {
    @InjectView(R.id.button)
    protected TextView button;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_text_list, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void refreshUI(String data) {
        button.setText(data);
    }
}
