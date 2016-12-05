package com.example.a.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a.myapplication.BaseFragment;
import com.example.a.myapplication.R;
import com.example.a.myapplication.view.LoadingPager;


public class ClubFragment extends BaseFragment {

	View view;
	@Override
	public View onCreateView(LayoutInflater inflater,
							 @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		view=inflater.inflate(R.layout.fragment_club, container, false);

		return view;
	}

	@Override
	protected View onLoadSuccessView() {
		return view;
	}

	@Override
	protected LoadingPager.LoadedResult onLoadingData() {
		return LoadingPager.LoadedResult.SUCCESS;
	}


}
