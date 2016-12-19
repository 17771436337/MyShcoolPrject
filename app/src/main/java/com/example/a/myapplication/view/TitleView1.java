package com.example.a.myapplication.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.a.myapplication.BaseApplication;
import com.example.a.myapplication.BaseView;
import com.example.a.myapplication.R;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/16.
 */
public class TitleView1 extends BaseView {

    @InjectView(R.id.title)
    protected TextView title;

    @InjectView(R.id.title_right)
    protected TextView titleRight;


    public TitleView1(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.view_title;
    }

    @Override
    protected void initView() {

    }


    /**
     * 设置标题
     */
    public void setTitleText(String title, String right) {
        this.title.setText(title);
        titleRight.setText(right);
    }


    /**
     * 设置right背景
     */
    public void setTitleRightBg(int id) {
        titleRight.setBackgroundResource(id);
    }

    @OnClick({R.id.back, R.id.title_right})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.back: //返回
                BaseApplication.getInstance().mCurrentActivity.finish();
                break;
            case R.id.title_right:

                break;
        }
    }


}
