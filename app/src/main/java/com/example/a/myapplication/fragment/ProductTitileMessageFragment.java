package com.example.a.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.ProductDetailsModel;
import com.example.a.myapplication.dialog.LookShopDialog;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.view.RoundImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/13.
 * 单品详情标题信息
 */
public class ProductTitileMessageFragment extends Fragment {
    View view;

    @InjectView(R.id.check_text)
    protected TextView checkText;//查看解答
    @InjectView(R.id.head)
    RoundImageView head;
    @InjectView(R.id.name)
    TextView name;
    @InjectView(R.id.shop_num)
    TextView shopNum;
    @InjectView(R.id.message_num)
    TextView messageNum;
    @InjectView(R.id.check_layout)
    FrameLayout checkLayout;
    @InjectView(R.id.answer)
    TextView answer;
    ProductDetailsModel mProductDetailsModel;
    public static ProductTitileMessageFragment newInstance(ProductDetailsModel mProductDetailsModel) {
        Bundle args = new Bundle();
        args.putSerializable("data", mProductDetailsModel);
        ProductTitileMessageFragment fragment = new ProductTitileMessageFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductDetailsModel = (ProductDetailsModel) getArguments().getSerializable("data");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_titlemessage_shop, null);
        ButterKnife.inject(this, view);
        initView();
        return view;
    }


    public void initView(){
        ImageLoader.getInstance().displayImage(Config.hostImgString+mProductDetailsModel.getO().getHead(),head);
        name.setText(mProductDetailsModel.getO().getName());
        messageNum.setText(mProductDetailsModel.getO().getCollection());
        shopNum.setText(mProductDetailsModel.getO().getComment());

    }


    @OnClick({R.id.check_layout, R.id.answer})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.check_layout:
                cacheOnClick.cacheOnClick();
                break;
            case R.id.answer:
//                CommonUtils.startIntent(getActivity(),AddShopActivity.class);

                LookShopDialog dialog = new LookShopDialog(getActivity());
                dialog.showAsDropDown(v);
                break;

        }
    }
    private SetOnClick cacheOnClick;

    public void cacheOnClick(SetOnClick cacheOnClick) {
        this.cacheOnClick = cacheOnClick;
    }

    public interface SetOnClick {
        void cacheOnClick();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }



}
