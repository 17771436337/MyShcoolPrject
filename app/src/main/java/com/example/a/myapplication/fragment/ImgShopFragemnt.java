package com.example.a.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.a.myapplication.R;
import com.example.a.myapplication.activity.lib.ShowBigPictrue;
import com.example.a.myapplication.lib.ScaleView.ScaleView.HackyViewPager;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/13.
 * 单品图片显示
 */
public class ImgShopFragemnt extends Fragment {
    View view;

    @InjectView(R.id.listView_ware)
    protected HackyViewPager listView;
    private ArrayList<View> allListView;
    /**ViewPager当前显示页的下标*/
    private int position=0;

    private int[] resId = { R.drawable.detail_show_1, R.drawable.detail_show_2, R.drawable.detail_show_3, R.drawable.detail_show_4, R.drawable.detail_show_5, R.drawable.detail_show_6 };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_img_shop,null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.inject(this,view);

        initImgView();
    }

    private void initImgView() {
        if (allListView != null) {
            allListView.clear();
            allListView = null;
        }
        allListView = new ArrayList<View>();

        for (int i = 0; i < resId.length; i++) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.pic_item, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.pic_item);
            imageView.setImageResource(resId[i]);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    //挑战到查看大图界面
                    Intent intent = new Intent(getActivity(), ShowBigPictrue.class);
                    intent.putExtra("position", position);
                    startActivity(intent);
                }
            });
            allListView.add(view);
        }
        ViewPagerAdapter adapter = new ViewPagerAdapter();
        listView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                position=arg0;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
        listView.setAdapter(adapter);
    }

    private class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return allListView.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == (View) arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = allListView.get(position);
            container.addView(view);
            return view;
        }

    }

}
