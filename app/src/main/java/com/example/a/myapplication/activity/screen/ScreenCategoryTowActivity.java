package com.example.a.myapplication.activity.screen;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.ScreenCategoryTowAdapter;
import com.example.a.myapplication.bean.ScreenCategoryTowModel;
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
 * 品类二级筛选
 */
public class ScreenCategoryTowActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @InjectView(R.id.pull_layout)
    protected PullToRefreshListView pullListView;

    ScreenCategoryTowModel model = new ScreenCategoryTowModel();

    ScreenCategoryTowAdapter adapter;

    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    private String id;  //一级分类对应的id
    private String name;//选择标题名字
    private int page = 1;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_categorytow;
    }

    @Override
    protected void initView() {
        id = getIntent().getExtras().getString("id");
        name = getIntent().getExtras().getString("name");
        initTitle();
        getData();
        adapter = new ScreenCategoryTowAdapter(pullListView, model.getO());
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
        view.setTitleText(name, "完成");
    }


    private void getData() {

        Map<String, String> par = CommonUtils.getMapParm();
        par.put("pagination", String.valueOf(page));
        par.put("pagelen", Config.listCount);
        par.put("pid", id);
        OkHttpUtil.getInstance().addRequestPost(Config.getSecondCategory, par, new OkHttpUtil.HttpCallBack<ScreenCategoryTowModel>() {

            @Override
            public void onSuccss(ScreenCategoryTowModel screenCategoryTowModel) {
                EventBus.getDefault().post(screenCategoryTowModel);
            }

            @Override
            public void onFailure(String error) {

            }
        });


    }

    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);
        if (obj instanceof ScreenCategoryTowModel) {
            ScreenCategoryTowModel screenCategoryTowModel = (ScreenCategoryTowModel) obj;
            pullListView.onRefreshComplete();
            if (screenCategoryTowModel.getC() == 1) {

                model = screenCategoryTowModel;
                if (screenCategoryTowModel.getO() != null && screenCategoryTowModel.getO().size() > 0) {
                    if (page == 1) {
                        if (adapter.getmDatas() != null)
                            adapter.getmDatas().clear();
                    }

                    adapter.addData(screenCategoryTowModel.getO());
                    adapter.notifyDataSetChanged();

                }
            } else {
                Toast.makeText(this, screenCategoryTowModel.getM() + "", Toast.LENGTH_SHORT).show();
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
            ScreenCategoryTowModel.Category data = model.getO().get(position - 1);
            if (data.is()) {
                data.setIs(false);
            } else {
                data.setIs(true);
            }

            adapter.notifyDataSetChanged();
        }
    }
}
