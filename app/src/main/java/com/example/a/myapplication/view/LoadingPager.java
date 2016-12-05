package com.example.a.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;

import com.example.a.myapplication.R;
import com.example.a.myapplication.util.ThreadManager;
import com.example.a.myapplication.util.UIUtils;


public abstract class LoadingPager extends FrameLayout implements OnClickListener {
    public final static int STATE_NONE = 0;            // 空的状态
    private final static int STATE_LOADING = 1;            // 加载中的状态
    private final static int STATE_EMPTY = 2;            // 空的状态
    private final static int STATE_ERROR = 3;            // 错误的状态
    private final static int STATE_SUCCESS = 4;            // 成功的状态
    private View mLoadingView;
    private View mEmptyView;
    private View mErrorView;
    private View mSuccessView;
    /**
     * 属性空页面
     * */
    public View getmEmptyView() {
        return mEmptyView;
    }

    public View getmLoadingView() {
        return mLoadingView;
    }

    public View getmErrorView() {
        return mErrorView;
    }

    private View mRetryView;

    private int mCurrentState = STATE_NONE;    // 默认是加载中的状态

    public void setmCurrentState(int mCurrentState) {
        this.mCurrentState = mCurrentState;
    }

    public LoadingPager(Context context) {
        super(context);
        initView();
    }

    public LoadingPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        // 加载 （加载中，空页面，错误界面，成功）

        // 加载中
        if (mLoadingView == null) {
            mLoadingView = View.inflate(getContext(), R.layout.pager_loading, null);
            // 添加到容器中
            addView(mLoadingView);
        }

        // 空页面
        if (mEmptyView == null) {
            mEmptyView = View.inflate(getContext(), R.layout.pager_empty, null);
/*
            mEmptyView.findViewById(R.id.rl_title)
*/
            // 添加到容器中
            addView(mEmptyView);
        }

        // 错误界面
        if (mErrorView == null) {
            mErrorView = View.inflate(getContext(), R.layout.pager_error, null);
            // 添加到容器中
            addView(mErrorView);
            mRetryView = mErrorView.findViewById(R.id.error_btn_retry);
            mRetryView.setOnClickListener(this);
        }
        // 成功页面等数据加载成功后添加
        // 通过状态更新View的显示
        safeUpdateUI();
    }

    private void safeUpdateUI() {
        UIUtils.post(new Runnable() {

            @Override
            public void run() {
                updateUI();
            }
        });
    }

    private void updateUI() {
        mLoadingView.setVisibility((mCurrentState == STATE_NONE || mCurrentState == STATE_LOADING) ? View.VISIBLE : View.GONE);
        mEmptyView.setVisibility(mCurrentState == STATE_EMPTY ? View.VISIBLE : View.GONE);
        mErrorView.setVisibility(mCurrentState == STATE_ERROR ? View.VISIBLE : View.GONE);
        if (mCurrentState == STATE_SUCCESS && mSuccessView == null) {
            // 需要创造成功的View
            mSuccessView = initSuccessView();

            // 添加到容器中
            addView(mSuccessView);
        }

        // 成功的view
        if (mSuccessView != null) {
            mSuccessView.setVisibility(mCurrentState == STATE_SUCCESS ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 加载数据
     */
    public void loadData() {
        // 如果现在是成功状态就不去加载
        if (mCurrentState != STATE_SUCCESS && mCurrentState != STATE_LOADING) {
            System.out.println("####开线程去加载数据###");

            mCurrentState = STATE_LOADING;
            safeUpdateUI();
            //创建的线程
            ThreadManager.getLongPool().execute(new LoadDataTask());
        }
    }

    /**
     * 让子类实现
     *
     * @return
     */
    protected abstract View initSuccessView();

    protected abstract LoadedResult onLoadData();

    class LoadDataTask implements Runnable {

        @Override
        public void run() {
            // 去加载数据
            // 数据加载成功没有
            LoadedResult result = onLoadData();
            // 负责view切换---》 state1
            mCurrentState = result.getState();

            // 在子线程中执行ui操作
            safeUpdateUI();
        }
    }
    public enum LoadedResult {
        EMPTY(STATE_EMPTY), ERROR(STATE_ERROR), SUCCESS(STATE_SUCCESS);
        private int state;

        private LoadedResult(int state) {
            this.state = state;
        }

        public int getState() {
            return state;
        }
    }
    @Override
    public void onClick(View v) {
        if (v == mRetryView) {
            loadData();
        }
    }
}
