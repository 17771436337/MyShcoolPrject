package com.example.a.myapplication.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.ShopAdapter;
import com.example.a.myapplication.bean.ShopModel;
import com.example.a.myapplication.holder.ContentHolder;
import com.example.a.myapplication.holder.ShopHolder;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.view.TitleView1;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/8.
 * 购物车
 */
public class ShopActivity extends BaseActivity implements ContentHolder.IsChecked, ShopHolder.IsCheckedGroup {


    @InjectView(R.id.pull_layout)
    protected PullToRefreshListView pullListView;
    ShopModel model = new ShopModel();
    ShopAdapter adapter;

    @InjectView(R.id.account)
    protected TextView account;
    public static ShopActivity shopActivity;
    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    @InjectView(R.id.price_text)
    protected TextView priceTextView;
    private double pricer;

    @InjectView(R.id.check_layout)
    protected CheckBox checkBox;

    String id;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_shop;
    }

    @Override
    protected void initView() {
        shopActivity = this;
        initTitle();
        getData();
        adapter = new ShopAdapter(pullListView, model.getO());
        pullListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullListView.getRefreshableView().setAdapter(adapter);

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
        view.setTitleText("购物车", "编辑");
    }


    @OnClick({R.id.account, R.id.check_layout})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.account://结算
                if (TextUtils.isEmpty(id)) {
                    Toast.makeText(this, "请选择商品", Toast.LENGTH_SHORT).show();
                    return;
                }

                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                CommonUtils.startIntent(this, OrederDetailsActivity.class, bundle);

                break;
            case R.id.check_layout:
                id = null;
                for (int i = 0; i < model.getO().size(); i++) {
                    for (int j = 0; j < model.getO().get(i).getShops().size(); j++) {
                        model.getO().get(i).getShops().get(j).setIs(checkBox.isChecked());
                        if (checkBox.isChecked()) {
                            id += model.getO().get(i).getShops().get(j).getId() + ",";
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                break;
        }
    }


    private void getData() {

        Map<String, String> par = new HashMap<>();
        par.put("uid", "2");
        OkHttpUtil.getInstance().addRequestPost(Config.cartList, par, new OkHttpUtil.HttpCallBack<ShopModel>() {

            @Override
            public void onSuccss(ShopModel shopModel) {
                EventBus.getDefault().post(shopModel);
            }

            @Override
            public void onFailure(String error) {

            }
        });

    }

    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);
        if (obj instanceof ShopModel) {
            ShopModel shopMode = (ShopModel) obj;
            if (shopMode.getC() == 1) {
                model = shopMode;
                adapter.addData(shopMode.getO());
                adapter.notifyDataSetChanged();

            } else {

            }

        }
    }

    @Override
    public void isChecked(ShopModel.Shop.Content data) {
        pricer = 0;
        double num = 0;
        id = null;
        for (int i = 0; i < model.getO().size(); i++) {
            for (int j = 0; j < model.getO().get(i).getShops().size(); j++) {
                if (model.getO().get(i).getShops().get(j).is()) {
                    num = Double.parseDouble(model.getO().get(i).getShops().get(j).getPrice());
                    pricer += num;
                    id += model.getO().get(i).getShops().get(j).getId() + ",";
                } else {
                    checkBox.setChecked(false);
                }
            }
        }
        priceTextView.setText(pricer + "");
    }


    @Override
    public void isChecked(ShopModel.Shop data) {
        pricer = 0;
        double num = 0;
        id = null;
        for (int i = 0; i < model.getO().size(); i++) {
            for (int j = 0; j < model.getO().get(i).getShops().size(); j++) {
                if (model.getO().get(i).getShops().get(j).is()) {
                    num = Double.parseDouble(model.getO().get(i).getShops().get(j).getPrice());
                    pricer += num;
                    id += model.getO().get(i).getShops().get(j).getId() + ",";
                } else {
                    checkBox.setChecked(false);
                }
            }
        }

        priceTextView.setText(pricer + "");
    }


}
