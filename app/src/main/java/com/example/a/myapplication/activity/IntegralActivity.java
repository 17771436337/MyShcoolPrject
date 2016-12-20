package com.example.a.myapplication.activity;

import android.widget.RelativeLayout;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.IntegralAdapter;
import com.example.a.myapplication.bean.IntegralModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.view.IntegralHeadView;
import com.example.a.myapplication.view.TitleView2;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/8
 * 积分记录
 */
public class IntegralActivity extends BaseActivity {

    @InjectView(R.id.pull_layout)
    protected PullToRefreshListView pullListView;
    IntegralModel model = new IntegralModel();
    IntegralAdapter adapter;
    IntegralHeadView headView;

    private int page = 1;


    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_integral;
    }

    @Override
    protected void initView() {
        initTitle();

        headView = new IntegralHeadView(this);

        getData();
        pullListView.getRefreshableView().addHeaderView(headView.getView());
        pullListView.setMode(PullToRefreshBase.Mode.BOTH);


    }

    @Override
    protected void initData() {

    }


    /**
     * 标题初始化
     */
    private void initTitle() {
        TitleView2 view = new TitleView2(this);
        titleView.addView(view.getView());
        view.setTitleText("好物置换", R.drawable.icon_replacement);
    }


    private void getData() {

        Map<String, String> par = new HashMap<String, String>();
        par.put("uid", "2");
        par.put("pagination", page + "");
        par.put("pagelen", Config.listCount);

        OkHttpUtil.getInstance().addRequestPost(Config.my_Integral, par, new OkHttpUtil.HttpCallBack<IntegralModel>() {

            @Override
            public void onSuccss(IntegralModel integralModel) {

                EventBus.getDefault().post(integralModel);

            }

            @Override
            public void onFailure(String error) {

            }
        });


    }


    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);
        if (obj instanceof IntegralModel) {
            IntegralModel integralModel = (IntegralModel) obj;
            if (integralModel.getC() == 1) {
                model = integralModel;
                if (headView != null) {
                    headView.setTextView(model.getE());
                }
                adapter = new IntegralAdapter(pullListView, model.getO());
                pullListView.getRefreshableView().setAdapter(adapter);
            } else {

            }
        }
    }
}


