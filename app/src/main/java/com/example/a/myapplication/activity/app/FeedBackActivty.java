package com.example.a.myapplication.activity.app;

import android.text.TextUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.view.TitleView1;

import butterknife.InjectView;
import butterknife.OnTextChanged;

/**
 * Created by Administrator on 2016/12/16.
 * 意见反馈
 */
public class FeedBackActivty extends BaseActivity {
    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    @InjectView(R.id.text)
    protected TextView text;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_feedback;
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
        view.setTitleText("意见反馈", "");
    }


    @OnTextChanged(R.id.content)
    protected void onTextChanged(CharSequence str) {
        if (TextUtils.isEmpty(str)) {
            text.setText("0/1000");
        } else {

            if (str.length() >= 1000) {
                text.setText(str.length() + "/1000");
            } else {
                text.setText(str.length() + "/1000");
            }
        }
    }
}
