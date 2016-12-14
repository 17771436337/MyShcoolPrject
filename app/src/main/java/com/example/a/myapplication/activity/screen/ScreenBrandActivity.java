package com.example.a.myapplication.activity.screen;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.ScreenBrandAdapter;
import com.example.a.myapplication.bean.ScreenBrandModel;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * Created by Administrator on 2016/12/9.
 * 品牌
 */
public class ScreenBrandActivity extends BaseActivity {

    @InjectView(R.id.srarch)
    protected EditText srarch;

    @InjectView(R.id.delete)
    protected ImageView delete;

    @InjectView(R.id.pull_layout)
    protected PullToRefreshListView pullListView;

    ScreenBrandModel model = new ScreenBrandModel();

    ScreenBrandAdapter adapter;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_screenbrand;
    }

    @Override
    protected void initView() {

        getData();
        adapter = new ScreenBrandAdapter(pullListView ,model.getList());
        pullListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullListView.getRefreshableView().setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }


    private  void getData(){
        ArrayList<ScreenBrandModel.Brand> list = new ArrayList<ScreenBrandModel.Brand>();
        for (int i = 0 ;i < 10 ; i ++){
            ScreenBrandModel.Brand brand = new ScreenBrandModel.Brand();
            list.add(brand);
        }
        model.setList(list);
    }


    @OnClick({R.id.delete})
    public void onClick(View v){
        switch (v.getId()){
            case  R.id.delete:  //删除
                srarch.setText("");
                break;
        }
    }

    /**输入框监听*/
    @OnTextChanged(R.id.srarch)
    public void onTextChanged(CharSequence text){
        if (TextUtils.isEmpty(text)) {
            delete.setVisibility(View.GONE);
        } else {
            delete.setVisibility(View.VISIBLE);
        }
    }



}
