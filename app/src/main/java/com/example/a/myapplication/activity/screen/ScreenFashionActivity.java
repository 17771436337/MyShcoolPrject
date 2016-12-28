package com.example.a.myapplication.activity.screen;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.ScreenFashionAdapter;
import com.example.a.myapplication.bean.ScreenFashionModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.view.TitleView1;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Map;

import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/12.
 */
public class ScreenFashionActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @InjectView(R.id.pull_layout)
    protected PullToRefreshListView pullListView;

    ScreenFashionModel model = new ScreenFashionModel();

    ScreenFashionAdapter adapter;

    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    private int page = 1;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_fashion;
    }

    @Override
    protected void initView() {
        initTitle();
        getData();
        adapter = new ScreenFashionAdapter(pullListView, model.getO());
        pullListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullListView.getRefreshableView().setAdapter(adapter);
        pullListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                EventBus.getDefault().post("onPullDownToRefresh");
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                EventBus.getDefault().post("onPullUpToRefresh");
            }
        });
        pullListView.getRefreshableView().setOnItemClickListener(this);

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
        view.setTitleText("流行", "完成");

        view.setTitleOnClickListeneRight(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model != null && model.getO() != null) {
                    ScreenFashionModel towModel = new ScreenFashionModel();
                    ArrayList<ScreenFashionModel.Fashion> list = new ArrayList<ScreenFashionModel.Fashion>();
                    for (int i = 0; i < model.getO().size(); i++) {
                        ScreenFashionModel.Fashion data = model.getO().get(i);
                        if (data.is()) {
                            list.add(data);
                        }
                    }

                    towModel.setO(list);
                    Intent intent = new Intent();
                    intent.putExtra("fashion", towModel);
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

        OkHttpUtil.getInstance().addRequestPost(Config.getPopulars, par, new OkHttpUtil.HttpCallBack<ScreenFashionModel>() {

            @Override
            public void onSuccss(ScreenFashionModel screenFashionModel) {
                EventBus.getDefault().post(screenFashionModel);
            }

            @Override
            public void onFailure(String error) {

            }
        });


    }


    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);
        if (obj instanceof ScreenFashionModel) {
            ScreenFashionModel screenFashionModel = (ScreenFashionModel) obj;

            if (screenFashionModel.getC() == 1) {
                if (model.getO() != null) {
                    if (page == 1) {
                        model.getO().clear();
                    }

                    model.getO().addAll(screenFashionModel.getO());
                } else {
                    model = screenFashionModel;
                }
                if (screenFashionModel.getO() != null && screenFashionModel.getO().size() > 0) {
                    if (page == 1) {
                        if (adapter.getmDatas() != null)
                            adapter.getmDatas().clear();
                    }

                    adapter.addData(screenFashionModel.getO());
                    adapter.notifyDataSetChanged();

                }
            } else {
                Toast.makeText(this, screenFashionModel.getM() + "", Toast.LENGTH_SHORT).show();
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
            ScreenFashionModel.Fashion data = model.getO().get(position - 1);
            if (data.is()) {
                data.setIs(false);
            } else {
                data.setIs(true);
            }

            adapter.notifyDataSetChanged();
        }
    }
}
