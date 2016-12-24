package com.example.a.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a.myapplication.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/24.
 */
public class FavorableNotFragment extends Fragment {
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favorable_not, null);
        ButterKnife.inject(this, view);
        return view;
    }
}
