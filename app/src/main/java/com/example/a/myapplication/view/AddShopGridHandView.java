package com.example.a.myapplication.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.TextView;

import com.example.a.myapplication.BaseView;
import com.example.a.myapplication.R;
import com.example.a.myapplication.activity.AddShopActivity;
import com.example.a.myapplication.activity.screen.ScreenActivity;
import com.example.a.myapplication.util.CommonUtils;

import java.util.List;

import butterknife.InjectViews;
import butterknife.OnClick;

import static android.graphics.Color.TRANSPARENT;

/**
 * Created by Administrator on 2016/12/15.
 */
public class AddShopGridHandView extends BaseView {

    @InjectViews({R.id.text1, R.id.text2})
    protected List<TextView> textView;

    @InjectViews({R.id.line1, R.id.line2})
    protected List<View> lineView;

    private final int SCREEN_REQUESTCODE = 0x0001;  //筛选携带参数返回值

    public AddShopGridHandView(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.view_addshophand;
    }

    @Override
    protected void initView() {
        setSeleoct(1);
    }


    @OnClick({R.id.right, R.id.l1, R.id.l2})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.right://筛选
                CommonUtils.startIntent(((AddShopActivity) mContext), ScreenActivity.class, SCREEN_REQUESTCODE);
                break;
            case R.id.l1:
                setSeleoct(0);
                index.getIndex(0);
                break;
            case R.id.l2:

                setSeleoct(1);
                index.getIndex(1);
                break;
        }
    }

    /**
     * 选择器
     */
    private void setSeleoct(int i) {

        for (int j = 0; j < textView.size(); j++) {
            if (i == j) {
                textView.get(j).setTextColor(mContext.getResources().getColor(R.color.black_text));
                lineView.get(j).setBackgroundResource(R.color.black);

            } else {

                lineView.get(j).setBackgroundDrawable(new ColorDrawable(TRANSPARENT));
                textView.get(j).setTextColor(mContext.getResources().getColor(R.color.black_transparency_text));
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
