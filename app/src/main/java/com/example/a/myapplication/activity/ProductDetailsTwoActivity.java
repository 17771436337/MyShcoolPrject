package com.example.a.myapplication.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.dialog.ShopDialog;
import com.example.a.myapplication.fragment.ImgShopFragemnt;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/14.
 * 单品详情
 */
public class ProductDetailsTwoActivity extends BaseActivity{
    FragmentManager fragmentManager;
    private FragmentTransaction mFragmentTransaction;// 碎片的事物

    @InjectView(R.id.img_f)
    protected FrameLayout img_f;
    ImgShopFragemnt imgFragement;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_productdetailtow;
    }

    @Override
    protected void initView() {
        initImgView();
    }

    @Override
    protected void initData() {

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


    @OnClick({R.id.shop_text})
    protected void onClick(View v){
        switch (v.getId()){
            case R.id.shop_text://加入购物车
                Toast.makeText(this,"点击效果",1000).show();
                ShopDialog dialog = new ShopDialog(this);
                dialog.showAsDropDown(v);
                break;
        }
    }


}
