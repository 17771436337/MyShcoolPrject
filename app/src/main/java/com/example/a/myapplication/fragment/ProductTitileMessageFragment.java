package com.example.a.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a.myapplication.R;
import com.example.a.myapplication.dialog.LookShopDialog;

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

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_titlemessage_shop, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.inject(this, view);


    }


    @OnClick({R.id.check_layout, R.id.answer})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.check_layout:
                cacheOnClick.cacheOnClick();
//                makeText(getActivity(),"点击有效",1000).show();
                break;
            case R.id.answer:
//                CommonUtils.startIntent(getActivity(),AddShopActivity.class);

                LookShopDialog dialog = new LookShopDialog(getActivity());
                dialog.showAsDropDown(v);
                break;

        }
    }


    //---------------------------------------
    /**
     * 查看解答
     */
    private SetOnClick cacheOnClick;

    public void cacheOnClick(SetOnClick cacheOnClick) {
        this.cacheOnClick = cacheOnClick;
    }

    public interface SetOnClick {
        void cacheOnClick();
    }
    //---------------------------------------

}
