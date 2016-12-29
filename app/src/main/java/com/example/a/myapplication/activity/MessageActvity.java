package com.example.a.myapplication.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.MessageAdapter;
import com.example.a.myapplication.bean.MessageModel;
import com.example.a.myapplication.bean.ScreenFashionModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.Preference;
import com.example.a.myapplication.view.TitleView1;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/9.
 * 消息
 */
public class MessageActvity extends BaseActivity implements OnItemClickListener {

    @InjectView(R.id.pull_layout)
    protected PullToRefreshListView pullListView;


    private MessageModel model = new MessageModel();

    MessageAdapter adapter;


    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    private int page = 1;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_messsage;
    }

    @Override
    protected void initView() {
        initTitle();

        adapter = new MessageAdapter(pullListView, model.getO());
        pullListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullListView.getRefreshableView().setAdapter(adapter);
        pullListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                EventBus.getDefault().post("onPullDownToRefresh");
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                EventBus.getDefault().post("onPullUpToRefresh");
            }
        });

        pullListView.getRefreshableView().setOnItemClickListener(this);
        getData();
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
        Map<String, String> par = CommonUtils.getMapParm();
        par.put("uid", "2");
        par.put("pagination", String.valueOf(page));
        par.put("pagelen", Config.listCount);
        OkHttpUtil.getInstance().addRequestPost(Config.messageList, par, new OkHttpUtil.HttpCallBack<MessageModel>() {

            @Override
            public void onSuccss(MessageModel messageModel) {
                EventBus.getDefault().post(messageModel);
            }

            @Override
            public void onFailure(String error) {

            }
        });

    }


    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);
        if (obj instanceof MessageModel) {
            MessageModel messageModel = (MessageModel) obj;
            if (messageModel.getC() == 1) {

                if (model.getO() != null) {
                    if (page == 1) {
                        model.getO().clear();
                    }

                    model.getO().addAll(messageModel.getO());
                } else {
                    model = messageModel;
                }
                if (messageModel.getO() != null && messageModel.getO().size() > 0) {
                    if (page == 1) {
                        if (adapter.getmDatas() != null)
                            adapter.getmDatas().clear();
                    }

                    adapter.addData(messageModel.getO());
                    adapter.notifyDataSetChanged();
                }
            } else {
                Toast.makeText(this, messageModel.getM() + "", Toast.LENGTH_SHORT).show();
            }
        }


        if (obj instanceof String) {
            String str = (String) obj;
            if (str.equals("onPullDownToRefresh")) {
                page = 1;
                getData();

            } else if (str.equals("onPullUpToRefresh")) {
                page++;
                getData();
            }
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (model != null) {
            MessageModel.Message data = model.getO().get(position - 1);
            if (data.getType().equals("1")) {
                CommonUtils.startIntent(this, MyOrderActivity.class);
            }

            adapter.notifyDataSetChanged();
        }
    }
}
