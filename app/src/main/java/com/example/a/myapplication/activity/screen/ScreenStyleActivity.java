package com.example.a.myapplication.activity.screen;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.ScreenBrandAdapter;
import com.example.a.myapplication.bean.ScreenBrandModel;
import com.example.a.myapplication.view.TitleView1;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * Created by Administrator on 2016/12/16.
 */
public class ScreenStyleActivity extends BaseActivity {

    @InjectView(R.id.srarch)
    protected EditText srarch;

    @InjectView(R.id.delete)
    protected ImageView delete;

    @InjectView(R.id.pull_layout)
    protected PullToRefreshListView pullListView;

    ScreenBrandModel model = new ScreenBrandModel();

    ScreenBrandAdapter<ScreenBrandModel.Brand> adapter;


    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_screenbrand;
    }


    @Override
    protected void initView() {
        initTitle();
        getData();
        adapter = new ScreenBrandAdapter<ScreenBrandModel.Brand>(pullListView, model.getList());
        pullListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullListView.getRefreshableView().setAdapter(adapter);
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
        view.setTitleText("Fashion Iocn", "完成");
    }


    private void getData() {
        ArrayList<ScreenBrandModel.Brand> list = new ArrayList<ScreenBrandModel.Brand>();
        for (int i = 0; i < 10; i++) {
            ScreenBrandModel.Brand brand = new ScreenBrandModel.Brand();
            list.add(brand);
        }
        model.setList(list);
    }


    @OnClick({R.id.delete})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete:  //删除
                srarch.setText("");
                break;
        }
    }

    /**
     * 输入框监听
     */
    @OnTextChanged(R.id.srarch)
    public void onTextChanged(CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            delete.setVisibility(View.GONE);
        } else {
            delete.setVisibility(View.VISIBLE);
        }
    }
}
