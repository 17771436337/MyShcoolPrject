package com.example.a.myapplication.activity;

import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.activity.WheelPicker.DatePicker;
import com.example.a.myapplication.bean.BaseModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.lib.view.LoopView;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.PopupUtil;
import com.example.a.myapplication.util.Preference;
import com.example.a.myapplication.util.UIUtils;
import com.example.a.myapplication.view.TitleView1;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Map;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/8.
 * 更换资料
 */
public class MyProFileActivity extends BaseActivity {

    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    @InjectView(R.id.name_text)
    protected TextView name;

    @InjectView(R.id.sex_text)
    protected TextView sex;

    @InjectView(R.id.date_text)
    protected TextView date;

    @InjectView(R.id.head)
    protected ImageView head;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_myprofile;
    }

    @Override
    protected void initView() {
        initTitle();
        name.setText(Preference.get(Config.NAME, ""));
        sex.setText(Preference.get(Config.SEX, ""));
        date.setText(Preference.get(Config.AGE, ""));

        Glide.with(UIUtils.getContext()).load(Config.hostImgString + Preference.get(Config.HEAD, "")).asBitmap().centerCrop().into(new BitmapImageViewTarget(head) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(UIUtils.getContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                head.setImageDrawable(circularBitmapDrawable);
            }
        });
    }


    /**
     * 标题初始化
     */
    private void initTitle() {
        TitleView1 view = new TitleView1(this);
        titleView.addView(view.getView());
        view.setTitleText("更换资料", "保存");
        view.setTitleOnClickListeneRight(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, String> par = CommonUtils.getMapParm();
                par.put("userid", Preference.get(Config.ID, ""));
                par.put("img", "");
                par.put("nickname", name.getText().toString());
                par.put("sex", sex.getText().toString());
                par.put("age", date.getText().toString());

                OkHttpUtil.getInstance().addRequestPost(Config.saveProfile, par, new OkHttpUtil.HttpCallBack<BaseModel>() {


                    @Override
                    public void onSuccss(BaseModel baseModel) {
                        EventBus.getDefault().post(baseModel);
                    }

                    @Override
                    public void onFailure(String error) {

                    }
                });
            }
        });
    }


    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);
        if (obj instanceof BaseModel) {
            BaseModel baseModel = (BaseModel) obj;
            Toast.makeText(this, baseModel.getM() + "", Toast.LENGTH_SHORT).show();
            if (baseModel.getC() == 1) {
                Preference.put(Config.NAME, name.getText().toString());
                Preference.put(Config.SEX, sex.getText().toString());
                Preference.put(Config.AGE, date.getText().toString());

                finish();
            }


        }
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.head_layout, R.id.name_layout, R.id.sex_layout, R.id.date_layout})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_layout://头像

                break;
            case R.id.name_layout://姓名
                dialogName();
                break;
            case R.id.sex_layout://性别
                dialogSex();
                break;
            case R.id.date_layout://生日
                dialogAge();
                break;
        }
    }

    /**
     * 弹出生日
     */
    private void dialogAge() {
        final DatePicker datePicker = new DatePicker(this);
        datePicker.setRange(1900, 2100);
        datePicker.setSelectedItem(2017, 1, 1);

        datePicker.setOnDatePickListener(
                new DatePicker.OnYearMonthDayPickListener() {

                    @Override
                    public void onDatePicked(String year, String month, String day) {
                        date.setText(year + "-" + month + "-" + day);
                    }
                });
        datePicker.show();

    }

    /**
     * 弹出输入文字
     */
    private void dialogSex() {
        PopupUtil.showInBottom(this, R.layout.dialog_selecor, new PopupUtil.ShowListener() {
            @Override
            public void onShow(View view, final PopupWindow popupWindow) {
                final LoopView loopView = (LoopView) view.findViewById(R.id.sex);
                TextView no = (TextView) view.findViewById(R.id.no);
                TextView yes = (TextView) view.findViewById(R.id.yes);
                final ArrayList<String> strings = new ArrayList<String>();
                strings.add("男");
                strings.add("女");
                loopView.setItems(strings);
                loopView.setNotLoop();
                loopView.setTextSize(28);


                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sex.setText(strings.get(loopView.getSelectedItem()));
                        popupWindow.dismiss();
                    }
                });

                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
            }
        });
    }

    /**
     * 弹出输入文字
     */
    private void dialogName() {
        PopupUtil.showInCenter(this, R.layout.dialog_addname, new PopupUtil.ShowListener() {
            @Override
            public void onShow(View view, final PopupWindow popupWindow) {
                final EditText nameView = (EditText) view.findViewById(R.id.name_edit);
                TextView yes = (TextView) view.findViewById(R.id.yes);
                TextView no = (TextView) view.findViewById(R.id.no);

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(nameView.getText().toString())) {
                            Toast.makeText(MyProFileActivity.this, "请输入姓名", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        name.setText(nameView.getText().toString());
                        popupWindow.dismiss();
                    }
                });

                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
            }
        });
    }
}
