package com.example.a.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.a.myapplication.fragment.ProductTitleMessageTwoFragment;

import java.util.List;

/**
 * Created by a on 2016/12/23.
 */

public class FragAdapter extends FragmentPagerAdapter {

    private List<ProductTitleMessageTwoFragment> fragments;


    public FragAdapter(FragmentManager fm) {
        super(fm);
    }

    public FragAdapter(FragmentManager fm, List<ProductTitleMessageTwoFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        ProductTitleMessageTwoFragment page = null;
        if (fragments.size() > position) {
            page = fragments.get(position);
            if (page != null) {
                return page;
            }
        }
        while (position>=fragments.size()) {
            fragments.add(null);
        }
        page = ProductTitleMessageTwoFragment.newInstance(fragments.get(position));
        pages.set(position, page);
        return page;
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    public List<ProductTitleMessageTwoFragment> getFragmentList(){
        return  fragments;
    }
}
