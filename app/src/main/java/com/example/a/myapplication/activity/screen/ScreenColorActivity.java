package com.example.a.myapplication.activity.screen;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.ScreenColorAdapter;
import com.example.a.myapplication.bean.ScreenColorModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.view.TitleView1;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Map;

import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/12.
 * 筛选颜色
 */
public class ScreenColorActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    @InjectView(R.id.pull_layout)
    protected PullToRefreshGridView pullGridView;
    ScreenColorModel model = new ScreenColorModel();
    ScreenColorAdapter adapter;

    private int page = 1;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_screencolor;
    }

    @Override
    protected void initView() {
        initTitle();
        pullGridView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new ScreenColorAdapter(pullGridView, model.getO());
        pullGridView.getRefreshableView().setAdapter(adapter);
        pullGridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                EventBus.getDefault().post("onPullDownToRefresh");
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                EventBus.getDefault().post("onPullUpToRefresh");
            }
        });
        pullGridView.getRefreshableView().setOnItemClickListener(this);
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
        view.setTitleText("颜色 Color", "完成");

        view.setTitleOnClickListeneRight(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model != null && model.getO() != null) {
                    ScreenColorModel towModel = new ScreenColorModel();
                    ArrayList<ScreenColorModel.OBean> list = new ArrayList<ScreenColorModel.OBean>();
                    for (int i = 0; i < model.getO().size(); i++) {
                        ScreenColorModel.OBean data = model.getO().get(i);
                        if (data.is()) {
                            list.add(data);
                        }
                    }

                    towModel.setO(list);
                    Intent intent = new Intent();
                    intent.putExtra("color", towModel);
                    setResult(0x0001, intent);
                    finish();
                } else {

                }
            }
        });
    }

    private void getData() {

        Map<String, String> par = CommonUtils.getMapParm();
        par.put("pagination", String.valueOf(page));
        par.put("pagelen", Config.listCount);

        OkHttpUtil.getInstance().addRequestPost(Config.getColors, par, new OkHttpUtil.HttpCallBack<ScreenColorModel>() {

            @Override
            public void onSuccss(ScreenColorModel screenColorModel) {
                EventBus.getDefault().post(screenColorModel);
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }


    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);
        if (obj instanceof ScreenColorModel) {
            ScreenColorModel screenColorModel = (ScreenColorModel) obj;
            pullGridView.onRefreshComplete();
            if (screenColorModel.getC() == 1) {
                if (model.getO() != null) {
                    if (page == 1) {
                        model.getO().clear();
                    }

                    model.getO().addAll(screenColorModel.getO());
                } else {
                    model = screenColorModel;
                }
                if (screenColorModel.getO() != null && screenColorModel.getO().size() > 0) {
                    if (page == 1) {
                        if (adapter.getmDatas() != null)
                            adapter.getmDatas().clear();
                    }

                    adapter.addData(screenColorModel.getO());
                    adapter.notifyDataSetChanged();
                }
            } else {
                Toast.makeText(this, screenColorModel.getM() + "", Toast.LENGTH_SHORT).show();
            }
        }

        if (obj instanceof String) {
            String str = (String) obj;
            if (str.equals("onPullDownToRefresh")) {
                page = 1;
                getData();

            } else if (str.equals("onPullUpToRefresh")) {
                page++;
                getData();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (model != null) {
            ScreenColorModel.OBean data = model.getO().get(position);
            if (data.is()) {
                data.setIs(false);
            } else {
                data.setIs(true);
            }

            adapter.notifyDataSetChanged();
        }
    }
}
