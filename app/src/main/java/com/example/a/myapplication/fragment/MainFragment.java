package com.example.a.myapplication.fragment;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a.myapplication.R;
import com.example.a.myapplication.activity.MessageActvity;
import com.example.a.myapplication.activity.RecommendListActivity;
import com.example.a.myapplication.util.CommonUtils;
import com.wirelesspienetwork.overview.model.OverviewAdapter;
import com.wirelesspienetwork.overview.model.ViewHolder;
import com.wirelesspienetwork.overview.views.Overview;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static android.app.SearchManager.INTENT_GLOBAL_SEARCH_ACTIVITY_CHANGED;
import static android.content.Intent.ACTION_SCREEN_OFF;
import static android.graphics.Color.argb;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
import static android.view.View.inflate;
import static android.widget.Toast.makeText;
import static com.wirelesspienetwork.overview.misc.Utilities.setShadowProperty;
import static java.lang.String.valueOf;


public class MainFragment extends Fragment implements Overview.RecentsViewCallbacks {


    View view;
    boolean mVisible;
    @InjectView(R.id.title_text)
    protected TextView titleText;

    @InjectView(R.id.recents_view)
    protected Overview mRecentsView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, view);
        initTitleView();
        initContent();
        return view;
    }

    /**
     * 卡片内容
     */
    private void initContent() {
        mRecentsView.setCallbacks(this);
//        mRecentsView.setSystemUiVisibility(SYSTEM_UI_FLAG_LAYOUT_STABLE |
//                SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
//                SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        // Register the broadcast receiver to handle messages when the screen is turned off
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_SCREEN_OFF);
        filter.addAction(INTENT_GLOBAL_SEARCH_ACTIVITY_CHANGED);

        // Private API calls to make the shadows look better
        try {
            setShadowProperty("ambientRatio", valueOf(1.5f));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        mVisible = true;

        ArrayList<Integer> models = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            Random random = new Random();
            random.setSeed(i);
            int color = argb(225, random.nextInt(255), random.nextInt(255), random.nextInt(255));
            models.add(color);
        }

        final OverviewAdapter stack = new OverviewAdapter<ViewHolder<View, Integer>, Integer>(models) {
            @Override
            public ViewHolder onCreateViewHolder(Context context, ViewGroup parent) {
                View v = inflate(context, R.layout.recents_dummy, null);
                return new ViewHolder<View, Integer>(v);
            }

            @Override
            public void onBindViewHolder(ViewHolder<View, Integer> viewHolder) {
                viewHolder.itemView.setBackgroundColor(viewHolder.model);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CommonUtils.startIntent(getActivity(),RecommendListActivity.class);
                    }
                });
            }
        };

        mRecentsView.setTaskStack(stack);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                stack.notifyDataSetInserted(new Integer(1), 2);
//            }
//        },2000);
    }

    /**
     * 设置标题
     */
    private void initTitleView() {
        titleText.setText("首页");


    }


    @OnClick({R.id.message_icon})
    public void OnClic(View v) {
        switch (v.getId()) {

            case R.id.message_icon://消息

                CommonUtils.startIntent(getActivity(),MessageActvity.class);
                break;
        }
    }


    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    /**
     * 侧滑删除监听
     */
    @Override
    public void onCardDismissed(int position) {
        makeText(getActivity(), position + "", 1000).show();

    }

    @Override
    public void onAllCardsDismissed() {
        makeText(getActivity(), "这是一个什么", 1000).show();
    }
}
