package com.example.a.myapplication.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.a.myapplication.MainActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.activity.MessageActvity;
import com.example.a.myapplication.activity.ProductDetailsActivity;
import com.example.a.myapplication.activity.screen.ScreenActivity;
import com.example.a.myapplication.adapter.MyFragmentPagerAdapter;
import com.example.a.myapplication.adapter.MyRecyclerCommonAdapter;
import com.example.a.myapplication.bean.RecommendModel;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.ScreenUtils;
import com.example.a.myapplication.view.MyLoadMoreRecyclerView;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static android.graphics.Color.TRANSPARENT;


/**
 * 发现
 */
public class FindFragment extends Fragment {
    View view;
    @InjectView(R.id.fragment_find_tal)
    protected TabLayout fragment_find_tal;
    @InjectView(R.id.fragment_find_vp)
    protected ViewPager fragment_find_vp;
    @InjectView(R.id.title_text)
    protected TextView title_text;
    @InjectView(R.id.message_icon)
    protected ImageView message_icon;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_find, container, false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.inject(this, view);
        initTitleView();
    }
    private void initTitleView() {
        title_text.setText("设计师");
        message_icon.setImageResource(R.drawable.icon_screen);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager(),  getActivity());
        fragment_find_vp.setAdapter(adapter);
        fragment_find_tal.setupWithViewPager(fragment_find_vp);

    }

}
