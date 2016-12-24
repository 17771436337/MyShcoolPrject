package com.example.a.myapplication.activity;

import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.MyCollecAdapter;
import com.example.a.myapplication.bean.MyCollecModer;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.ScreenUtils;
import com.example.a.myapplication.view.TitleView1;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.InjectView;
import butterknife.OnClick;

import static com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.BOTH;

/**
 * Created by Administrator on 2016/12/7.
 * 我的收藏
 */
public class MyCollectActivity extends BaseActivity {

    @InjectView(R.id.pull_layout)
    protected PullToRefreshGridView pullGridView;

    MyCollecModer moder = new MyCollecModer();

    MyCollecAdapter adapter;

    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;


    /**
     * 1风格 2商品
     */
    private int type = 2;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_mycollect;
    }

    @Override
    protected void initView() {
        ScreenUtils.initScreen(this);
        initTitle();
        pullGridView.setMode(BOTH);
        adapter = new MyCollecAdapter(pullGridView, moder.getO());
        pullGridView.getRefreshableView().setAdapter(adapter);
        getData();


    }

    @Override
    protected void initData() {

    }

    /**
     * 标题初始化
     */
    private void initTitle() {
        TitleView1 view = new TitleView1(this);
        titleView.addView(view.getView());
        view.setTitleText("我的收藏", "");
    }


    @OnClick({R.id.shop, R.id.style})
    public void onRadioButtonClicked(RadioButton radioButton) {
        boolean checked = radioButton.isChecked();
        switch (radioButton.getId()) {
            case R.id.shop:
                if (checked) {
                    // code
                    type = 1;
                    getData();
                }
                break;
            case R.id.style:
                if (checked) {
                    // code
                    type = 2;
                    getData();
                }
                break;

        }
    }

    private void getData() {

        Map<String, String> par = new HashMap<String, String>();
        par.put("uid", "2");
        par.put("type", type + "");
        par.put("brands", "");
        par.put("categorys", "");
        par.put("colors", "");
        par.put("populars", "");
        par.put("idols", "");
        OkHttpUtil.getInstance().addRequestPost(Config.myCollect, par, new OkHttpUtil.HttpCallBack<MyCollecModer>() {

            @Override
            public void onSuccss(MyCollecModer myCollecModer) {
                EventBus.getDefault().post(myCollecModer);
            }

            @Override
            public void onFailure(String error) {

            }
        });

    }


    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);
        if (obj instanceof MyCollecModer) {
            pullGridView.onRefreshComplete();
            MyCollecModer myCollecModer = (MyCollecModer) obj;
            if (myCollecModer.getC() == 1) {
                moder = myCollecModer;

                adapter.addData(moder.getO());
                adapter.notifyDataSetChanged();
            } else {

            }
        }
    }
}
