package com.example.a.myapplication.activity;

import android.widget.RelativeLayout;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.MessageAdapter;
import com.example.a.myapplication.bean.MessageModel;
import com.example.a.myapplication.view.TitleView1;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/9.
 * 消息
 */
public class MessageActvity extends BaseActivity {

    @InjectView(R.id.pull_layout)
    protected PullToRefreshListView pullListView;

    MessageModel model = new MessageModel();

    MessageAdapter adapter;


    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_messsage;
    }

    @Override
    protected void initView() {
        initTitle();
        getData();
        adapter = new MessageAdapter(pullListView, model.getList());
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
        view.setTitleText("消息", "");
    }


    public void getData() {
        List<MessageModel.Message> list = new ArrayList<MessageModel.Message>();
        for (int i = 0; i < 10; i++) {
            MessageModel.Message message = new MessageModel.Message();

            list.add(message);
        }
        model.setList(list);
    }
}
