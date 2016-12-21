package com.example.a.myapplication.view;

import android.content.Context;
import android.widget.TextView;

import com.example.a.myapplication.BaseView;
import com.example.a.myapplication.R;

import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/8.
 */
public class IntegralHeadView extends BaseView {

    @InjectView(R.id.integral_text)
    protected TextView textView;

    public IntegralHeadView(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.view_intedalhand;
    }

    @Override
    protected void initView() {

    }


    public void setTextView(String text) {
        textView.setText(text);
    }
}
