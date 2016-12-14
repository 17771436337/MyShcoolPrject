package com.example.a.myapplication.activity.screen;

import android.view.View;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.util.CommonUtils;

import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/9.
 * 筛选
 */
public class ScreenActivity extends BaseActivity {

    private final int BRAND = 0x0001;
    public final static int CATEGORY = 0x0002;
    private final int FASHION = 0x0003;
    private final int COLOR = 0x0004;
    @Override
    protected int getLayoutID() {
        return R.layout.activity_screen;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.brand_layout, R.id.category_layout, R.id.fashion_layout, R.id.color_layout, R.id.style_layout})
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.brand_layout: //品牌
                CommonUtils.startIntent(this,ScreenBrandActivity.class,BRAND);
                break;

            case R.id.category_layout: //品类
                CommonUtils.startIntent(this,ScreenCategoryActivity.class,FASHION);
                break;
            case R.id.fashion_layout: //流行
                CommonUtils.startIntent(this,ScreenFashionActivity.class,FASHION);
                break;
            case R.id.color_layout: //颜色

                CommonUtils.startIntent(this,ScreenColorActivity.class,COLOR);
                break;
            case R.id.style_layout: //风格偶像
                break;

        }
    }
}
