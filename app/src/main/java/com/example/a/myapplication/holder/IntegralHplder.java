package com.example.a.myapplication.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.IntegralModel;
import com.example.a.myapplication.util.UIUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/8.
 */
public class IntegralHplder extends BaseHolder<IntegralModel.OBean> {

    @InjectView(R.id.text)
    protected TextView text;

    @InjectView(R.id.time)
    protected TextView time;

    @InjectView(R.id.integral)
    protected TextView integral;

    @InjectView(R.id.icon)
    protected ImageView icon;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_integral_list, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void refreshUI(IntegralModel.OBean data) {

        text.setText(data.getDescribe());
        time.setText(data.getTime());

        switch (data.getItype()) {
            case "0":
                integral.setText("+" + data.getIntegral());
                integral.setTextColor(UIUtils.getColor(R.color.red_text));

                break;
            case "1":
                integral.setText("-" + data.getIntegral());
                integral.setTextColor(UIUtils.getColor(R.color.black_transparency_text));

                break;
        }


        switch (data.getType()) {
            case "1":  // 1邀请好友2购物3分享商品4分享APP 5好物置换
                icon.setImageResource(R.drawable.icon_integeral_1);
                break;
            case "2":
                icon.setImageResource(R.drawable.icon_integeral_1);
                break;
            case "3":
                icon.setImageResource(R.drawable.icon_integeral_2);
                break;
            case "4":
                icon.setImageResource(R.drawable.icon_integeral_1);
                break;
            case "5":
                icon.setImageResource(R.drawable.icon_integeral_5);
                break;


        }

    }
}
