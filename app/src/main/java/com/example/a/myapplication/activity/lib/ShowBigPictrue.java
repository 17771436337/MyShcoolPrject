package com.example.a.myapplication.activity.lib;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.RelativeLayout;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.fragment.PictrueFragment;
import com.example.a.myapplication.lib.ScaleView.ScaleView.HackyViewPager;
import com.example.a.myapplication.view.TitleView1;


/**
 * 显示大图界面
 *
 * @author https://shop262893968.taobao.com/?spm=a230r.7195193.1997079397.2.8JmOqz
 */
public class ShowBigPictrue extends BaseActivity {

    private HackyViewPager viewPager;
    private int[] resId = {R.drawable.detail_show_1, R.drawable.detail_show_2, R.drawable.detail_show_3, R.drawable.detail_show_4, R.drawable.detail_show_5, R.drawable.detail_show_6};
    /**
     * 得到上一个界面点击图片的位置
     */
    private int position = 0;

    @Override
    protected int getLayoutID() {
        return R.layout.show_big_pictrue_a;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        initTitle();
        initViewPager();
    }

    @Override
    protected void initData() {

    }

    private void initTitle() {
        RelativeLayout title = (RelativeLayout) findViewById(R.id.title_layout);
        TitleView1 view = new TitleView1(this);
        title.addView(view.getView());
        view.setTitleText("查看图片", "");

    }

    private void initViewPager() {

        viewPager = (HackyViewPager) findViewById(R.id.viewPager_show_bigPic);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        //跳转到第几个界面
        viewPager.setCurrentItem(position);

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            int show_resId = resId[position];
            return new PictrueFragment(show_resId);
        }

        @Override
        public int getCount() {
            return resId.length;
        }


    }

}
