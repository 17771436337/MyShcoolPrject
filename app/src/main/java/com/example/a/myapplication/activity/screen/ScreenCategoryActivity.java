package com.example.a.myapplication.activity.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.ScreenCategoryOneAdapter;
import com.example.a.myapplication.bean.ScreenCategoryOneModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.view.TitleView1;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/12.
 * 品类的一级筛选
 */
public class ScreenCategoryActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @InjectView(R.id.list_view)
    protected PullToRefreshListView listView;

    ScreenCategoryOneModel model = new ScreenCategoryOneModel();

    ScreenCategoryOneAdapter adapter;

    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    int page = 1;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_category;
    }

    @Override
    protected void initView() {
        initTitle();

        listView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new ScreenCategoryOneAdapter(model.getO());
        listView.getRefreshableView().setAdapter(adapter);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                EventBus.getDefault().post("onPullDownToRefresh");
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                EventBus.getDefault().post("onPullUpToRefresh");
            }
        });


        listView.getRefreshableView().setOnItemClickListener(this);
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
        view.setTitleText("选择种类", "");
    }

    private void getData() {

        Map<String, String> par = CommonUtils.getMapParm();
        par.put("pagination", String.valueOf(page));
        par.put("pagelen", Config.listCount);
        OkHttpUtil.getInstance().addRequestPost(Config.getFirstCategory, par, new OkHttpUtil.HttpCallBack<ScreenCategoryOneModel>() {

            @Override
            public void onSuccss(ScreenCategoryOneModel screenCategoryOneModel) {
                EventBus.getDefault().post(screenCategoryOneModel);
            }

            @Override
            public void onFailure(String error) {

            }
        });


    }


    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);
        if (obj instanceof ScreenCategoryOneModel) {
            ScreenCategoryOneModel screenCategoryOneModel = (ScreenCategoryOneModel) obj;
            if (screenCategoryOneModel.getC() == 1) {
                listView.onRefreshComplete();
                model = screenCategoryOneModel;
                if (screenCategoryOneModel.getO() != null && screenCategoryOneModel.getO().size() > 0) {
                    if (page == 1) {
                        if (adapter.getmDatas() != null)
                            adapter.getmDatas().clear();
                    }

                    adapter.addData(screenCategoryOneModel.getO());
                    adapter.notifyDataSetChanged();

                }
            } else {
                Toast.makeText(this, screenCategoryOneModel.getM() + "", Toast.LENGTH_SHORT).show();
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

        Bundle bundle = new Bundle();
        bundle.putString("id", model.getO().get(position - 1).getId());
        bundle.putString("name", model.getO().get(position - 1).getName());
        CommonUtils.startIntent(this, ScreenCategoryTowActivity.class, bundle, ScreenActivity.CATEGORY);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 0x0001) {
            if (requestCode == ScreenActivity.CATEGORY) {
                Intent intent = new Intent();
                intent.putExtras(data);
                setResult(0x0001, intent);
                finish();
            }
        }
    }
}
