package com.example.a.myapplication.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.fragment.CheckProductFragment;
import com.example.a.myapplication.fragment.ImgShopFragemnt;
import com.example.a.myapplication.fragment.ProductTitileMessageFragment;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/12.
 * 求单品详情
 */
public class ProductDetailsActivity extends BaseActivity {

    FragmentManager fragmentManager;
    private FragmentTransaction mFragmentTransaction;// 碎片的事物

    @InjectView(R.id.title)
    protected TextView titleText;

    @InjectView(R.id.title_right)
    protected ImageView titleRight;

    @InjectView(R.id.img_f)
    protected FrameLayout img_f;
    ImgShopFragemnt imgFragement;

    @InjectView(R.id.content)
    protected FrameLayout title;

    ProductTitileMessageFragment productTitileMessageFragment;
    CheckProductFragment checkProductFragment;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_productdetail;
    }

    @Override
    protected void initView() {
        Log.e("id",getIntent().getExtras().getString("id"));
        initTitle();
        initImgView();
        initTitleOne();
        initOnClick();
    }


    @Override
    protected void initData() {

    }

    /**
     * 标题初始化
     */
    private void initTitle() {
        titleText.setText("求单品详情");
    }

    /**
     * 商品图片碎片初始化
     */
    private void initImgView() {
        imgFragement = new ImgShopFragemnt();
        fragmentManager = getSupportFragmentManager();
        mFragmentTransaction = fragmentManager.beginTransaction();
        mFragmentTransaction.add(R.id.img_f, imgFragement);
        mFragmentTransaction.commit();
    }


    /**
     * 底下标题第一页
     */
    private void initTitleOne() {

        productTitileMessageFragment = new ProductTitileMessageFragment();
        fragmentManager = getSupportFragmentManager();
        mFragmentTransaction = fragmentManager.beginTransaction();
        mFragmentTransaction.add(R.id.content, productTitileMessageFragment);
        mFragmentTransaction.commit();
    }

    /**
     * 查看解答
     */
    private void initTitleCheck() {
        checkProductFragment = new CheckProductFragment();
        fragmentManager = getSupportFragmentManager();
        mFragmentTransaction = fragmentManager.beginTransaction();
        mFragmentTransaction.add(R.id.content, checkProductFragment);
        mFragmentTransaction.commit();
    }


    /**
     * 监听事件
     */
    private void initOnClick() {

        /**查看解答 点击*/
        if (productTitileMessageFragment != null) {
            productTitileMessageFragment.cacheOnClick(new ProductTitileMessageFragment.SetOnClick() {
                @Override
                public void cacheOnClick() {
                    checkProductFragment = new CheckProductFragment();
                    fragmentManager = getSupportFragmentManager();
                    mFragmentTransaction = fragmentManager.beginTransaction();
                    mFragmentTransaction.remove(productTitileMessageFragment);
                    mFragmentTransaction.add(R.id.content, checkProductFragment);
                    mFragmentTransaction.commit();
                }
            });
        }
    }


    @OnClick({R.id.back, R.id.title_right})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.back: //返回
                finish();
                break;
            case R.id.title_right:

                break;
        }
    }
}
