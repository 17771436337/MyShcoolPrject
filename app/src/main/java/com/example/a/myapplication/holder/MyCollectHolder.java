package com.example.a.myapplication.holder;

import android.view.View;

import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.MyCollecModer;
import com.example.a.myapplication.util.UIUtils;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/7.
 */
public class MyCollectHolder extends BaseHolder<MyCollecModer.Colledt> {
    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_mycollect_list, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void refreshUI(MyCollecModer.Colledt data) {

    }
}
