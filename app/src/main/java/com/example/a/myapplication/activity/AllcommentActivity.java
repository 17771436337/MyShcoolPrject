package com.example.a.myapplication.activity;

import android.widget.RelativeLayout;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.AllCommentAdapter;
import com.example.a.myapplication.bean.AllCommentModel;
import com.example.a.myapplication.view.TitleView1;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/15.
 */
public class AllcommentActivity extends BaseActivity {

    @InjectView(R.id.pull_layout)
    protected PullToRefreshListView pullListView;
    AllCommentAdapter adapter;
    AllCommentModel model = new AllCommentModel();

    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_allcomment;
    }

    @Override
    protected void initView() {
        initTitle();
        getData();
        adapter = new AllCommentAdapter(pullListView, model.getList());
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
        view.setTitleText("全部评价", "");
    }


    void getData() {
        ArrayList<AllCommentModel.Comment> list = new ArrayList<AllCommentModel.Comment>();
        for (int i = 0; i < 10; i++) {
            AllCommentModel.Comment comment = new AllCommentModel.Comment();
            list.add(comment);
        }

        model.setList(list);
    }
}
