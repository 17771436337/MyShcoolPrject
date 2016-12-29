package com.example.a.myapplication.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.MessageModel;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.UIUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/9.
 */
public class MessageHolder extends BaseHolder<MessageModel.Message> {

    @InjectView(R.id.icon)
    protected ImageView icon;

    @InjectView(R.id.text)
    protected TextView text;

    @InjectView(R.id.time)
    protected TextView time;

    @InjectView(R.id.img)
    protected ImageView img;

    @InjectView(R.id.content)
    protected TextView content;

    @InjectView(R.id.l1)
    protected LinearLayout l1;


    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_msg_list, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void refreshUI(MessageModel.Message data) {
        time.setText(data.getTime());
        text.setText(data.getContent());
        l1.setVisibility(View.VISIBLE);
        switch (data.getType()) {
            case "1": // 1 发货1，收藏2，评论3，关注4，单品解答5，风格解答6
                img.setVisibility(View.VISIBLE);
                content.setVisibility(View.VISIBLE);
                Glide.with(UIUtils.getContext()).load(Config.hostImgString + data.getInfo().getImg())
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .centerCrop()
                        .crossFade().into(img);
                content.setText("#" + data.getInfo().getBrand() + "      #" + data.getInfo().getCategory());
                icon.setImageResource(R.drawable.icon_message_1);
                break;
            case "2": // 1 发货1，收藏2，评论3，关注4，单品解答5，风格解答6
                img.setVisibility(View.VISIBLE);
                content.setVisibility(View.VISIBLE);
                Glide.with(UIUtils.getContext()).load(Config.hostImgString + data.getInfo().getImg())
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .centerCrop()
                        .crossFade().into(img);
                content.setText("#" + data.getInfo().getBrand() + "      #" + data.getInfo().getCategory());
                icon.setImageResource(R.drawable.icon_message_2);
                break;
            case "3": // 1 发货1，收藏2，评论3，关注4，单品解答5，风格解答6
                img.setVisibility(View.GONE);
                content.setVisibility(View.VISIBLE);
                content.setText(data.getInfo().getComment());
                icon.setImageResource(R.drawable.icon_message_3);
                break;
            case "4": // 1 发货1，收藏2，评论3，关注4，单品解答5，风格解答6
                img.setVisibility(View.GONE);
                content.setVisibility(View.GONE);
                l1.setVisibility(View.GONE);
                icon.setImageResource(R.drawable.icon_message_4);
                break;
            case "5": // 1 发货1，收藏2，评论3，关注4，单品解答5，风格解答6
                img.setVisibility(View.VISIBLE);
                content.setVisibility(View.VISIBLE);

                if (data.getInfo() != null) {
                    Glide.with(UIUtils.getContext()).load(Config.hostImgString + data.getInfo().getImg())
                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                            .centerCrop()
                            .crossFade().into(img);
                    content.setText("点击查看详情");
                } else {
                    l1.setVisibility(View.GONE);
                }
                icon.setImageResource(R.drawable.icon_message_5);
                break;
            case "6": // 1 发货1，收藏2，评论3，关注4，单品解答5，风格解答6
                img.setVisibility(View.VISIBLE);
                content.setVisibility(View.VISIBLE);

                if (data.getInfo() != null) {
                    Glide.with(UIUtils.getContext()).load(Config.hostImgString + data.getInfo().getImg())
                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                            .centerCrop()
                            .crossFade().into(img);
                    content.setText("点击查看详情");
                } else {
                    l1.setVisibility(View.GONE);
                }
                icon.setImageResource(R.drawable.icon_message_5);
                break;


        }
    }
}
