package com.example.a.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.a.myapplication.BaseApplication;
import com.example.a.myapplication.BaseFragment;
import com.example.a.myapplication.R;
import com.example.a.myapplication.activity.stylist.ProductTitleMessageActivity;
import com.example.a.myapplication.activity.stylist.SingleProductDetailsActivity;
import com.example.a.myapplication.adapter.ProductTitleMessageTwoAdapter;
import com.example.a.myapplication.bean.ProductTitleMessageModel;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.view.LoadingPager;
import com.example.a.myapplication.view.RoundImageView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 具体itme
 */
public class ProductTitleMessageTwoFragment extends BaseFragment {
    ProductTitleMessageModel.OBean oBean;
    View view;
    @InjectView(R.id.fragment_product_title_message_two_pull_layout)
    PullToRefreshListView fragmentProductTitleMessageTwoPullLayout;

    @InjectView(R.id.activity_product_title_message_tv1)
    TextView activityProductTitleMessageTv1;
    @InjectView(R.id.activity_product_title_message_tv2)
    TextView activityProductTitleMessageTv2;

    ProductTitleMessageTwoAdapter adapter;
    @InjectView(R.id.fragment_product_title_message_two_riv)
    RoundImageView fragmentProductTitleMessageTwoRiv;
    @InjectView(R.id.fragment_product_title_message_two_name)
    TextView fragmentProductTitleMessageTwoName;
    @InjectView(R.id.fragment_product_title_message_two_con)
    TextView fragmentProductTitleMessageTwoCon;
    public int postion;
    public int numSum;
    public static ProductTitleMessageTwoFragment newInstance(ProductTitleMessageModel.OBean oBean,int postion,int numSum) {
        Bundle args = new Bundle();
        ProductTitleMessageTwoFragment fragment = new ProductTitleMessageTwoFragment();
        args.putParcelable("OBean", oBean);
        args.putInt("postion", postion);
        args.putInt("numSum", numSum);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        oBean = getArguments().getParcelable("OBean");
        postion = getArguments().getInt("postion");
        numSum = getArguments().getInt("numSum");
    }

    @Override
    protected View onLoadSuccessView() {
        view = View.inflate(getActivity(), R.layout.fragment_product_title_message_two, null);
        ButterKnife.inject(this, view);
        initIcon();
        adapter = new ProductTitleMessageTwoAdapter(fragmentProductTitleMessageTwoPullLayout, oBean.getDetails());
        fragmentProductTitleMessageTwoPullLayout.setMode(PullToRefreshBase.Mode.DISABLED);
        fragmentProductTitleMessageTwoPullLayout.getRefreshableView().setAdapter(adapter);
        fragmentProductTitleMessageTwoPullLayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("sid", oBean.getDetails().get(i - 1).getId());
                CommonUtils.startIntent(BaseApplication.mCurrentActivity, SingleProductDetailsActivity.class, bundle);
            }
        });

        ImageLoader.getInstance().displayImage(Config.hostImgString+oBean.getHead(),fragmentProductTitleMessageTwoRiv);
        fragmentProductTitleMessageTwoName.setText(oBean.getName());
        return view;
    }

    @Override
    protected LoadingPager.LoadedResult onLoadingData() {

        try {
            if (null == oBean) {
                return LoadingPager.LoadedResult.EMPTY;
            }
            return LoadingPager.LoadedResult.SUCCESS;
        } catch (Exception e) {
            return LoadingPager.LoadedResult.ERROR;
        }
    }

    public void initIcon() {
        activityProductTitleMessageTv1.setText(getResources().getString(R.string.icon_syy1));
        activityProductTitleMessageTv2.setText(getResources().getString(R.string.icon_xyy1));

        initIconFont(activityProductTitleMessageTv1);
        initIconFont(activityProductTitleMessageTv2);

        if(postion==0){
            activityProductTitleMessageTv1.setTextColor(getActivity().getResources().getColor(R.color.icon_sxy_color));
        }if(postion==numSum-1){
            activityProductTitleMessageTv2.setTextColor(getActivity().getResources().getColor(R.color.icon_sxy_color));
        }
    }
    @OnClick({R.id.activity_product_title_message_tv1,R.id.activity_product_title_message_tv2})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_product_title_message_tv1:
              /*  if((postion-1)<=0){
                    activityProductTitleMessageTv1.setTextColor(getActivity().getResources().getColor(R.color.icon_sxy_color));
                }else{
                    activityProductTitleMessageTv1.setTextColor(getActivity().getResources().getColor(R.color.icon_sxy_color1));
                }*/
                ((ProductTitleMessageActivity)getActivity()).onIconClick(postion-1);
                break;
            case R.id.activity_product_title_message_tv2:

                ((ProductTitleMessageActivity)getActivity()).onIconClick(postion+1);
                break;
        }
    }
    public static interface onIocnClickListener{

        public void onIconClick(int postion);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
