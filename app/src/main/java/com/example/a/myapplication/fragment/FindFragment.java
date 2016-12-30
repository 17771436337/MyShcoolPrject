package com.example.a.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.MyFragmentPagerAdapter;
import com.example.a.myapplication.util.Config;

import butterknife.ButterKnife;
import butterknife.InjectView;

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
    private String[] titles = new String[]{"求单品", "求风格"};
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
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager(),getActivity(),titles, Config.QITTMELIST,"");
        fragment_find_vp.setAdapter(adapter);
        fragment_find_tal.setupWithViewPager(fragment_find_vp);
    }

}
