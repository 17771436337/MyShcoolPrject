package com.example.a.myapplication.activity.screen;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.ScreenBrandAdapter;
import com.example.a.myapplication.bean.ScreenBrandModel;
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
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * Created by Administrator on 2016/12/9.
 * 品牌
 */
public class ScreenBrandActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @InjectView(R.id.srarch)
    protected EditText srarch;

    @InjectView(R.id.delete)
    protected ImageView delete;

    @InjectView(R.id.pull_layout)
    protected PullToRefreshListView pullListView;

    ScreenBrandModel model = new ScreenBrandModel();

    ScreenBrandAdapter<ScreenBrandModel.Brand> adapter;


    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;
    int page = 1;

    private String brandname;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_screenbrand;
    }

    @Override
    protected void initView() {
        initTitle();
        getData();
        adapter = new ScreenBrandAdapter<ScreenBrandModel.Brand>(pullListView, model.getO());
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
        view.setTitleText("品牌 brand", "完成");
        view.setTitleOnClickListeneRight(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model != null && model.getO() != null) {
                    ScreenBrandModel towModel = new ScreenBrandModel();
                    ArrayList<ScreenBrandModel.Brand> list = new ArrayList<ScreenBrandModel.Brand>();
                    for (int i = 0; i < model.getO().size(); i++) {
                        ScreenBrandModel.Brand data = model.getO().get(i);
                        if (data.is()) {
                            list.add(data);
                        }
                    }

                    towModel.setO(list);
                    Intent intent = new Intent();
                    intent.putExtra("brand", towModel);
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
        if (!TextUtils.isEmpty(brandname)) {
            par.put("brandname", brandname);
        }

        OkHttpUtil.getInstance().addRequestPost(Config.getBrands, par, new OkHttpUtil.HttpCallBack<ScreenBrandModel>() {

            @Override
            public void onSuccss(ScreenBrandModel screenBrandModel) {
                EventBus.getDefault().post(screenBrandModel);
            }

            @Override
            public void onFailure(String error) {

            }
        });

    }


    @OnClick({R.id.delete})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete:  //删除
                srarch.setText("");
                brandname = "";
                EventBus.getDefault().post("srceh");
                break;
        }
    }

    /**
     * 输入框监听
     */
    @OnTextChanged(R.id.srarch)
    public void onTextChanged(CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            delete.setVisibility(View.GONE);
            brandname = "";
        } else {
            brandname = text.toString();
            delete.setVisibility(View.VISIBLE);
        }

        EventBus.getDefault().post("srceh");
    }

    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);
        if (obj instanceof ScreenBrandModel) {
            ScreenBrandModel screenBrandModel = (ScreenBrandModel) obj;
            pullListView.onRefreshComplete();
            if (screenBrandModel.getC() == 1) {
                if (model.getO() != null) {
                    if (page == 1) {
                        model.getO().clear();
                    }

                    model.getO().addAll(screenBrandModel.getO());
                } else {
                    model = screenBrandModel;
                }
                if (screenBrandModel.getO() != null && screenBrandModel.getO().size() > 0) {
                    if (page == 1) {
                        if (adapter.getmDatas() != null)
                            adapter.getmDatas().clear();
                    }

                    adapter.addData(screenBrandModel.getO());
                    adapter.notifyDataSetChanged();
                }
            } else {
                Toast.makeText(this, screenBrandModel.getM() + "", Toast.LENGTH_SHORT).show();
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
            } else if (str.equals("srceh")) {
                page = 1;
                getData();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (model != null) {
            ScreenBrandModel.Brand data = model.getO().get(position - 1);
            if (data.is()) {
                data.setIs(false);
            } else {
                data.setIs(true);
            }

            adapter.notifyDataSetChanged();
        }
    }
}
