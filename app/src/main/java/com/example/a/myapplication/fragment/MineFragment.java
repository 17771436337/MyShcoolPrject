package com.example.a.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.TestAdapter;
import com.example.a.myapplication.bean.TestModel;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MineFragment extends Fragment {

	TestAdapter mTestAdapter;
    @InjectView(R.id.pull_layout)
	PullToRefreshListView pull_layout;
	TestModel mTestModel;
	View view;
	@Override
	public View onCreateView(LayoutInflater inflater,
							 @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		view=inflater.inflate(R.layout.fragment_mine, container, false);
		return view;
	}
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ButterKnife.inject(this, getView());
		getData();
		initView();

	}


	private void initView() {
		pull_layout.setMode(PullToRefreshBase.Mode.BOTH);
		mTestAdapter = new TestAdapter(pull_layout, mTestModel.getPeos());
		pull_layout.getRefreshableView().setAdapter(mTestAdapter);

		/*mTestAdapter.setOnRereshListListenr(this);
		mListView.getRefreshableView().setAdapter(adapter);
		mListView.setMode(Mode.BOTH);
		mListView
				.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

					@Override
					public void onPullDownToRefresh(
							PullToRefreshBase<ListView> refreshView) {
						page = 1;
						initData();
					}

					@Override
					public void onPullUpToRefresh(
							PullToRefreshBase<ListView> refreshView) {
						page++;
						initData();
					}
				});*/
	}
	/**
	 * 添加数据
	 * @param
     */
	public void getData(){
		mTestModel=new TestModel();
		List<TestModel.Peo> list = new ArrayList<TestModel.Peo>();
		for (int i=0;i<10;i++){
			TestModel.Peo pep=new TestModel.Peo();
			pep.setAge(i+"");
			pep.setName("名字"+i);
			pep.setSex(i+"");
			list.add(pep);
			mTestModel.setPeos(list);
		}
	}



}
