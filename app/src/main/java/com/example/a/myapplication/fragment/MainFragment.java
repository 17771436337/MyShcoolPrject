package com.example.a.myapplication.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a.myapplication.R;
import com.example.a.myapplication.activity.MessageActvity;
import com.example.a.myapplication.adapter.MainFragmentAdapter;
import com.example.a.myapplication.adapter.MainFragmentListAdapter;
import com.example.a.myapplication.bean.MainFragmentModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainFragment extends Fragment {


    View view;
    boolean mVisible;
    @InjectView(R.id.title_text)
    protected TextView titleText;


    @InjectView(R.id.listView)
    protected PullToRefreshListView listView;
    private MainFragmentAdapter mListAdapter;
    private MainFragmentListAdapter adapter;
    private MainFragmentModel model = new MainFragmentModel();


    private int page = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_main, null);
        ButterKnife.inject(this, view);
        initTitleView();
//        initContent();

        initList();
        return view;
    }

    /**
     * 添加内容
     */
    private void initList() {
        getData();
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new MainFragmentListAdapter(listView, model.getO());
        listView.setAdapter(adapter);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                getData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                getData();
            }
        });

    }


    /**
     * 设置标题
     */
    private void initTitleView() {
        titleText.setText("NO没");


    }


    @OnClick({R.id.message_icon})
    public void OnClic(View v) {
        switch (v.getId()) {

            case R.id.message_icon://消息

                CommonUtils.startIntent(getActivity(), MessageActvity.class);
                break;
        }
    }


    @Override
    public void onStart() {
        super.onStart();
    }


    private void getData() {
        Map<String, String> parm = CommonUtils.getMapParm();
        parm.put("pagination", String.valueOf(page));
        parm.put("pagelen", Config.listCount);
        OkHttpUtil.getInstance().addRequestPost(Config.getExclusives, parm, new OkHttpUtil.HttpCallBack<MainFragmentModel>() {
            @Override
            public void onSuccss(MainFragmentModel mainFragmentModel) {

                Message message = new Message();
                message.obj = mainFragmentModel;
                message.what = 0x0001;
                handler.sendMessage(message);
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x0001:
                    listView.onRefreshComplete();
                    MainFragmentModel mainFragmentModel = (MainFragmentModel) msg.obj;
                    if (page == 1) {
                        adapter.getmDatas().clear();
                    }

                    model = mainFragmentModel;


                    if (model.getO() != null) {
                        adapter.addData(mainFragmentModel.getO());
                        adapter.notifyDataSetChanged();
                    }

                    break;
            }
        }
    };


}
