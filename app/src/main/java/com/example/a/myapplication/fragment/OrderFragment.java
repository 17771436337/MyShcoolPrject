package com.example.a.myapplication.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.a.myapplication.BaseFragment;
import com.example.a.myapplication.R;
import com.example.a.myapplication.activity.MyOrderDetailsActivity;
import com.example.a.myapplication.adapter.MyOrderAdapter;
import com.example.a.myapplication.bean.MyOrderModer;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.Preference;
import com.example.a.myapplication.util.ThreadManager;
import com.example.a.myapplication.view.LoadingPager;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.BOTH;

/**
 * Created by Administrator on 2016/12/22.
 */
public class OrderFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    View view;


    @InjectView(R.id.swipeToLoadLayout)
    protected RelativeLayout fragment_stylist_sl;

    @InjectView(R.id.pull_layout)
    public PullToRefreshListView pullListView;

    public static final String ARGS_PAGE = "args_page";
    public static final String ARGS_TYPE = "arge_type";
    public int page = 1;
    private MyOrderAdapter adapter;
    MyOrderModer orderModer;
    int position;

    public static OrderFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(ARGS_TYPE, position);
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        if (null != adapter) {
            adapter.setDatas(orderModer.getO());
        }
        pullListView.onRefreshComplete();
        pullListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(final PullToRefreshBase<ListView> refreshView) {
                ThreadManager.getLongPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        initDate();
                        refreshView.post(new Runnable() {
                            @Override
                            public void run() {
                                adapter.getDatas().clear();
                                if (null != orderModer.getO()) {
                                    adapter.getDatas().addAll(orderModer.getO());
                                    adapter.notifyDataSetChanged();
                                }
                                refreshView.onRefreshComplete();
                            }
                        });
                    }
                });
            }

            @Override
            public void onPullUpToRefresh(final PullToRefreshBase<ListView> refreshView) {
                ThreadManager.getLongPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        page = page + 1;
                        initDate();
                        refreshView.post(new Runnable() {
                            @Override
                            public void run() {
                                if (null != orderModer.getO()) {
                                    adapter.getDatas().addAll(orderModer.getO());
                                    adapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(getActivity(), "没有更多数据了！", Toast.LENGTH_SHORT).show();
                                }
                                refreshView.onRefreshComplete();
                            }
                        });
                    }
                });
            }
        });

        pullListView.getRefreshableView().setOnItemClickListener(this);
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
        par.put("uid", Preference.get(Config.ID, ""));
        par.put("type", position + "");
        par.put("pagination", String.valueOf(page));
        par.put("pagelen", "10");
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Bundle bundle = new Bundle();
        bundle.putString("id", orderModer.getO().get(position - 1).getId());
        CommonUtils.startIntent(getActivity(), MyOrderDetailsActivity.class, bundle);
    }
}
