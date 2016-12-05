package com.example.a.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.a.myapplication.R;
import com.example.a.myapplication.activity.TestActivity;
import com.example.a.myapplication.util.CommonUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MessageFragment extends Fragment {

	@InjectView(R.id.message_test)
	Button message_test;
	View view;
	@Override
	public View onCreateView(LayoutInflater inflater,
							 @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		view=inflater.inflate(R.layout.fragment_main, container, false);
		ButterKnife.inject(this, view);
		return view;
	}


	@OnClick(R.id.message_test)
	public void testActivity(){
		CommonUtils.startIntent(getActivity(), TestActivity.class);
	}

}
