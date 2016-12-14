package com.example.a.myapplication.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.FansModel;
import com.example.a.myapplication.util.UIUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import butterknife.ButterKnife;
import butterknife.InjectView;

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

    DisplayImageOptions options;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_myfans_list, null);
        ButterKnife.inject(this, view);
//        options = UIUtils.getRoundedDisplayOptions(R.drawable.android);
        return view;
    }

    @Override
    protected void refreshUI(FansModel.MyFans data) {
//        ImageLoader.getInstance().displayImage(data.getImg(), head,options);
        nameText.setText(data.getName());
        if (data.getType() == 1) {
            no.setVisibility(View.VISIBLE);
            yes.setVisibility(View.GONE);
        } else {
            yes.setVisibility(View.VISIBLE);
            no.setVisibility(View.GONE);
        }
    }
}
