package com.example.a.myapplication.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a.myapplication.BaseApplication;
import com.example.a.myapplication.BaseView;
import com.example.a.myapplication.R;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/16.
 */
public class TitleView2 extends BaseView {

    @InjectView(R.id.title)
    protected TextView title;

    @InjectView(R.id.title_right)
    protected ImageView titleRight;

    public TitleView2(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.view_title2;
    }

    @Override
    protected void initView() {

    }

    /**
     * 设置标题
     */
    public void setTitleText(String title, int id) {
        this.title.setText(title);
        titleRight.setImageResource(id);
    }

    /**
     * 是否显示图标
     */
    public void setTitleRight(boolean is) {
        if (is)
            titleRight.setVisibility(View.VISIBLE);
        else
            titleRight.setVisibility(View.GONE);
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
