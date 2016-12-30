package com.example.a.myapplication.holder;

import android.view.View;
import android.widget.TextView;

import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.ProductTitleMessageModel;
import com.example.a.myapplication.util.UIUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/9.
 */
public class ProductTitleMessageTwoHolder extends BaseHolder<ProductTitleMessageModel.OBean.DetailsBean> {
    @InjectView(R.id.item_message_list_status)
    TextView itemMessageListStatus;
    @InjectView(R.id.item_message_list_pp)
    TextView itemMessageListPp;
    @InjectView(R.id.item_message_list_pl)
    TextView itemMessageListPl;
    private int position;
    View view;
    public ProductTitleMessageTwoHolder(int position) {
        this.position = position;
    }

    @Override
    protected View initView() {
         view = View.inflate(UIUtils.getContext(), R.layout.item_message_list, null);
        ButterKnife.inject(this,view);
        return view;
    }

    @Override
    protected void refreshUI(ProductTitleMessageModel.OBean.DetailsBean data) {
        itemMessageListStatus.setText(String.valueOf(position+1));
        itemMessageListPp.setText(data.getBrand());
        itemMessageListPl.setText(data.getCategory());
    }
}
