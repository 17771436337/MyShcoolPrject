package com.example.a.myapplication.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.a.myapplication.BaseFragment;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.MyOrderAdapter;
import com.example.a.myapplication.bean.MyOrderModer;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.view.LoadingPager;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.BOTH;

/**
 * Created by Administrator on 2016/12/22.
 */
public class OrderFragment extends BaseFragment {
    View view;


    @InjectView(R.id.swipeToLoadLayout)
    protected RelativeLayout fragment_stylist_sl;

    @InjectView(R.id.pull_layout)
    protected PullToRefreshListView pullListView;

    public static final String ARGS_PAGE = "args_page";
    public static final String ARGS_TYPE = "arge_type";
    private int mPage;
    public static int page = 1;
    private MyOrderAdapter adapter;
    MyOrderModer orderModer;
    int position;

    public static OrderFragment newInstance(int position) {
        Bundle args = new Bundle();

        args.putInt(ARGS_PAGE, page);
        args.putInt(ARGS_TYPE, position);
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARGS_PAGE);
        position = getArguments().getInt(ARGS_TYPE);
    }

    @Override
    protected View onLoadSuccessView() {
        view = View.inflate(getActivity(), R.layout.fragment_orderlist, null);
        ButterKnife.inject(this, view);
        init();
        return view;
    }

    private void init() {

        pullListView.setMode(BOTH);
        adapter = new MyOrderAdapter(pullListView, orderModer.getO());
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

    }


    public LoadingPager.LoadedResult initData() {
        try {
            initDate();
            if (null == orderModer.getO() || orderModer.getO().size() == 0) {
                return LoadingPager.LoadedResult.EMPTY;
            }
            return LoadingPager.LoadedResult.SUCCESS;
        } catch (Exception e) {
            return LoadingPager.LoadedResult.ERROR;
        }
    }

    public MyOrderModer initDate() {

        Map<String, String> par = CommonUtils.getMapParm();
        par.put("uid", "2");
        par.put("type", position + "");
        par.put("pagination", String.valueOf(page));
        par.put("pagelen", Config.listCount);
        String result = OkHttpUtil.getInstance().addRequestNoCallPost(Config.orderList, par);
        Log.e("json", result + "");
        orderModer = new Gson().fromJson(result, MyOrderModer.class);
        return orderModer;

    }

    @Override
    protected LoadingPager.LoadedResult onLoadingData() {
        return initData();
    }


    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);
        if ("onPullUpToRefresh".equals(obj.toString())) {
            initDate();
            page = 1;
            notifyData();
        }
        if ("onPullDownToRefresh".equals(obj.toString())) {
            initDate();
            page++;
            notifyData();
        }
    }

    /**
     * 刷新数据
     */
    public void notifyData() {


        pullListView.onRefreshComplete();
        if (orderModer.getO() != null) {
            adapter.getDatas().addAll(orderModer.getO());
        }
        adapter.notifyDataSetChanged();


    }

}
