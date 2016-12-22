package com.example.a.myapplication.holder;

import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.WatchlistModel;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.UIUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/22.
 */
public class WatchlistHolder extends BaseHolder<WatchlistModel.OBean> {

    @InjectView(R.id.head)
    protected ImageView head;

    @InjectView(R.id.name)
    protected TextView nameText;
    //
    @InjectView(R.id.no)
    protected TextView no;//取消

    @InjectView(R.id.yes)
    protected TextView yes;//已关注

    DisplayImageOptions options;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_myfans_list, null);
        ButterKnife.inject(this, view);
//        options = UIUtils.getRoundedDisplayOptions(R.drawable.android);
        return view;
    }

    @Override
    protected void refreshUI(WatchlistModel.OBean data) {
        no.setText("取消");

        Glide.with(UIUtils.getContext()).load(Config.hostImgString + data.getFhead()).asBitmap().centerCrop().into(new BitmapImageViewTarget(head) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(UIUtils.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                head.setImageDrawable(circularBitmapDrawable);
            }
        });

        nameText.setText(data.getFname());
        yes.setVisibility(View.VISIBLE);
        no.setVisibility(View.GONE);
//        if (data.().equals("0")) {//未关注   /
//            yes.setVisibility(View.GONE);
//            no.setVisibility(View.VISIBLE);
//        } else {
//            yes.setVisibility(View.VISIBLE);
//            no.setVisibility(View.GONE);
//        }

    }

}
