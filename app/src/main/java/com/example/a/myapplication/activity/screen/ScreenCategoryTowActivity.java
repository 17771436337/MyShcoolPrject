package com.example.a.myapplication.activity.screen;

import android.widget.RelativeLayout;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.ScreenCategoryTowAdapter;
import com.example.a.myapplication.bean.ScreenCategoryTowModel;
import com.example.a.myapplication.view.TitleView1;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/12.
 * 品类二级筛选
 */
public class ScreenCategoryTowActivity extends BaseActivity {

    @InjectView(R.id.pull_layout)
    protected PullToRefreshListView pullListView;

    ScreenCategoryTowModel model = new ScreenCategoryTowModel();

    ScreenCategoryTowAdapter adapter;

    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_categorytow;
    }

    @Override
    protected void initView() {
        initTitle();
        getData();
        adapter = new ScreenCategoryTowAdapter(pullListView, model.getList());
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
        view.setTitleText("上衣", "完成");
    }


    private void getData() {

        ArrayList<ScreenCategoryTowModel.Category> list = new ArrayList<ScreenCategoryTowModel.Category>();
        for (int i = 0; i < 10; i++) {
            ScreenCategoryTowModel.Category category = new ScreenCategoryTowModel.Category();
            list.add(category);
        }
        model.setList(list);
    }
}
