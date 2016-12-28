package com.example.a.myapplication.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.ScreenFashionModel;
import com.example.a.myapplication.util.UIUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/12.
 */
public class ScreenFashionHolder extends BaseHolder<ScreenFashionModel.Fashion> {
    @InjectView(R.id.text)
    protected TextView text;

    @InjectView(R.id.chexk)
    protected ImageView chexk;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_fashion_list, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void refreshUI(ScreenFashionModel.Fashion data) {
        text.setText(data.getName());

        if (data.is()) {
            chexk.setVisibility(View.VISIBLE);
        } else {
            chexk.setVisibility(View.INVISIBLE);
        }
    }
}
