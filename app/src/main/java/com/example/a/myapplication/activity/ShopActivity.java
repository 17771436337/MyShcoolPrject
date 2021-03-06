package com.example.a.myapplication.activity;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
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
import com.example.a.myapplication.util.Preference;
import com.example.a.myapplication.util.UIUtils;
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

    String id = null;

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
        pullListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        pullListView.getRefreshableView().setAdapter(adapter);
        pullListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                EventBus.getDefault().post("onRefresh");
            }
        });

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
        view.setTitleText("购物车", "");
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
                Log.e("id", "id" + id);
                CommonUtils.startIntent(this, OrederDetailsActivity.class, bundle);

                break;
            case R.id.check_layout:
                id = new String();
                pricer = 0;
                double num = 0;
                for (int i = 0; i < model.getO().size(); i++) {
                    for (int j = 0; j < model.getO().get(i).getShops().size(); j++) {
                        model.getO().get(i).getShops().get(j).setIs(checkBox.isChecked());
                        if (checkBox.isChecked()) {
                            num = Double.parseDouble(model.getO().get(i).getShops().get(j).getPrice());
                            int sum = Integer.parseInt(model.getO().get(i).getShops().get(j).getSum());
                            pricer += (num * sum);
                            id += model.getO().get(i).getShops().get(j).getId() + ",";
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                String text = "总价：￥" + pricer;
                SpannableStringBuilder style = new SpannableStringBuilder(text);
                style.setSpan(new ForegroundColorSpan(UIUtils.getContext().getResources().getColor(R.color.red_text)), 3, text.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //设置指定位置文字的颜色
                style.setSpan(new ForegroundColorSpan(UIUtils.getContext().getResources().getColor(R.color.black_transparency_text)), 0, 3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //设置指定位置文字的颜色
                priceTextView.setText(style);

                break;
        }
    }


    private void getData() {

        Map<String, String> par = new HashMap<>();
        par.put("uid", Preference.get(Config.ID, ""));
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
            pullListView.onRefreshComplete();
            if (shopMode.getC() == 1) {
                model = shopMode;
                adapter.addData(shopMode.getO());
                adapter.notifyDataSetChanged();

            } else {
                Toast.makeText(this, shopMode.getM() + "", Toast.LENGTH_SHORT).show();
            }


        }


        if (obj instanceof String) {
            adapter.getmDatas().clear();
            getData();
        }
    }

    @Override
    public void isChecked(ShopModel.Shop.Content data) {
        pricer = 0;
        double num = 0;
        id = new String();
        for (int i = 0; i < model.getO().size(); i++) {
            for (int j = 0; j < model.getO().get(i).getShops().size(); j++) {
                if (model.getO().get(i).getShops().get(j).is()) {
                    num = Double.parseDouble(model.getO().get(i).getShops().get(j).getPrice());
                    int sum = Integer.parseInt(model.getO().get(i).getShops().get(j).getSum());
                    pricer += (num * sum);
                    id += model.getO().get(i).getShops().get(j).getId() + ",";
                } else {
                    checkBox.setChecked(false);
                }
            }
        }
        String text = "总价：￥" + pricer;
        SpannableStringBuilder style = new SpannableStringBuilder(text);
        style.setSpan(new ForegroundColorSpan(UIUtils.getContext().getResources().getColor(R.color.red_text)), 3, text.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //设置指定位置文字的颜色
        style.setSpan(new ForegroundColorSpan(UIUtils.getContext().getResources().getColor(R.color.black_transparency_text)), 0, 3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //设置指定位置文字的颜色
        priceTextView.setText(style);
    }


    @Override
    public void isChecked(ShopModel.Shop data) {
        pricer = 0;
        double num = 0;
        id = new String();
        for (int i = 0; i < model.getO().size(); i++) {
            for (int j = 0; j < model.getO().get(i).getShops().size(); j++) {
                if (model.getO().get(i).getShops().get(j).is()) {
                    num = Double.parseDouble(model.getO().get(i).getShops().get(j).getPrice());
                    int sum = Integer.parseInt(model.getO().get(i).getShops().get(j).getSum());
                    pricer += (num * sum);
                    id += model.getO().get(i).getShops().get(j).getId() + ",";
                } else {
                    checkBox.setChecked(false);
                }
            }
        }

        String text = "总价：￥" + pricer;
        SpannableStringBuilder style = new SpannableStringBuilder(text);
        style.setSpan(new ForegroundColorSpan(UIUtils.getContext().getResources().getColor(R.color.red_text)), 3, text.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //设置指定位置文字的颜色
        style.setSpan(new ForegroundColorSpan(UIUtils.getContext().getResources().getColor(R.color.black_transparency_text)), 0, 3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //设置指定位置文字的颜色
        priceTextView.setText(style);
    }


}
