package com.example.a.myapplication.activity.app;

import android.widget.RelativeLayout;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.view.TitleView1;

import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/9.
 */
public class BingDingPhoneActivty extends BaseActivity {

    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_bingdingphone;
    }

    @Override
    protected void initView() {
        initTitle();


    }

    /**
     * 标题初始化
     */
    private void initTitle() {
        TitleView1 view = new TitleView1(this);
        titleView.addView(view.getView());
        view.setTitleText("绑定手机号", "");
    }

    @Override
    protected void initData() {

    }
}
