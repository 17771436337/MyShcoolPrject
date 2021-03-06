package com.example.a.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a.myapplication.MainActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.activity.MessageActvity;
import com.example.a.myapplication.activity.screen.ScreenActivity;
import com.example.a.myapplication.util.CommonUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/1/3.
 */
public class TestFragment extends Fragment {
    View view;
    @InjectView(R.id.title_text)
    protected TextView titleText;

    @InjectView(R.id.right)
    protected ImageView tightImg;
    private final int SCREEN_REQUESTCODE = 0x0001;  //筛选携带参数返回值

    @InjectView(R.id.img)
    protected ImageView img;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_wardrobe, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.inject(this, view);
        initTitleView();
        Animation animation = new AlphaAnimation(0.1f, 1.0f);
        animation.setDuration(2000);
        img.startAnimation(animation);

    }

    /**
     * 设置标题
     */
    private void initTitleView() {
        titleText.setText("造型师");
        tightImg.setImageResource(R.drawable.icon_screen);

    }

    @OnClick({R.id.right, R.id.message_icon})
    public void OnClic(View v) {
        switch (v.getId()) {

            case R.id.right://筛选
                CommonUtils.startIntent(((MainActivity) getActivity()), ScreenActivity.class, SCREEN_REQUESTCODE);
                break;
            case R.id.message_icon://消息
                CommonUtils.startIntent(getActivity(), MessageActvity.class);
                break;
        }
    }
}
