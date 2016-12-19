package com.example.a.myapplication.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a.myapplication.R;
import com.example.a.myapplication.activity.ProductDetailsActivity;
import com.example.a.myapplication.activity.screen.ScreenActivity;
import com.example.a.myapplication.util.CommonUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectViews;
import butterknife.OnClick;

import static android.graphics.Color.TRANSPARENT;

/**
 * Created by Administrator on 2016/12/15.
 */
public class LookShopDialog extends PopupWindow {
    private Context context;
    View view;

    @InjectViews({R.id.text1, R.id.text2})
    protected List<TextView> textView;

    @InjectViews({R.id.line1, R.id.line2})
    protected List<View> lineView;

    @InjectViews({R.id.l1_layout, R.id.l2_layout})
    protected List<LinearLayout> layouts;


    private final int SCREEN_REQUESTCODE = 0x0001;  //筛选携带参数返回值

    public LookShopDialog(Context context) {
        super(context);
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.dialog_lookshop, null);
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
        setSeleoct(1);

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

    @OnClick({R.id.right, R.id.l1, R.id.l2})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.right://筛选
                CommonUtils.startIntent(((ProductDetailsActivity) context), ScreenActivity.class, SCREEN_REQUESTCODE);
                break;
            case R.id.l1:
                setSeleoct(0);
//                index.getIndex(0);
                break;
            case R.id.l2:

                setSeleoct(1);
//                index.getIndex(1);
                break;
        }
    }

    /**
     * 选择器
     */
    private void setSeleoct(int i) {

        for (int j = 0; j < textView.size(); j++) {
            if (i == j) {
                textView.get(j).setTextColor(context.getResources().getColor(R.color.black_text));
                lineView.get(j).setBackgroundResource(R.color.black);

            } else {

                lineView.get(j).setBackgroundDrawable(new ColorDrawable(TRANSPARENT));
                textView.get(j).setTextColor(context.getResources().getColor(R.color.black_transparency_text));
            }
        }
    }


    private Index index;

    public void getIndex(Index index) {
        this.index = index;
    }

    public interface Index {
        void getIndex(int i);
    }
}
