package com.example.a.myapplication.activity;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.ShopAdapter;
import com.example.a.myapplication.bean.ShopModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.view.TitleView1;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/8.
 * 购物车
 */
public class ShopActivity extends BaseActivity {


    @InjectView(R.id.pull_layout)
    protected PullToRefreshListView pullListView;
    ShopModel model = new ShopModel();
    ShopAdapter adapter;

    @InjectView(R.id.account)
    protected TextView account;


    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_shop;
    }

    @Override
    protected void initView() {
        initTitle();
        getData();
        adapter = new ShopAdapter(pullListView, model.getList());
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


    @OnClick({R.id.account})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.account://结算
                CommonUtils.startIntent(this, ConfirmAnOrderActivity.class);
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
        List<ShopModel.Shop> list = new ArrayList<ShopModel.Shop>();
        for (int i = 0; i < 10; i++) {
            ShopModel.Shop shop = new ShopModel.Shop();
            List<ShopModel.Shop.Content> list2 = new ArrayList<ShopModel.Shop.Content>();
            for (int j = 0; j < 2; j++) {
                ShopModel.Shop.Content content = new ShopModel.Shop.Content();
                list2.add(content);
            }

            shop.setList(list2);
            list.add(shop);
        }

        model.setList(list);
    }

    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);
        if (obj instanceof ShopModel) {
            ShopModel shopMode = (ShopModel) obj;
            if (shopMode.getC() == 1) {

            } else {

            }

        }
    }
}
