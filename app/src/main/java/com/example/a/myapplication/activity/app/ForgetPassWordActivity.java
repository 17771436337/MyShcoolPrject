package com.example.a.myapplication.activity.app;

import android.widget.RelativeLayout;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.view.TitleView1;

import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/9.
 * 忘记密码
 */
public class ForgetPassWordActivity extends BaseActivity{
    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_forgetpassword;
    }

    @Override
    protected void initView() {
        initTitle();
    }

    @Override
    protected void initData() {

    }

    /**
     * 标题初始化
     */
    private void initTitle() {
        TitleView1 view = new TitleView1(this);
        titleView.addView(view.getView());
        view.setTitleText("忘记密码", "");
    }
}
