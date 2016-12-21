package com.example.a.myapplication.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.a.myapplication.fragment.StylistFragment;

/**
 * Created by a on 2016/12/20.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] titles = new String[]{"求单品", "求风格"};
    private Context context;

    public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return StylistFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}