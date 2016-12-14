package com.example.a.myapplication.activity.screen;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.ScreenCategoryTowAdapter;
import com.example.a.myapplication.bean.ScreenCategoryTowModel;
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

    @Override
    protected int getLayoutID() {
        return R.layout.activity_categorytow;
    }

    @Override
    protected void initView() {
        getData();
        adapter = new ScreenCategoryTowAdapter(pullListView, model.getList());
        pullListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullListView.getRefreshableView().setAdapter(adapter);


}

    @Override
    protected void initData() {

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
