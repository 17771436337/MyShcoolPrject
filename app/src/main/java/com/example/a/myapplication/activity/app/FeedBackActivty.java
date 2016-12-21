package com.example.a.myapplication.activity.app;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.BaseModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.Preference;
import com.example.a.myapplication.view.TitleView1;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * Created by Administrator on 2016/12/16.
 * 意见反馈
 */
public class FeedBackActivty extends BaseActivity {
    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    @InjectView(R.id.text)
    protected TextView text;

    @InjectView(R.id.content)
    protected EditText editText;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initView() {
        initTitle();
    }

    @Override
    protected void initData() {

    }

    /**
     * 标题初始化
     */
    private void initTitle() {
        TitleView1 view = new TitleView1(this);
        titleView.addView(view.getView());
        view.setTitleText("意见反馈", "");
    }

    @OnClick(R.id.ok)
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.ok://提交
                Map<String, String> param = new HashMap<String, String>();
                param.put("uid", Preference.get(Config.ID, ""));
                String content = editText.getText().toString().trim();
                param.put("describe", content);

                OkHttpUtil.getInstance().addRequestPost(Config.addfeedback, param, new OkHttpUtil.HttpCallBack<BaseModel>() {

                    @Override
                    public void onSuccss(BaseModel baseModel) {
                        EventBus.getDefault().post(baseModel);
                    }

                    @Override
                    public void onFailure(String error) {

                    }
                });
                break;
        }
    }

    @OnTextChanged({R.id.content})
    protected void onTextChanged(CharSequence str) {
        if (TextUtils.isEmpty(str)) {
            text.setText("0/1000");
        } else {

            if (str.length() >= 1000) {
                text.setText(str.length() + "/1000");
            } else {
                text.setText(str.length() + "/1000");
            }
        }
    }

    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);
        if (obj instanceof BaseModel) {

            BaseModel data = (BaseModel) obj;

            if (data.getC() == 1) {
                Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, data.getM() + "", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
