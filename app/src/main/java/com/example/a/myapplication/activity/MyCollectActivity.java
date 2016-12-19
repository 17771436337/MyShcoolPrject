package com.example.a.myapplication.activity;

import android.widget.RelativeLayout;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.MyCollecAdapter;
import com.example.a.myapplication.bean.MyCollecModer;
import com.example.a.myapplication.view.TitleView1;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.ArrayList;

import butterknife.InjectView;

import static com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.BOTH;

/**
 * Created by Administrator on 2016/12/7.
 * 我的收藏
 */
public class MyCollectActivity extends BaseActivity{

    @InjectView(R.id.pull_layout)
    protected PullToRefreshGridView pullGridView;

    MyCollecModer moder = new MyCollecModer();

    MyCollecAdapter adapter;

    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_mycollect;
    }

    @Override
    protected void initView() {
        initTitle();

        getData();
        adapter = new MyCollecAdapter(pullGridView,moder.getCollect() );
        pullGridView.setMode(BOTH);
        pullGridView.getRefreshableView().setAdapter(adapter);

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
        view.setTitleText("我的收藏", "");
    }

    private void getData(){
        ArrayList<MyCollecModer.Colledt> list = new ArrayList<MyCollecModer.Colledt>();
        for (int i = 0; i < 30 ; i++){
            MyCollecModer.Colledt data = new MyCollecModer.Colledt();
            list.add(data);
        }
        moder.setCollect(list);
    }
}
