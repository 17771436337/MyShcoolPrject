package com.example.a.myapplication.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.myapplication.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/14.
 */
public class ShopDialog extends PopupWindow {
    private Context context;

    @InjectView(R.id.pop_num)
    protected TextView pop_num;

    private final int ADDORREDUCE = 1;//购买数量

    public ShopDialog(Context context) {
        super(context);
        this.context = context;

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_shop, null);
        ButterKnife.inject(this, view);
        this.setContentView(view);
        // ����SelectPicPopupWindow��������Ŀ�
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        // ����SelectPicPopupWindow��������ĸ�
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置popwindow的动画效果
        this.setAnimationStyle(R.style.popWindow_anim_style);
        this.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }




    /**
     * 弹窗显示的位置
     */
    public void showAsDropDown(View parent) {
        this.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
    }

    @OnClick({R.id.pop_add, R.id.pop_reduce})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pop_reduce:
                if (!pop_num.getText().toString().equals("1")) {
                    String num_reduce = Integer.valueOf(pop_num.getText().toString()) - ADDORREDUCE + "";
                    pop_num.setText(num_reduce);
                } else {
                    Toast.makeText(context, "购买数量不能低于1件", Toast.LENGTH_SHORT).show();
                }
                break;


            case R.id.pop_add:
                if (!pop_num.getText().toString().equals("750")) {

                    String num_add = Integer.valueOf(pop_num.getText().toString()) + ADDORREDUCE + "";
                    pop_num.setText(num_add);
                } else {
                    Toast.makeText(context, "不能超过最大产品数量", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }


    /**
     * S
     * 消除弹窗
     */
    public void dissmiss() {
        this.dismiss();
    }

}
