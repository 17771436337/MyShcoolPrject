package com.example.a.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ViewUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a.myapplication.BaseFragment;
import com.example.a.myapplication.R;
import com.example.a.myapplication.view.LoadingPager;


/**
 * 发现
 * */
public class FindFragment extends BaseFragment {
	View mView;
	@Override
	public View onCreateView(LayoutInflater inflater,
							 @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		mView=inflater.inflate(R.layout.fragment_find, container, false);
		return mView;

	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	protected View onLoadSuccessView() {
		return mView;
	}

	@Override
	protected LoadingPager.LoadedResult onLoadingData() {
		return LoadingPager.LoadedResult.SUCCESS;
	}


}
