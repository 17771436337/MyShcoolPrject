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
<<<<<<< HEAD
public class IntegralHplder extends BaseHolder<IntegralModel.OBean> {

    @InjectView(R.id.text)
    protected TextView describe;

    @InjectView(R.id.time)
    protected TextView time;

    @InjectView(R.id.integral)
    protected TextView integral;

    @InjectView(R.id.icon)
    protected ImageView icon;

=======
public class IntegralHplder extends BaseHolder<IntegralModel.OBean>{
>>>>>>> 21727943196c6f0683ea0d7c14fda5858d2c8dd1
    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_integral_list, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void refreshUI(IntegralModel.OBean data) {
<<<<<<< HEAD

        describe.setText(data.getDescribe());
        time.setText(data.getTime());
        integral.setText(data.getIntegral());

        switch (data.getType()) {
            case "1":  //1邀请好友
                icon.setImageResource(R.drawable.icon_integeral_1);
                break;
            case "2":  //2购物
                icon.setImageResource(R.drawable.icon_integeral_2);
                break;
            case "3":  //3分享商品
                icon.setImageResource(R.drawable.icon_integeral_1);
                break;
            case "4": //4分享APP
                icon.setImageResource(R.drawable.icon_integeral_1);
                break;
            case "5": //  5好物置换
                icon.setImageResource(R.drawable.icon_integeral_5);
                break;
        }

=======
>>>>>>> 21727943196c6f0683ea0d7c14fda5858d2c8dd1

    }
}
