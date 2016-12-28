package com.example.a.myapplication.activity.stylist;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.BaseModel;
import com.example.a.myapplication.bean.CartShopInfoModel;
import com.example.a.myapplication.bean.ShopDetailsModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.PopupUtil;
import com.example.a.myapplication.util.Preference;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
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
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


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
                        dialog_shop_s = (TextView) view.findViewById(R.id.dialog_shop_s);
                        dialog_shop_m = (TextView) view.findViewById(R.id.dialog_shop_m);
                        dialog_shop_l = (TextView) view.findViewById(R.id.dialog_shop_l);


                        ImageLoader.getInstance().displayImage(Config.hostImgString+mCartShopInfoModel.getO().getImg(), dialogShopPic);
                        dialogShopTitle.setText(mCartShopInfoModel.getO().getName());
                        dialogShopPirac.setText(mCartShopInfoModel.getO().getPrice());
                        dialog_shop_color.setText(mCartShopInfoModel.getO().getColor());
                        dialog_shop_color.setVisibility("".equals(mCartShopInfoModel.getO().getColor())?View.INVISIBLE:View.VISIBLE);
                        dialog_shop_s.setText(mCartShopInfoModel.getO().getS());
                        dialog_shop_s.setVisibility("".equals(mCartShopInfoModel.getO().getS())?View.INVISIBLE:View.VISIBLE);
                        dialog_shop_l.setText(mCartShopInfoModel.getO().getL());
                        dialog_shop_l.setVisibility("".equals(mCartShopInfoModel.getO().getL())?View.INVISIBLE:View.VISIBLE);
                        dialog_shop_m.setText(mCartShopInfoModel.getO().getXL());
                        dialog_shop_m.setVisibility("".equals(mCartShopInfoModel.getO().getXL())?View.INVISIBLE:View.VISIBLE);

                        popReduce.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                intNum= intNum-1;
                                    dialogShopNum.setText(intNum>=0?String.valueOf(intNum):"0");
                            }
                        });
                        popAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                intNum= intNum+1;
                                    dialogShopNum.setText((intNum)>=0?String.valueOf(intNum):"0");
                            }
                        });
                        dialogShopDelete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popupWindow.dismiss();
                            }
                        });
                        dialog_shop_s.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                hiddenAll();
                                dialog_shop_s.setBackgroundColor(getResources().getColor(R.color.font_color));
                                dialog_shop_s.setTextColor(getResources().getColor(R.color.white));
                                sizemls="S";
                            }
                        });
                        dialog_shop_l.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                hiddenAll();
                                dialog_shop_l.setBackgroundColor(getResources().getColor(R.color.font_color));
                                dialog_shop_l.setTextColor(getResources().getColor(R.color.white));
                                sizemls="L";
                            }
                        });
                        dialog_shop_m.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                hiddenAll();
                                dialog_shop_m.setBackgroundColor(getResources().getColor(R.color.font_color));
                                dialog_shop_m.setTextColor(getResources().getColor(R.color.white));
                                sizemls="M";
                            }
                        });
                        dialogShopBut.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
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
        }
        if(obj instanceof String){
            Toast.makeText(this, obj.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void addCart(Map<String,String> parm){
        OkHttpUtil.getInstance().addRequestPost(Config.addCart, parm, new OkHttpUtil.HttpCallBack<BaseModel>() {
            @Override
            public void onSuccss(BaseModel baseModel) {
                    EventBus.getDefault().post(baseModel.getM());
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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public CartShopInfoModel mCartShopInfoModel;

    @OnClick(R.id.activity_single_product_details_gwc)
    public void onClick() {
        getdate();
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

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("SingleProductDetails Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
