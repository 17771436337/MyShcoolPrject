package com.example.a.myapplication.activity;

import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.view.AddShopGridHandView;

import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/15.
 */
public class AddShopActivity extends BaseActivity {


    @InjectView(R.id.l1)
    protected LinearLayout l1;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_addshop;
    }

    @Override
    protected void initView() {
        AddShopGridHandView view = new AddShopGridHandView(this);
        l1.addView(view.getView());
        view.getIndex(new AddShopGridHandView.Index() {
            @Override
            public void getIndex(int i) {
                Toast.makeText(AddShopActivity.this, "I:" + i, 1000).show();
            }
        });
    }

    @Override
    protected void initData() {

    }
}
