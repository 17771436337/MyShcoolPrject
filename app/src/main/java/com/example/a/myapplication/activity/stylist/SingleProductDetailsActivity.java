package com.example.a.myapplication.activity.stylist;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.ShopDetailsModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.PopupUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SingleProductDetailsActivity extends BaseActivity {


    @InjectView(R.id.activity_single_product_details_tv1)
    protected TextView activity_single_product_details_tv1;
    @InjectView(R.id.activity_single_product_details_tv2)
    protected TextView activity_single_product_details_tv2;
    @InjectView(R.id.activity_single_product_details_tv3)
    protected TextView activity_single_product_details_tv3;

    ShopDetailsModel mShopDetailsModel;
    @InjectView(R.id.activity_single_product_details_name)
    TextView activitySingleProductDetailsName;
    @InjectView(R.id.activity_single_product_details_brand)
    TextView activitySingleProductDetailsBrand;
    @InjectView(R.id.activity_single_product_details_category)
    TextView activitySingleProductDetailsCategory;
    @InjectView(R.id.activity_single_product_details_color)
    TextView activitySingleProductDetailsColor;
    @InjectView(R.id.activity_single_product_details_popilar)
    TextView activitySingleProductDetailsPopilar;
    @InjectView(R.id.activity_single_product_details_idol)
    TextView activitySingleProductDetailsIdol;
    @InjectView(R.id.activity_single_product_details_gwc)
    LinearLayout activitySingleProductDetailsGwc;
    @InjectView(R.id.activity_single_product_details_price)
    TextView activitySingleProductDetailsPrice;
    @InjectView(R.id.back)
    ImageView back;
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.title_right)
    ImageView titleRight;
    @InjectView(R.id.activity_single_product_details_vp)
    ViewPager activitySingleProductDetailsVp;
    List<ShopDetailsModel.OBean.ImgsBean> imgs;
    @Override
    protected int getLayoutID() {
        return R.layout.activity_single_product_details;
    }

    @Override
    protected void initView() {
        init();
    }
    private PagerAdapter adapter;
    @Override
    protected void initData() {
        Map<String, String> parm = CommonUtils.getMapParm();
        parm.put("sid", getIntent().getExtras().getString("sid"));
        OkHttpUtil.getInstance().addRequestPost(Config.shopDetails, parm, new OkHttpUtil.HttpCallBack<ShopDetailsModel>() {
            @Override
            public void onSuccss(ShopDetailsModel shopDetailsModel) {
                EventBus.getDefault().post(shopDetailsModel);
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);
        if (obj instanceof ShopDetailsModel) {
            mShopDetailsModel = (ShopDetailsModel) obj;
            if (1 == mShopDetailsModel.getC()) {
                activitySingleProductDetailsName.setText(mShopDetailsModel.getO().getName());
                activitySingleProductDetailsBrand.setText(mShopDetailsModel.getO().getBrand());
                activitySingleProductDetailsCategory.setText(mShopDetailsModel.getO().getCategory());
                activitySingleProductDetailsColor.setText(mShopDetailsModel.getO().getColor());
                activitySingleProductDetailsPopilar.setText(mShopDetailsModel.getO().getPopular());
                activitySingleProductDetailsIdol.setText(mShopDetailsModel.getO().getIdol());
                activitySingleProductDetailsIdol.setText(mShopDetailsModel.getO().getIdol());
                activitySingleProductDetailsPrice.setText("￥" + mShopDetailsModel.getO().getPrice());
                imgs=mShopDetailsModel.getO().getImgs();
                adapter = new PagerAdapter() {
                    @Override
                    public int getCount() {
                        return imgs == null ? 0 : imgs.size();
                    }

                    @Override
                    public boolean isViewFromObject(View view, Object object) {
                        return view == object;
                    }

                    @Override
                    public Object instantiateItem(ViewGroup container, int position) {
                        ImageView iv = new ImageView(container.getContext());
                        iv.setScaleType(ImageView.ScaleType.FIT_XY);
                        ImageLoader.getInstance().displayImage(Config.hostImgString+imgs.get(position).getImg(),iv);
                        // 千万记得调用
                        container.addView(iv);
                        return iv;
                    }

                    @Override
                    public void destroyItem(ViewGroup container, int position, Object object) {
                        container.removeView((View) object);
                    }
                };
                activitySingleProductDetailsVp.setAdapter(adapter);
            }
        }
    }

    public void init() {
        activity_single_product_details_tv1.setText(getString(R.string.icon_gwc));
        initIconFont(activity_single_product_details_tv1);
        activity_single_product_details_tv2.setText(getString(R.string.icon_sc));
        initIconFont(activity_single_product_details_tv2);
        activity_single_product_details_tv3.setText(getString(R.string.icon_fx));
        initIconFont(activity_single_product_details_tv3);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @OnClick(R.id.activity_single_product_details_gwc)
    public void onClick() {
        PopupUtil.showInBottom(this, R.layout.dialog_shop, new PopupUtil.ShowListener() {
            @Override
            public void onShow(View view, PopupWindow popupWindow) {

            }
        });
    }


}
