package com.example.a.myapplication.holder;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.BaseModel;
import com.example.a.myapplication.bean.FansModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.Preference;
import com.example.a.myapplication.util.UIUtils;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/7.
 */
public class MyFansHolder extends BaseHolder<FansModel.MyFans> {

    @InjectView(R.id.head)
    protected ImageView head;

    @InjectView(R.id.name)
    protected TextView nameText;
    //
    @InjectView(R.id.no)
    protected TextView no;//加关注

    @InjectView(R.id.yes)
    protected TextView yes;//已关注

    FansModel.MyFans data;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_myfans_list, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void refreshUI(FansModel.MyFans data) {

        this.data = data;


        if (TextUtils.isEmpty(data.getFhead())) {
            head.setImageResource(R.drawable.default_head);
        } else {
            Glide.with(UIUtils.getContext()).load(Config.hostImgString + data.getFhead()).asBitmap().centerCrop().into(new BitmapImageViewTarget(head) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(UIUtils.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);

                    head.setImageDrawable(circularBitmapDrawable);
                }
            });
        }
        nameText.setText(data.getFname());
        if (data.getIs_focus().equals("0")) {//未关注   /
            yes.setVisibility(View.GONE);
            no.setVisibility(View.VISIBLE);
        } else {
            yes.setVisibility(View.VISIBLE);
            no.setVisibility(View.GONE);
        }


    }


    @OnClick({R.id.no, R.id.yes})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.no://加关注
                Map<String, String> par = CommonUtils.getMapParm();
                par.put("id", Preference.get(Config.ID, ""));
                par.put("nid", data.getNid());
                OkHttpUtil.getInstance().addRequestPost(Config.addfocuson, par, new OkHttpUtil.HttpCallBack<BaseModel>() {

                    @Override
                    public void onSuccss(BaseModel baseModel) {
                        Message message = new Message();
                        message.what = 0x0001;
                        message.obj = baseModel;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onFailure(String error) {

                    }
                });

                break;
            case R.id.yes://已关注
                break;
        }
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x0001:
                    BaseModel baseModel = (BaseModel) msg.obj;
                    if (baseModel.getC() == 1) {
                        Toast.makeText(UIUtils.getContext(), "关注成功", Toast.LENGTH_SHORT).show();
                        yes.setVisibility(View.VISIBLE);
                        no.setVisibility(View.GONE);

                    } else {
                        Toast.makeText(UIUtils.getContext(), baseModel.getM() + "", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
}
