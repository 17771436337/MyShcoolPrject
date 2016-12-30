package com.example.a.myapplication.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.activity.stylist.ProductTitleMessageActivity;
import com.example.a.myapplication.bean.BaseModel;
import com.example.a.myapplication.bean.ProductDetailsModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.Preference;
import com.example.a.myapplication.util.UIUtils;
import com.example.a.myapplication.view.RoundImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.example.a.myapplication.http.OkHttpUtil.getInstance;
import static org.greenrobot.eventbus.EventBus.*;

/**
 * Created by Administrator on 2016/12/12.
 * 求单品详情
 */
public class ProductDetailsActivity extends BaseActivity {
    @InjectView(R.id.img_f)
    ImageView imgF;
    @InjectView(R.id.head)
    RoundImageView head;
    @InjectView(R.id.name)
    TextView name;
    @InjectView(R.id.shop_num)
    TextView shopNum;
    @InjectView(R.id.message_num)
    TextView messageNum;
    @InjectView(R.id.check_text)
    TextView checkText;
    @InjectView(R.id.answer)
    TextView answer;
    @InjectView(R.id.title)
    protected TextView titleText;
    @InjectView(R.id.title_right)
    protected ImageView titleRight;
    @InjectView(R.id.check_layout)
    protected FrameLayout check_layout;
    public ProductDetailsModel mProductDetailsModel;
    String is_collection;
    @Override
    protected int getLayoutID() {
        return R.layout.activity_productdetail;
    }

    @Override
    protected void initView() {
        Log.e("id", getIntent().getExtras().getString("id"));
        is_collection= getIntent().getExtras().getString("is_collection");
        titleText.setText("单品详情");
    }
    @Override
    protected void initData() {
        Map<String, String> parm = CommonUtils.getMapParm();
        parm.put("itemid", getIntent().getExtras().getString("id"));
        getInstance().addRequestPost(Config.StylistDe, parm, new OkHttpUtil.HttpCallBack<ProductDetailsModel>() {
            @Override
            public void onSuccss(ProductDetailsModel productDetailsModel) {
                getDefault().post(productDetailsModel);
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);
        if (obj instanceof ProductDetailsModel) {
            mProductDetailsModel = (ProductDetailsModel) obj;
            ImageLoader.getInstance().displayImage(Config.hostImgString + mProductDetailsModel.getO().getImg(), imgF);
            ImageLoader.getInstance().displayImage(Config.hostImgString + mProductDetailsModel.getO().getHead(), head);
            name.setText(mProductDetailsModel.getO().getName());
            messageNum.setText(mProductDetailsModel.getO().getCollection());
            shopNum.setText(mProductDetailsModel.getO().getComment());
        }
    }

    @Override
    public void onEvent(Object obj) {
        super.onEvent(obj);
    }

    @Override
    public void onEventAsyncThread(Object obj) {
        super.onEventAsyncThread(obj);
    }

    @Override
    public void onEventBackgroundThread(Object obj) {
        super.onEventBackgroundThread(obj);
    }

    @OnClick({R.id.back, R.id.check_layout,R.id.message_num})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.back: //返回
                finish();
                break;
            case R.id.check_layout:
                Bundle bundle = getIntent().getExtras();
                if (mProductDetailsModel.getO() != null) {
                    bundle.putString("imgurl", mProductDetailsModel.getO().getImg());
                }
                CommonUtils.startIntent(this, ProductTitleMessageActivity.class, bundle);
                break;
            case R.id.message_num:
                messageNum.setEnabled(false);
                if("1".equals(is_collection)){
                    is_collection="2";
                }else{
                    is_collection="1";
                }
                toSc();
                break;
        }
    }

    public void toSc(){
        Map<String,String> parm=CommonUtils.getMapParm();
        parm.put("uid", Preference.get(Config.ID,""));
        parm.put("itemid",getIntent().getExtras().getString("id"));
        parm.put("type",is_collection);
        OkHttpUtil.getInstance().addRequestPost(Config.styleCollect, parm, new OkHttpUtil.HttpCallBack<BaseModel>() {
            @Override
            public void onSuccss(final BaseModel baseModel) {
                UIUtils.post(new Runnable() {
                    @Override
                    public void run() {
                        if(1==baseModel.getC()){
                            messageNum.setEnabled(true);
                            Drawable drawable= getResources().getDrawable(R.drawable.sch);
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(),drawable.getMinimumHeight());
                            messageNum.setCompoundDrawables(drawable,null,null,null);
                            messageNum.setText(Integer.parseInt(messageNum.getText().toString())+1+"");
                        }
                    }
                });
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.answer)
    public void onanswer(){
        Toast.makeText(this,"功能正在开发中。。。",Toast.LENGTH_SHORT).show();
    }

}
