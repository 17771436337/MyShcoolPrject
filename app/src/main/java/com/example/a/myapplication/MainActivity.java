package com.example.a.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.a.myapplication.fragment.ClubFragment;
import com.example.a.myapplication.fragment.FindFragment;
import com.example.a.myapplication.fragment.MessageFragment;
import com.example.a.myapplication.fragment.MineFragment;
import com.example.a.myapplication.util.ScreenUtils;
import com.example.a.myapplication.view.BottomView;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    private FragmentTransaction mFragmentTransaction;// 碎片的事物
    private FragmentManager mFragmentManager;// 碎片管理器

    private int currentPostion;

    private MessageFragment messageFragment;
    private ClubFragment findFragment;
    private FindFragment tradeFragment;
    private MineFragment mineFragment;

    private MessageFragment mainCatchFragment;
    private ClubFragment findCatchFragment;
    private FindFragment tradeCatchFragment;
    private MineFragment mineCatchFragment;


    private RelativeLayout mBottomLayout;
    private BottomView mBottomView;


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
        this.messageFragment = (MessageFragment) Fragment.instantiate(this,
                MessageFragment.class.getName(), null);
        this.findFragment = (ClubFragment) Fragment.instantiate(this,
                ClubFragment.class.getName(), null);
        this.tradeFragment = (FindFragment) Fragment.instantiate(this,
                FindFragment.class.getName(), null);
        this.mineFragment = (MineFragment) Fragment.instantiate(this,
                MineFragment.class.getName(), null);
    }

    private void showContent() {


        if (currentPostion == 1 || currentPostion == 2 || currentPostion == 3
                || currentPostion == 4) {
        }

        if (this.mFragmentManager == null) {
            this.mFragmentManager = this.getSupportFragmentManager();
        }
        this.mFragmentTransaction = this.mFragmentManager.beginTransaction();
        this.getCacheFragement();
        if (currentPostion == 0) {
            this.setCurrentFragment(mainCatchFragment, messageFragment, "tab1");

        } else if (currentPostion == 1) {
            this.setCurrentFragment(findCatchFragment, findFragment, "tab2");

        } else if (currentPostion == 3) {
            this.setCurrentFragment(tradeCatchFragment, tradeFragment, "tab4");

        } else if (currentPostion == 4) {

            this.setCurrentFragment(mineCatchFragment, mineFragment, "tab5");
        }
        this.mFragmentTransaction.commit();
        this.mFragmentManager.executePendingTransactions();

    }

    private void getCacheFragement() {
        this.mainCatchFragment = (MessageFragment) getCacheFragment("tab1");
        this.findCatchFragment = (ClubFragment) getCacheFragment("tab2");
        this.tradeCatchFragment = (FindFragment) getCacheFragment("tab4");
        this.mineCatchFragment = (MineFragment) getCacheFragment("tab5");


        this.hideFragment(mainCatchFragment);
        this.hideFragment(findCatchFragment);
        this.hideFragment(tradeCatchFragment);
        this.hideFragment(mineCatchFragment);
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
        if (messageFragment != null)
            messageFragment.onActivityResult(requestCode, resultCode, data);
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
