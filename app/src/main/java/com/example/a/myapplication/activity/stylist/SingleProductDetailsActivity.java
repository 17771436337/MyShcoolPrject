package com.example.a.myapplication.activity.stylist;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.activity.RecommendListActivity;
import com.example.a.myapplication.activity.ShopActivity;
import com.example.a.myapplication.adapter.DialogShopAdapter;
import com.example.a.myapplication.bean.BaseModel;
import com.example.a.myapplication.bean.CartShopInfoModel;
import com.example.a.myapplication.bean.ShopDetailsModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.PopupUtil;
import com.example.a.myapplication.util.Preference;
import com.example.a.myapplication.util.UIUtils;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshHorizontalScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
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
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    public static  Map<String,String> parm;

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
                title.setText(mShopDetailsModel.getO().getName());
                activitySingleProductDetailsName.setText(
                        mShopDetailsModel.getO().getBrand()+"   "+
                                mShopDetailsModel.getO().getCategory()+"   "+
                                mShopDetailsModel.getO().getColor()+"   "+
                                mShopDetailsModel.getO().getPopular()  );
                activitySingleProductDetailsIdol.setText(mShopDetailsModel.getO().getIdol());
                activitySingleProductDetailsPrice.setText("￥" + mShopDetailsModel.getO().getPrice());
                imgs = mShopDetailsModel.getO().getImgs();
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
                        ImageLoader.getInstance().displayImage(Config.hostImgString + imgs.get(position).getImg(), iv);
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
        if (obj instanceof CartShopInfoModel) {
            mCartShopInfoModel = (CartShopInfoModel) obj;
            if (1 == mShopDetailsModel.getC()) {
                PopupUtil.showInBottom(this, R.layout.dialog_shop, new PopupUtil.ShowListener() {
                    @Override
                    public void onShow(View view, final PopupWindow popupWindow) {
                        dialogShopPic = (ImageView) view.findViewById(R.id.dialog_shop_pic);
                        dialogShopTitle = (TextView) view.findViewById(R.id.dialog_shop_title);
                        dialogShopPirac = (TextView) view.findViewById(R.id.dialog_shop_pirac);
                        popReduce = (TextView) view.findViewById(R.id.pop_reduce);//减数量
                        dialogShopNum = (TextView) view.findViewById(R.id.dialog_shop_num);//数量
                        final String num=dialogShopNum.getText().toString().trim();
                        intNum=Integer.parseInt(num);
                        popAdd = (TextView) view.findViewById(R.id.pop_add);//加数量
                        dialogShopDelete = (ImageView) view.findViewById(R.id.dialog_shop_delete);//关闭
                        dialogShopBut = (TextView) view.findViewById(R.id.dialog_shop_but);//确定
                        popLayout = (LinearLayout) view.findViewById(R.id.pop_layout);


                        dialog_shop_color = (TextView) view.findViewById(R.id.dialog_shop_color);
                        dialog_shop_sizes = (PullToRefreshGridView) view.findViewById(R.id.dialog_shop_sizes);

                        mDialogShopAdapter=   new DialogShopAdapter(dialog_shop_sizes,mCartShopInfoModel.getO().getSize());
                        dialog_shop_sizes.getRefreshableView().setAdapter(mDialogShopAdapter);
                        sizemls=mCartShopInfoModel.getO().getSize().get(0);
                        dialog_shop_sizes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                                for(int ii=0;ii<adapterView.getTouchables().size();ii++){
                                    TextView textView= (TextView) adapterView.getTouchables().get(ii).findViewById(R.id.dialog_shop_s);
                                    if(i==ii){
                                        textView.setBackgroundColor(UIUtils.getColor(R.color.font_color));
                                        textView.setTextColor(UIUtils.getColor(R.color.white));
                                        sizemls=mCartShopInfoModel.getO().getSize().get(ii);
                                    }else{
                                        textView.setBackgroundColor(UIUtils.getColor(R.color.white));
                                        textView.setTextColor(UIUtils.getColor(R.color.font_color));
                                    }
                                }
                            }
                        });
                        ImageLoader.getInstance().displayImage(Config.hostImgString+mCartShopInfoModel.getO().getImg(), dialogShopPic);
                        dialogShopTitle.setText(mCartShopInfoModel.getO().getName());
                        dialogShopPirac.setText(mCartShopInfoModel.getO().getPrice());
                        dialog_shop_color.setText(mCartShopInfoModel.getO().getColor());
                        popReduce.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                intNum= intNum-1;
                                dialogShopNum.setText(intNum>=1?String.valueOf(intNum):"1");
                            }
                        });
                        popAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                intNum= intNum+1;
                                dialogShopNum.setText((intNum)>=1?String.valueOf(intNum):"1");
                            }
                        });
                        dialogShopDelete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popupWindow.dismiss();
                            }
                        });

                        dialogShopBut.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if("".equals(dialogShopNum.getText().toString().trim())){
                                    Toast.makeText(SingleProductDetailsActivity.this,"数量不正确",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                if("".equals(dialog_shop_color.getText().toString().trim())){
                                    Toast.makeText(SingleProductDetailsActivity.this,"请选择颜色",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                if(null==sizemls||"".equals(sizemls)){
                                    Toast.makeText(SingleProductDetailsActivity.this,"请选择尺寸",Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                Map<String,String> parm=CommonUtils.getMapParm();
                                parm.put("uid", Preference.get(Config.ID,""));
                                parm.put("sid",getIntent().getExtras().getString("sid"));
                                parm.put("sum",dialogShopNum.getText().toString().trim());
                                parm.put("color",dialog_shop_color.getText().toString().trim());
                                parm.put("size",sizemls);

                                addCart(parm);
                            }
                        });

                    }

                });
            } else {
                Toast.makeText(this, mCartShopInfoModel.getM(), Toast.LENGTH_SHORT).show();

            }
            activitySingleProductDetailsGwc.setEnabled(true);
        }
        if(obj instanceof String){
            Toast.makeText(this, obj.toString(), Toast.LENGTH_SHORT).show();
            CommonUtils.startIntent(this, ShopActivity.class);
        }
    }
    public void addCart(Map<String,String> parm){
        OkHttpUtil.getInstance().addRequestPost(Config.addCart, parm, new OkHttpUtil.HttpCallBack<BaseModel>() {
            @Override
            public void onSuccss(BaseModel baseModel) {
                if(1==baseModel.getC()){
                    EventBus.getDefault().post(baseModel.getM());
                }else{
                    Toast.makeText(SingleProductDetailsActivity.this,baseModel.getM(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void hiddenAll(){
        dialog_shop_s.setBackground(getResources().getDrawable(R.drawable.black_border_shape));
        dialog_shop_l.setBackground(getResources().getDrawable(R.drawable.black_border_shape));
        dialog_shop_m.setBackground(getResources().getDrawable(R.drawable.black_border_shape));
        dialog_shop_s.setTextColor(getResources().getColor(R.color.font_color));
        dialog_shop_l.setTextColor(getResources().getColor(R.color.font_color));
        dialog_shop_m.setTextColor(getResources().getColor(R.color.font_color));

    }
    public DialogShopAdapter mDialogShopAdapter;
    public static String sizemls;
    public int intNum;
    public ImageView dialogShopPic;
    public TextView dialogShopTitle;
    public TextView dialogShopPirac;
    public TextView popReduce;
    public TextView dialogShopNum;
    public TextView popAdd;
    public ImageView dialogShopDelete;
    public TextView dialogShopBut;
    public TextView dialog_shop_color,dialog_shop_s,dialog_shop_m,dialog_shop_l;
    public LinearLayout popLayout;
    public PullToRefreshGridView dialog_shop_sizes;

    public void init() {
        activity_single_product_details_tv1.setText(getString(R.string.icon_gwc));
        initIconFont(activity_single_product_details_tv1);
        activity_single_product_details_tv2.setText(getString(R.string.icon_sc3));
        initIconFont(activity_single_product_details_tv2);
        activity_single_product_details_tv3.setText(getString(R.string.icon_fx2));
        initIconFont(activity_single_product_details_tv3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SingleProductDetailsActivity.this.finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
    public CartShopInfoModel mCartShopInfoModel;
    @OnClick({R.id.activity_single_product_details_gwc,R.id.activity_single_product_details_tv3,R.id.activity_single_product_details_tv2,R.id.activity_single_product_details_cd})
    public void onClick(View view) {

        switch (view.getId()){
            case  R.id.activity_single_product_details_gwc:
                activitySingleProductDetailsGwc.setEnabled(false);
                getdate();
                break;
            case  R.id.activity_single_product_details_tv3:
                Toast.makeText(SingleProductDetailsActivity.this,"功能正在开发中。。。",Toast.LENGTH_SHORT).show();
                break;
            case  R.id.activity_single_product_details_tv2:
                activity_single_product_details_tv2.setEnabled(false);
                toSc();
                break;

            case  R.id.activity_single_product_details_cd:
                parm=CommonUtils.getMapParm();
                if(null==mShopDetailsModel&&null!=mShopDetailsModel.getO()){
                    return;
                }
                ShopDetailsModel.OBean ob= mShopDetailsModel.getO();
                if(CommonUtils.isNullAnd(ob.getBrands())){
                    parm.put("brands",ob.getBrands());
                }
                if(CommonUtils.isNullAnd(ob.getCategorys())){
                    parm.put("categorys",ob.getCategorys());
                }
                if(CommonUtils.isNullAnd(ob.getColors())){
                    parm.put("colors",ob.getColors());
                }
                if(CommonUtils.isNullAnd(ob.getPopulars())){
                    parm.put("populars",ob.getPopulars());
                }
                if(CommonUtils.isNullAnd(ob.getIdols())){
                    parm.put("idols",ob.getIdols());
                }

                Intent mIntent=   new Intent();
                mIntent.setClass(SingleProductDetailsActivity.this, RecommendListActivity.class);
                startActivity(mIntent);
                break;
        } }

    public void toSc(){
        Map<String,String> parm=CommonUtils.getMapParm();
        parm.put("uid",Preference.get(Config.ID,""));
        parm.put("shopid",getIntent().getExtras().getString("sid"));
        OkHttpUtil.getInstance().addRequestPost(Config.styleCollect, parm, new OkHttpUtil.HttpCallBack<BaseModel>() {
            @Override
            public void onSuccss(final BaseModel baseModel) {
                UIUtils.post(new Runnable() {
                    @Override
                    public void run() {
                        if(baseModel.getC()==1){
                            activity_single_product_details_tv2.setEnabled(true);
                            activity_single_product_details_tv2.setTextColor(Color.RED);
                        }
                        Toast.makeText(SingleProductDetailsActivity.this,baseModel.getM(),Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    public void getdate() {
        Map<String, String> parm = CommonUtils.getMapParm();
        parm.put("sid",getIntent().getExtras().getString("sid"));
        OkHttpUtil.getInstance().addRequestPost(Config.cartShopInfo, parm, new OkHttpUtil.HttpCallBack<CartShopInfoModel>() {
            @Override
            public void onSuccss(CartShopInfoModel cartShopInfoModel) {
                EventBus.getDefault().post(cartShopInfoModel);
            }
            @Override
            public void onFailure(String error) {

            }
        });
    }

}
