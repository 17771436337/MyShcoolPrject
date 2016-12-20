package com.example.a.myapplication.activity;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.MyOrderAdapter;
import com.example.a.myapplication.bean.MyOrderModer;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.view.TitleView1;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;

import static android.graphics.Color.TRANSPARENT;

/**
 * Created by Administrator on 2016/12/7.
 * 我的订单
 */
public class MyOrderActivity extends BaseActivity {


    @InjectView(R.id.pull_layout)
    protected PullToRefreshListView pullListView;
    MyOrderModer moder = new MyOrderModer();
    MyOrderAdapter adapter;

    /**
     * 0待支付，1已支付-待发货，2待收货，3已完成 不传表示所有
     */
    private int type;

    private int page;

    @InjectViews({R.id.text1, R.id.text2, R.id.text3, R.id.text4})
    protected List<TextView> textView;


    @InjectViews({R.id.line1, R.id.line2, R.id.line3, R.id.line4})
    protected List<View> lineView;

    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_myorder;
    }

    @Override
    protected void initView() {
        initTitle();
        getData();
        adapter = new MyOrderAdapter(pullListView, moder.getData());
        pullListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullListView.getRefreshableView().setAdapter(adapter);
    }

    @Override
    protected void initData() {
        setSeleoct(0);
    }


    /**
     * 标题初始化
     */
    private void initTitle() {
        TitleView1 view = new TitleView1(this);
        titleView.addView(view.getView());
        view.setTitleText("我的订单", "");
    }


    @OnClick({R.id.l1, R.id.l2, R.id.l3, R.id.l4})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.l1://全部
                setSeleoct(0);

                break;
            case R.id.l2://待付款
                setSeleoct(1);
                break;
            case R.id.l3://待发货
                setSeleoct(2);
                break;
            case R.id.l4://待收货
                setSeleoct(3);
                break;
        }
    }


    /**
     * 选择器
     */
    private void setSeleoct(int i) {

        type = i;
        for (int j = 0; j < textView.size(); j++) {
            if (i == j) {
                textView.get(j).setTextColor(this.getResources().getColor(R.color.black_text));
                lineView.get(j).setBackgroundResource(R.color.black);

            } else {

                lineView.get(j).setBackgroundDrawable(new ColorDrawable(TRANSPARENT));
                textView.get(j).setTextColor(this.getResources().getColor(R.color.black_transparency_text));
            }
        }
    }


    public void getData() {

        Map<String, String> par = new HashMap<String, String>();
        par.put("uid", "3");
        par.put("type", type + "");
        par.put("pagination", page + "");
        par.put("pagelen", Config.listCount);
        OkHttpUtil.getInstance().addRequestPost(Config.orderList, par, new OkHttpUtil.HttpCallBack<MyOrderModer>() {

            @Override
            public void onSuccss(MyOrderModer myOrderModer) {
                EventBus.getDefault().post(myOrderModer);
            }

            @Override
            public void onFailure(String error) {

            }
        });

        List<MyOrderModer.Order> list = new ArrayList<MyOrderModer.Order>();
        for (int i = 0; i < 10; i++) {
            MyOrderModer.Order Order = new MyOrderModer.Order();


            list.add(Order);
        }
        moder.setData(list);
    }


    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);
        if (obj instanceof MyOrderModer) {
            MyOrderModer myOrderModer = (MyOrderModer) obj;
            if (myOrderModer.getC() == 1) {

            } else {

            }
        }
    }
}
