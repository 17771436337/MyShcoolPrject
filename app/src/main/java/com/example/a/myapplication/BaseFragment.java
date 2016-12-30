package com.example.a.myapplication;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import com.example.a.myapplication.util.UIUtils;
import com.example.a.myapplication.view.LoadingPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Map;

public abstract class BaseFragment extends Fragment {
    /**
     * 要设置的数据
     */
    public LoadingPager mLoadingPager;
    protected LayoutInflater inflater;
    protected List mDatas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        this.inflater = inflater;

        if (mLoadingPager == null) {
            mLoadingPager = new LoadingPager(getActivity()) {
                @Override
                protected View initSuccessView() {
                    return onLoadSuccessView();
                }

                @Override
                protected LoadedResult onLoadData() {
                    return onLoadingData();
                }
            };
        } else {
            ViewParent parent = mLoadingPager.getParent();
            if (parent != null && parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(mLoadingPager);
            }
        }

        return mLoadingPager;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.loadData();
    }

    protected LoadingPager.LoadedResult checkData(Object data) {
        if (data == null) {
            return LoadingPager.LoadedResult.EMPTY;
        }
        if (data instanceof List) {
            if (((List) data).size() == 0) {
                return LoadingPager.LoadedResult.EMPTY;
            }
        }
        if (data instanceof Map) {
            if (((Map) data).size() == 0) {
                return LoadingPager.LoadedResult.EMPTY;
            }
        }
        return LoadingPager.LoadedResult.SUCCESS;
    }

    public void loadData() {
        if (mLoadingPager != null) {
            mLoadingPager.loadData();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (EventBus.getDefault().isRegistered(this)) {
                              EventBus.getDefault().unregister(this);
        }

    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    protected abstract View onLoadSuccessView();

    protected abstract LoadingPager.LoadedResult onLoadingData();

    public void initIcon(TextView mTextView) {
        Typeface iconfont = Typeface.createFromAsset(UIUtils.getContext().getAssets(), "iconfont.ttf");
        mTextView.setTypeface(iconfont);
    }
    /**
     * 初始化icon字体
     */
    public void initIconFont(TextView mTextView) {
        Typeface iconfont = Typeface.createFromAsset(getActivity().getAssets(), "iconfont.ttf");
        mTextView.setTypeface(iconfont);
    }
    /*以下为：事件分发*/
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onEvent(Object obj) {

        //接收消息-发送的线程
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Object obj) {
        //接收消息-主线程
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEventBackgroundThread(Object obj) throws Exception {
        //接收消息-后台线程
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEventAsyncThread(Object obj) {
        //接收消息-异步线程
    }
}
