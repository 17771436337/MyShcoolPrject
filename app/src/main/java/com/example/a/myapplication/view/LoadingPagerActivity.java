package com.example.a.myapplication.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.a.myapplication.R;
import com.example.a.myapplication.util.ThreadManager;
import com.example.a.myapplication.util.UIUtils;


public abstract class LoadingPagerActivity extends FrameLayout implements OnClickListener {
    public final static int STATE_NONE = 0;            // 空的状态
    private final static int STATE_LOADING = 1;            // 加载中的状态
    private final static int STATE_EMPTY = 2;            // 空的状态
    private final static int STATE_ERROR = 3;            // 错误的状态
    private final static int STATE_SUCCESS = 4;            // 成功的状态
    private View mLoadingView;
    private View mEmptyView;
    private View mErrorView;
    private View mSuccessView;
    //返回
    private View imageView;
    private Activity activity;
    //标题
    private TextView mTvtitle;
    private TextView mTvLoadtitle;
    private TextView mTvErrortitle;
    private TextView mBack;
    public String mTitlt;
    /**
     * 属性空页面
     */
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

    public LoadingPagerActivity(Context context,String mTitlt) {
        super(context);
        this.mTitlt=mTitlt;
        activity = (Activity) context;
        initView();
    }

    public LoadingPagerActivity(Context context, AttributeSet attrs) {
        super(context, attrs);
        activity = (Activity) context;
        initView();
    }

    //设置页面的标题
    public void setTitle(String title) {
        mTvtitle.setText(title);
        mTvLoadtitle.setText(title);
        mTvErrortitle.setText(title);
    }

    private void initView() {
        // 加载 （加载中，空页面，错误界面，成功）
        if (mLoadingView == null) {
            mLoadingView = View.inflate(getContext(), R.layout.activity_pager_loading, null);
            // 添加到容器中
            addView(mLoadingView);
            mTvLoadtitle = (TextView) mLoadingView.findViewById(R.id.back_image);
            inintBack(mLoadingView);
        }

        // 空页面
        if (mEmptyView == null) {
            mEmptyView = View.inflate(getContext(), R.layout.activity_pager_empty, null);
            // 添加到容器中
            addView(mEmptyView);
            inintBack(mEmptyView);
        }

        // 错误界面
        if (mErrorView == null) {
            mErrorView = View.inflate(getContext(), R.layout.activity_pager_error, null);
            // 添加到容器中
            addView(mErrorView);
            mRetryView = mErrorView.findViewById(R.id.error_btn_retry);
            mRetryView.setOnClickListener(this);
            inintBack(mErrorView);
            mTvErrortitle = (TextView) mErrorView.findViewById(R.id.title_text);
        }
        // 成功页面等数据加载成功后添加
        // 通过状态更新View的显示
        safeUpdateUI();
    }

    /**
     * 初始化返回键
     */
    private void inintBack(View view) {
        mBack = (TextView) view.findViewById(R.id.back_image);
        mTvtitle = (TextView) view.findViewById(R.id.title_text);
        mBack.setText(getResources().getString(R.string.icon_ht));
         initIcon(mBack);
         mBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
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

    public void initIcon(TextView mTextView) {
        Typeface iconfont = Typeface.createFromAsset(UIUtils.getContext().getAssets(), "iconfont.ttf");
        mTextView.setTypeface(iconfont);

    }
}
