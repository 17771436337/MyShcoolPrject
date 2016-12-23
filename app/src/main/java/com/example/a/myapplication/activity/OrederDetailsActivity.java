package com.example.a.myapplication.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.ShopAdapter;
import com.example.a.myapplication.bean.ShopModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.Preference;
import com.example.a.myapplication.view.CustomListView;
import com.example.a.myapplication.view.TitleView1;

import java.util.Map;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/15.
 */
public class OrederDetailsActivity extends BaseActivity {

    @InjectView(R.id.listView)
    protected CustomListView listView;
    ShopModel model = new ShopModel();
    ShopAdapter adapter;

    private boolean isShow = true;

    @InjectView(R.id.favorable_r1)
    protected LinearLayout favorable;

    @InjectView(R.id.arrow)
    protected ImageView arrow;

    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    String id;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_orderdetails;
    }

    @Override
    protected void initView() {
        initTitle();
        setShow(isShow);
        getData();
        adapter = new ShopAdapter(model.getO());
        listView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        id = getIntent().getExtras().getString("id");


    }

    /**
     * 标题初始化
     */
    private void initTitle() {
        TitleView1 view = new TitleView1(this);
        titleView.addView(view.getView());
        view.setTitleText("订单详情", "编辑");
    }

    private void getData() {

        Map<String, String> par = CommonUtils.getMapParm();
        par.put("uid", Preference.get(Config.ID, ""));
        par.put("cart_ids", id);

        OkHttpUtil.getInstance().addRequestPost(Config.submit, par, new OkHttpUtil.HttpCallBack<ShopModel>() {

            @Override
            public void onSuccss(ShopModel shopModel) {

            }

            @Override
            public void onFailure(String error) {

            }
        });


//        List<ShopModel.Shop> list = new ArrayList<ShopModel.Shop>();
//        for (int i = 0; i < 1; i++) {
//            ShopModel.Shop shop = new ShopModel.Shop();
//            List<ShopModel.Shop.Content> list2 = new ArrayList<ShopModel.Shop.Content>();
//            for (int j = 0; j < 2; j++) {
//                ShopModel.Shop.Content content = new ShopModel.Shop.Content();
//                list2.add(content);
//            }
//
//            shop.setShops(list2);
//            list.add(shop);
//        }
//
//        model.setO(list);
    }


    @OnClick({R.id.favorable_layout})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.favorable_layout://

                setShow(isShow);

                break;
        }
    }


    /**
     * 显示优惠
     */
    private void setShow(boolean isShow) {
        if (isShow) {
            arrow.setImageResource(R.drawable.icon_up_arrow);
            favorable.setVisibility(View.VISIBLE);
            this.isShow = !isShow;
        } else {
            arrow.setImageResource(R.drawable.icon_down_arrow);
            favorable.setVisibility(View.GONE);
            this.isShow = !isShow;
        }
    }
}
