package com.example.a.myapplication.activity.stylist;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.FragAdapter;
import com.example.a.myapplication.adapter.MyFragmentPagerAdapter;
import com.example.a.myapplication.bean.ProductTitleMessageModel;
import com.example.a.myapplication.fragment.ProductTitleMessageTwoFragment;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ProductTitleMessageActivity extends BaseActivity {


    @InjectView(R.id.back)
    ImageView back;
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.title_right)
    ImageView titleRight;
    @InjectView(R.id.img_f)
    ImageView imgF;
    @InjectView(R.id.layout_comment_tv)
    TextView layoutCommentTv;
    @InjectView(R.id.layout_comment_count)
    TextView layoutCommentCount;


    ProductTitleMessageModel mProductTitleMessageModel;
    @InjectView(R.id.activity_product_title_message_vp)
    ViewPager activityProductTitleMessageVp;
    @InjectView(R.id.layout_comment_rl)
    FrameLayout layoutCommentRl;
    @InjectView(R.id.layout_comment_but)
    Button layoutCommentBut;
    FragAdapter adapter;
    List<ProductTitleMessageTwoFragment> list;
    @Override
    protected int getLayoutID() {
        return R.layout.activity_product_title_message;
    }

    @Override
    protected void initView() {
        initIcon();
    }

    @Override
    protected void initData() {
        Map<String, String> parm = CommonUtils.getMapParm();
        parm.put("itemid",getIntent().getExtras().getString("id"));
        OkHttpUtil.getInstance().addRequestPost(Config.ANSWERLIST, parm, new OkHttpUtil.HttpCallBack<ProductTitleMessageModel>() {
            @Override
            public void onSuccss(ProductTitleMessageModel productTitleMessageModel) {
                EventBus.getDefault().post(productTitleMessageModel);
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }
    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);
        if(obj instanceof ProductTitleMessageModel){
            mProductTitleMessageModel=  (ProductTitleMessageModel)obj;
            for(int i=0;i<mProductTitleMessageModel.getO().size();i++){
                ProductTitleMessageTwoFragment mProductTitleMessageTwoFragment=   ProductTitleMessageTwoFragment.newInstance(mProductTitleMessageModel.getO().get(i));
                if(null==list){
                    list=new ArrayList<ProductTitleMessageTwoFragment>();
                }
                list.add(mProductTitleMessageTwoFragment);
            }
            if(null==adapter){
                adapter = new FragAdapter(getSupportFragmentManager(),list);
                activityProductTitleMessageVp.setAdapter(adapter);
            }
            adapter.getFragmentList().addAll(list);
            adapter.notifyDataSetChanged();
        }
    }

    public void initIcon(){
        layoutCommentTv.setText(getResources().getString(R.string.icon_pl));
        initIconFont(layoutCommentTv);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
