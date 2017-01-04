package com.example.a.myapplication.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.myapplication.R;
import com.example.a.myapplication.activity.IntegralActivity;
import com.example.a.myapplication.activity.MessageActvity;
import com.example.a.myapplication.activity.MyCollectActivity;
import com.example.a.myapplication.activity.MyFansActivity;
import com.example.a.myapplication.activity.MyOrderActivity;
import com.example.a.myapplication.activity.MyProFileActivity;
import com.example.a.myapplication.activity.MyWatchlistActivity;
import com.example.a.myapplication.activity.ShopActivity;
import com.example.a.myapplication.activity.app.SettingActivity;
import com.example.a.myapplication.bean.MianProfineModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.Preference;
import com.example.a.myapplication.util.UIUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static android.widget.Toast.makeText;


public class MineFragment extends Fragment {

    View view;


    @InjectView(R.id.title_text)
    protected TextView titleText;//标题

    @InjectView(R.id.right)
    protected ImageView setImg;//设置图标

    @InjectView(R.id.head)
    protected ImageView head;//头像

    @InjectView(R.id.name)
    protected TextView name;//名字

    @InjectView(R.id.fans_text)
    protected TextView fansTextView; //粉丝数

    @InjectView(R.id.attention_text)
    protected TextView attentionTextView;//关注数

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_mine, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ButterKnife.inject(this, view);
        initTtitle();
        initData();
    }

    private void initData() {
    }


    /**
     * 设置标题
     */
    private void initTtitle() {
        titleText.setText(" ");
        setImg.setImageResource(R.drawable.icon_setting);
    }


    /**
     * 监听事件
     */
    @OnClick({R.id.order_text, R.id.shopping_text, R.id.integral_text,
            R.id.replacement_text, R.id.collect_text, R.id.fans_layout,
            R.id.attention_layout, R.id.right, R.id.file_text, R.id.message_icon,
            R.id.head})
    public void OnClic(View v) {
        switch (v.getId()) {
            case R.id.order_text://我的订单
                CommonUtils.startIntent(getActivity(), MyOrderActivity.class);
                break;
            case R.id.shopping_text://购物车
                CommonUtils.startIntent(getActivity(), ShopActivity.class);
                break;
            case R.id.integral_text://我的积分
                CommonUtils.startIntent(getActivity(), IntegralActivity.class);
                break;
            case R.id.replacement_text://好物置换
                makeText(getActivity(), "研发中，敬请期待", Toast.LENGTH_SHORT).show();
                break;
            case R.id.collect_text://我的收藏
                CommonUtils.startIntent(getActivity(), MyCollectActivity.class);
                break;
            case R.id.fans_layout: //我的粉丝
                CommonUtils.startIntent(getActivity(), MyFansActivity.class);
                break;
            case R.id.attention_layout: //我的关注
                CommonUtils.startIntent(getActivity(), MyWatchlistActivity.class);
                break;
            case R.id.right://设置

                CommonUtils.startIntent(getActivity(), SettingActivity.class);
                break;
            case R.id.file_text://更换资料
                CommonUtils.startIntent(getActivity(), MyProFileActivity.class);
                break;
            case R.id.message_icon://消息
                CommonUtils.startIntent(getActivity(), MessageActvity.class);
                break;
            case R.id.head://点击头像
                CommonUtils.startIntent(getActivity(), MyProFileActivity.class);
                break;


        }
    }


    /**
     * 获取用户信息
     */
    private void getProfile() {

        Map<String, String> pam = new HashMap<String, String>();
        pam.put("userid", Preference.get(Config.ID, ""));
        OkHttpUtil.getInstance().addRequestPost(Config.getProfile, pam, new OkHttpUtil.HttpCallBack<MianProfineModel>() {

            @Override
            public void onSuccss(MianProfineModel mianProfineModel) {
                Message message = new Message();
                message.obj = mianProfineModel;
                message.what = 0x0001;
                handler.sendMessage(message);


            }

            @Override
            public void onFailure(String error) {

            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        getProfile();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 0x0001) {
                MianProfineModel mianProfineModel = (MianProfineModel) msg.obj;
                if (mianProfineModel.getC() == 1) {

                    ImageLoader.getInstance().displayImage(Config.hostImgString + mianProfineModel.getO().getHead(), head, UIUtils.getRoundedDisplayOptions(R.drawable.default_head));

                    name.setText(mianProfineModel.getO().getName());
                    fansTextView.setText(mianProfineModel.getO().getFans());
                    attentionTextView.setText(mianProfineModel.getO().getFocus());
                    Preference.put(Config.REMIND, mianProfineModel.getO().getRemind());
                    Preference.put(Config.HEAD, mianProfineModel.getO().getHead());


                } else {

                }
            }
        }
    };
}
