package com.example.a.myapplication.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.a.myapplication.BaseFragment;
import com.example.a.myapplication.R;
import com.example.a.myapplication.activity.stylist.SingleProductDetailsActivity;
import com.example.a.myapplication.adapter.ProductTitleMessageTwoAdapter;
import com.example.a.myapplication.bean.ProductTitleMessageModel;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.view.LoadingPager;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.ButterKnife;
import butterknife.InjectView;

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
    public static ProductTitleMessageTwoFragment newInstance(ProductTitleMessageModel.OBean oBean) {
        Bundle args = new Bundle();
        ProductTitleMessageTwoFragment fragment = new ProductTitleMessageTwoFragment();
        args.putSerializable("OBean", oBean);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        oBean = (ProductTitleMessageModel.OBean) getArguments().getSerializable("OBean");
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
                CommonUtils.startIntent(getActivity(), SingleProductDetailsActivity.class);
            }
        });
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
    public void initIcon(){
        activityProductTitleMessageTv1.setText(getResources().getString(R.string.icon_syy));
        activityProductTitleMessageTv2.setText(getResources().getString(R.string.icon_xyy));

        initIconFont(activityProductTitleMessageTv1);
        initIconFont(activityProductTitleMessageTv2);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
