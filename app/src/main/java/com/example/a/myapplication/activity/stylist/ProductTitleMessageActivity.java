package com.example.a.myapplication.activity.stylist;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.FragAdapter;
import com.example.a.myapplication.bean.ProductTitleMessageModel;
import com.example.a.myapplication.fragment.ProductTitleMessageTwoFragment;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.view.CustomViewPager;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.InjectView;
import butterknife.OnClick;

public class ProductTitleMessageActivity extends BaseActivity implements ProductTitleMessageTwoFragment.onIocnClickListener {
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
    CustomViewPager activityProductTitleMessageVp;
    @InjectView(R.id.layout_comment_but)
    Button layoutCommentBut;
    FragAdapter adapter;
    List<ProductTitleMessageTwoFragment> list;
    int mPage = 1;

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
        parm.put("itemid", getIntent().getExtras().getString("id"));
        parm.put("pagination", String.valueOf(mPage));
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
        if (obj instanceof ProductTitleMessageModel) {
            mProductTitleMessageModel = (ProductTitleMessageModel) obj;
            if (mProductTitleMessageModel.getO().size() > 0) {
                for (int i = 0; i < mProductTitleMessageModel.getO().size(); i++) {
                    ProductTitleMessageTwoFragment mProductTitleMessageTwoFragment = ProductTitleMessageTwoFragment.newInstance(mProductTitleMessageModel.getO().get(i),i,mProductTitleMessageModel.getO().size());
                    if (null == list) {
                        list = new ArrayList<ProductTitleMessageTwoFragment>();
                    }
                    list.add(i, mProductTitleMessageTwoFragment);
                }
            } else {
                activityProductTitleMessageVp.setVisibility(View.GONE);
            }
            if (null == adapter) {
                adapter = new FragAdapter(getSupportFragmentManager(), list, mProductTitleMessageModel.getO());
                activityProductTitleMessageVp.setAdapter(adapter);
            }
            if (mPage != 1) {
                adapter.getFragmentList().addAll(list);
                adapter.notifyDataSetChanged();
            }

        }
    }

    public void initIcon() {
        title.setText("单品详情");
        ImageLoader.getInstance().displayImage(Config.hostImgString + getIntent().getExtras().getString("imgurl"), imgF);
        layoutCommentTv.setText(getResources().getString(R.string.icon_pl));
        initIconFont(layoutCommentTv);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductTitleMessageActivity.this.finish();
            }
        });
    }

    @OnClick({R.id.layout_comment_to_pl,R.id.layout_comment_but})
    public void onClick() {
        Bundle bundle = new Bundle();
        //// TODO: 2016/12/24 根据Viewpage滑动的postion 来决定
        if(null==mProductTitleMessageModel){

        }
      //  bundle.putString("rid", mProductTitleMessageModel.getO().get(0).getRid());
        Toast.makeText(ProductTitleMessageActivity.this,"功能还在开发中。。。",Toast.LENGTH_SHORT).show();
        // CommonUtils.startIntent(this, CommentListActivity.class,bundle);
    }

    @Override
    public void onIconClick(int postion) {
        if(postion<=0){

            activityProductTitleMessageVp.setCurrentItem(0,true);
        }if(postion>=mProductTitleMessageModel.getO().size()){
            activityProductTitleMessageVp.setCurrentItem(mProductTitleMessageModel.getO().size()-1);
        }else{
            activityProductTitleMessageVp.setCurrentItem(postion,true);
        }
    }
}
