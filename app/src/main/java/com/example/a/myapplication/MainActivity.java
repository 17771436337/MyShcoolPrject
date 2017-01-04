package com.example.a.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.a.myapplication.fragment.FindFragment;
import com.example.a.myapplication.fragment.MainFragment;
import com.example.a.myapplication.fragment.MineFragment;
import com.example.a.myapplication.fragment.TestFragment;
import com.example.a.myapplication.fragment.WardrobeFragment;
import com.example.a.myapplication.util.ScreenUtils;
import com.example.a.myapplication.view.BottomView;

import butterknife.InjectView;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    private FragmentTransaction mFragmentTransaction;// 碎片的事物
    private FragmentManager mFragmentManager;// 碎片管理器

    private int currentPostion;

    private MainFragment mainFragment;
    private WardrobeFragment findFragment;
    private FindFragment tradeFragment;
    private MineFragment mineFragment;
    private TestFragment testFragment;

    private MainFragment mainCatchFragment;
    private WardrobeFragment findCatchFragment;
    private FindFragment tradeCatchFragment;
    private MineFragment mineCatchFragment;
    private TestFragment testCatchFragment;

    private RelativeLayout mBottomLayout;
    private BottomView mBottomView;


    @InjectView(R.id.realtabcontent)
    protected FrameLayout realtabcontent;

    public static MainActivity mainActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ScreenUtils.initScreen(this);
        initFragment();
        showContent();
    }


    @Override
    public void initView() {
        // setContentView(R.layout.activity_mainactivty);
        mBottomLayout = (RelativeLayout) findViewById(R.id.bottom_layout);
        mBottomView = new BottomView(this);
        mBottomLayout.addView(mBottomView.getView());
        mBottomView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                currentPostion = arg2;
                showContent();
            }
        });


    }

    @Override
    protected void initData() {

    }

    private void initFragment() {
        this.mainFragment = (MainFragment) Fragment.instantiate(this,
                MainFragment.class.getName(), null);
        this.findFragment = (WardrobeFragment) Fragment.instantiate(this,
                WardrobeFragment.class.getName(), null);
        this.tradeFragment = (FindFragment) Fragment.instantiate(this,
                FindFragment.class.getName(), null);
        this.mineFragment = (MineFragment) Fragment.instantiate(this,
                MineFragment.class.getName(), null);

        this.testFragment = (TestFragment) Fragment.instantiate(this, TestFragment.class.getName(), null);
    }

    private void showContent() {
        if (this.mFragmentManager == null) {
            this.mFragmentManager = this.getSupportFragmentManager();
        }
        this.mFragmentTransaction = this.mFragmentManager.beginTransaction();
        this.getCacheFragement();

        if (currentPostion == 0) {
            this.setCurrentFragment(mainCatchFragment, mainFragment, "tab1");

        } else if (currentPostion == 1) {
            this.setCurrentFragment(findCatchFragment, findFragment, "tab2");

        } else if (currentPostion == 2) {
            this.setCurrentFragment(testCatchFragment, testFragment, "tab3");
//            this.setCurrentFragment(tradeCatchFragment, tradeFragment, "tab4");

        } else if (currentPostion == 3) {

            this.setCurrentFragment(mineCatchFragment, mineFragment, "tab5");
        }
        this.mFragmentTransaction.commit();
        this.mFragmentManager.executePendingTransactions();

    }

    private void getCacheFragement() {
        this.mainCatchFragment = (MainFragment) getCacheFragment("tab1");
        this.findCatchFragment = (WardrobeFragment) getCacheFragment("tab2");
        this.tradeCatchFragment = (FindFragment) getCacheFragment("tab4");
        this.mineCatchFragment = (MineFragment) getCacheFragment("tab5");
        this.testCatchFragment = (TestFragment) getCacheFragment("tab3");


        this.hideFragment(mainCatchFragment);
        this.hideFragment(findCatchFragment);
        this.hideFragment(tradeCatchFragment);
        this.hideFragment(mineCatchFragment);
        this.hideFragment(testCatchFragment);
    }

    private Fragment getCacheFragment(String tag) {
        return this.mFragmentManager.findFragmentByTag(tag);
    }

    private void hideFragment(Fragment aHideFragment) {
        if (aHideFragment != null) {
            this.mFragmentTransaction.hide(aHideFragment);
        }
    }

    private void setCurrentFragment(Fragment aCache, Fragment aCurrt, String tag) {
        if (aCache == null) {
            this.mFragmentTransaction.add(R.id.realtabcontent, aCurrt, tag);
        } else {
            mFragmentTransaction.show(aCache);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mainFragment != null)
            mainFragment.onActivityResult(requestCode, resultCode, data);
        if (findCatchFragment != null)
            findCatchFragment.onActivityResult(requestCode, resultCode, data);
        if (tradeCatchFragment != null)
            tradeCatchFragment.onActivityResult(requestCode, resultCode, data);
        if (mineFragment != null)
            mineFragment.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_mainactivty;
    }

    @Override
    public void onClick(View view) {

    }


}
