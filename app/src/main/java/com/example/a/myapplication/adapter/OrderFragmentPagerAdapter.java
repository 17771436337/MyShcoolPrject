package com.example.a.myapplication.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.a.myapplication.fragment.OrderFragment;

/**
 * Created by Administrator on 2016/12/22.
 */
public class OrderFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] titles = new String[]{"全部", "待付款", "待发货", "待收货"};
    private Context context;

    public OrderFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            position = 5;
        }
        if (position == 1) {
            position = 0;
        }
        if (position == 2) {
            position = 1;
        }
        if (position == 3) {
            position = 2;
        }
        return OrderFragment.newInstance(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return titles.length;
    }
}
