package com.example.a.myapplication.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.ShopDialogAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/14.
 */
public class ShopDialog extends PopupWindow {
 /*   private Context context;
    View view;
    @InjectView(R.id.pop_num)
    protected TextView pop_num;

    private final int ADDORREDUCE = 1;//购买数量

    @InjectView(R.id.color_grid)
    protected GridView color;
    ArrayList<String> listColor = new ArrayList<String>();
    ShopDialogAdapter colorAdapter;

    @InjectView(R.id.size_grid)
    protected GridView size;
    ArrayList<String> sizeColor = new ArrayList<String>();
    ShopDialogAdapter sizeAdapter;

    public ShopDialog(Context context) {
        super(context);
        this.context = context;

        view = LayoutInflater.from(context).inflate(R.layout.dialog_shop, null);
        ButterKnife.inject(this, view);
        this.setContentView(view);
        // ����SelectPicPopupWindow��������Ŀ�
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        // ����SelectPicPopupWindow��������ĸ�
        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        //设置popwindow的动画效果
        this.setAnimationStyle(R.style.popWindow_anim_style);
        this.setBackgroundDrawable(new ColorDrawable(0xB0000000));
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // mMenuView���OnTouchListener�����жϻ�ȡ����λ�������ѡ�����������ٵ�����
        view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });

        initData();
    }

    private void initData() {
        listColor.add("白色");
        colorAdapter = new ShopDialogAdapter(listColor);
        color.setAdapter(colorAdapter);

        //-----------------
        sizeColor.add("S");
        sizeColor.add("SS");
        sizeColor.add("SSS");
        sizeAdapter = new ShopDialogAdapter(sizeColor);
        size.setAdapter(sizeAdapter);

    }


    *//**
     * 弹窗显示的位置
     *//*
    public void showAsDropDown(View parent) {
        this.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
    }

    @OnClick({R.id.pop_add, R.id.pop_reduce, R.id.delete})
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
            case R.id.delete:
                dismiss();
                break;
        }
    }


//    @OnItemClick({R.id.color_grid, R.id.size_grid})
//    protected void onitemClick(View v, int index) {
//        switch (v.getId()) {
//            case R.id.color_grid:
//                for (int i = 0; i < listColor.size(); i++) {
//                    if (i == index) {
//
//                    } else {
//
//                    }
//                }
//                break;
//
//            case R.id.size_grid:
//                break;
//        }
//    }
*/

    /**
     * S
     * 消除弹窗
     */
    public void dissmiss() {
        this.dismiss();
    }

}
