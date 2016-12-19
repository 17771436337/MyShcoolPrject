package com.example.a.myapplication.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a.myapplication.R;
import com.example.a.myapplication.activity.AllcommentActivity;
import com.example.a.myapplication.activity.ProductDetailsTwoActivity;
import com.example.a.myapplication.util.CommonUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/13.
 */
public class CheckProductFragment extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_check_shop, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.inject(this, view);


    }


    @OnClick({R.id.one_layout, R.id.two_layout, R.id.three_layout, R.id.message})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.one_layout://第一行 /
                CommonUtils.startIntent(getActivity(), ProductDetailsTwoActivity.class);

                break;
            case R.id.two_layout://第二行       /
                CommonUtils.startIntent(getActivity(), ProductDetailsTwoActivity.class);
                break;
            case R.id.three_layout://第三行     /
                CommonUtils.startIntent(getActivity(), ProductDetailsTwoActivity.class);
                break;
            case R.id.message:
                CommonUtils.startIntent(getActivity(),AllcommentActivity.class);
                break;
        }

    }
}
