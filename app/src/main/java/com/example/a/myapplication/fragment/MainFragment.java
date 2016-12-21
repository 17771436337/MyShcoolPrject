package com.example.a.myapplication.fragment;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a.myapplication.R;
import com.example.a.myapplication.activity.MessageActvity;
import com.example.a.myapplication.activity.RecommendListActivity;
import com.example.a.myapplication.adapter.MainFragmentAdapter;
import com.example.a.myapplication.util.CommonUtils;
import com.wirelesspienetwork.overview.model.OverviewAdapter;
import com.wirelesspienetwork.overview.model.ViewHolder;
import com.wirelesspienetwork.overview.views.Overview;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static android.app.SearchManager.INTENT_GLOBAL_SEARCH_ACTIVITY_CHANGED;
import static android.content.Intent.ACTION_SCREEN_OFF;
import static android.graphics.Color.argb;
import static android.view.View.inflate;
import static android.widget.Toast.makeText;
import static com.wirelesspienetwork.overview.misc.Utilities.setShadowProperty;
import static java.lang.String.valueOf;


public class MainFragment extends Fragment implements Overview.RecentsViewCallbacks {


    View view;
    boolean mVisible;
    @InjectView(R.id.title_text)
    protected TextView titleText;


    @InjectView(R.id.listView)
    protected RecyclerView listView;
    private MainFragmentAdapter mListAdapter;
    private List<String> mDatas = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_main, container, false);
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
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mDatas.add("wusdasda");
        mDatas.add(new String("嘻哈"));
        mDatas.add(new String("嘻哈"));
        mDatas.add(new String("嘻哈"));
        mDatas.add(new String("嘻哈"));

        mListAdapter = new MainFragmentAdapter(getActivity(), mDatas);
        // 设置item动画
        listView.setItemAnimator(new DefaultItemAnimator());
        listView.setAdapter(mListAdapter);


        mListAdapter.setOnItemClickListener(new MainFragmentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                CommonUtils.startIntent(getActivity(), RecommendListActivity.class);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    /**
     * 设置标题
     */
    private void initTitleView() {
        titleText.setText("首页");


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


    @Override
    public void onResume() {
        super.onResume();

    }

    /**
     * 侧滑删除监听
     */
    @Override
    public void onCardDismissed(int position) {
        makeText(getActivity(), position + "", 1000).show();

    }

    @Override
    public void onAllCardsDismissed() {
        makeText(getActivity(), "这是一个什么", 1000).show();
    }
}
