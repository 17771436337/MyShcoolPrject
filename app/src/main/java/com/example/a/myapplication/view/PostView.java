package com.example.a.myapplication.view;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.a.myapplication.BaseView;
import com.example.a.myapplication.R;


public class PostView extends BaseView implements OnClickListener {

	LinearLayout skills;
	LinearLayout goods;
	LinearLayout demand;
	// ----------
	LinearLayout demand_novice;
	LinearLayout skills_novice;
	LinearLayout goods_novice;

	// -----
	RelativeLayout all;
	ImageView post_image;

	public PostView(Context context) {
		super(context);
	}

	@Override
	protected int getLayoutID() {
		return R.layout.view_post;
	}

	@Override
	protected void initView() {
		all = (RelativeLayout) mView.findViewById(R.id.all);
		all.setOnClickListener(this);
		post_image = (ImageView) mView.findViewById(R.id.post_image);
		post_image.setOnClickListener(this);
	}

	public void show() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.post_image:// 显示图标
			all.setVisibility(View.GONE);
			break;

		default:// 隐藏当前视图
			all.setVisibility(View.GONE);
			break;
		}

	}

}
