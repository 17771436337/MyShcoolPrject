package com.example.a.myapplication.activity;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.ShopAdapter;
import com.example.a.myapplication.bean.ShopModel;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/8.
 * 购物车
 */
public class ShopActivity extends BaseActivity {


    @InjectView(R.id.pull_layout)
    protected PullToRefreshListView pullListView;

    ShopModel model = new ShopModel();

    ShopAdapter adapter;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_shop;
    }

    @Override
    protected void initView() {
        getData();
        adapter = new ShopAdapter(pullListView, model.getList());
        pullListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullListView.getRefreshableView().setAdapter(adapter);

    }

    @Override
    protected void initData() {

    }


    private void getData() {
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
}
