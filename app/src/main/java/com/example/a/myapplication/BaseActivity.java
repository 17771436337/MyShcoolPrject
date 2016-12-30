package com.example.a.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.myapplication.view.LoadingPagerActivity;
import com.example.a.myapplication.view.TitleView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import butterknife.ButterKnife;

import static android.content.pm.ActivityInfo.*;


public abstract class BaseActivity extends FragmentActivity {
    Intent mIntent;
    public int resultCcode;
    ViewGroup mView;
    // 对所有的activity进行管理
    protected static List<Activity> mActivities = new LinkedList<Activity>();
    private static Activity mCurrentActivity;
    protected TitleView mTitleView;
    public LoadingPagerActivity mLoadingPagerActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        // LocalDisplay.init(this);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
        synchronized (mActivities) {
            mActivities.add(this);
        }
        mView = (ViewGroup) LayoutInflater.from(this).inflate(getLayoutID(), null);
        addTitle();
        ButterKnife.inject(this, mView);
        // 初始化view
        initView();

        // 初始化数据
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        synchronized (mActivities) {
            mActivities.remove(this);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        BaseApplication.mCurrentActivity = this;
        // 横屏设置
        if (getRequestedOrientation() != SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);
        }

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT&& !(this instanceof MainActivity)) {
//            setTranslucentStatus(true);
//            SystemBarTintManager tintManager = new SystemBarTintManager(this);
//            tintManager.setStatusBarTintEnabled(true);
//            tintManager.setStatusBarTintResource(R.color.top_bg_color);
//        }

    }

    protected abstract int getLayoutID();

    protected void addTitle() {
        mTitleView = new TitleView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        // 设置广告出现的位置(悬浮于顶部)
        params.topMargin = 0;
        params.gravity = Gravity.TOP;
        //  mView.addView(mTitleView.getView(),params);
        setContentView(mView);
        // addContentView(mTitleView.getView(), params);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCurrentActivity = null;
        EventBus.getDefault().unregister(this);
    }

    public static void exitApp() {
        ListIterator<Activity> iterator = mActivities.listIterator();
        while (iterator.hasNext()) {
            Activity next = iterator.next();
            next.finish();
        }
    }

    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 初始化icon字体
     */
    public void initIcon(TextView mTextView) {
        Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfont.ttf");
        mTextView.setTypeface(iconfont);
    }

    public static Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    protected abstract void initView();


    protected abstract void initData();

    protected LoadingPagerActivity.LoadedResult checkData(Object data) {
        if (data == null) {
            return LoadingPagerActivity.LoadedResult.EMPTY;
        }
        if (data instanceof List) {
            if (((List) data).size() == 0) {
                return LoadingPagerActivity.LoadedResult.EMPTY;
            }
        }
        if (data instanceof Map) {
            if (((Map) data).size() == 0) {
                return LoadingPagerActivity.LoadedResult.EMPTY;
            }
        }
        return LoadingPagerActivity.LoadedResult.SUCCESS;
    }

    public static Activity getmCurrentActivity() {
        return mCurrentActivity;
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (isTaskRoot()) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    Toast.makeText(getApplicationContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    finish();
                    System.exit(0);
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onStart() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        super.onStart();
    }

    /**
     * 初始化icon字体
     */
    public void initIconFont(TextView mTextView) {
        Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfont.ttf");
        mTextView.setTypeface(iconfont);
    }

    /*以下为：事件分发*/
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onEvent(Object obj) {

        //接收消息-发送的线程
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Object obj) {
        //接收消息-主线程
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEventBackgroundThread(Object obj) {
        //接收消息-后台线程
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEventAsyncThread(Object obj) {
        //接收消息-异步线程
    }


}
