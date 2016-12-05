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
import com.example.a.myapplication.view.LoadingPager.LoadedResult;


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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.loadData();
    }

    protected LoadedResult checkData(Object data) {
        if (data == null) {
            return LoadedResult.EMPTY;
        }
        if (data instanceof List) {
            if (((List) data).size() == 0) {
                return LoadedResult.EMPTY;
            }
        }
        if (data instanceof Map) {
            if (((Map) data).size() == 0) {
                return LoadedResult.EMPTY;
            }
        }
        return LoadedResult.SUCCESS;
    }

    public void loadData() {
        if (mLoadingPager != null) {
            mLoadingPager.loadData();
        }
    }


    protected abstract View onLoadSuccessView();

    protected abstract LoadedResult onLoadingData();

    public void initIcon(TextView mTextView) {
        Typeface iconfont = Typeface.createFromAsset(UIUtils.getContext().getAssets(), "iconfont.ttf");
        mTextView.setTypeface(iconfont);
    }
}
