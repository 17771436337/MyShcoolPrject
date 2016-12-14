package com.example.a.myapplication.activity.screen;

import android.widget.ListView;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.ScreenCategoryOneAdapter;
import com.example.a.myapplication.bean.ScreenCategoryOneModel;
import com.example.a.myapplication.util.CommonUtils;

import java.util.ArrayList;

import butterknife.InjectView;
import butterknife.OnItemClick;

/**
 * Created by Administrator on 2016/12/12.
 * 品类的一级筛选
 */
public class ScreenCategoryActivity extends BaseActivity{

    @InjectView(R.id.list_view)
    protected ListView listView;

    ScreenCategoryOneModel model = new ScreenCategoryOneModel();

    ScreenCategoryOneAdapter adapter;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_category;
    }

    @Override
    protected void initView() {
        getData();
        adapter = new ScreenCategoryOneAdapter(model.getList());
        listView.setAdapter(adapter);



    }

    @Override
    protected void initData() {

    }


    private  void getData(){

        ArrayList<ScreenCategoryOneModel.Category> list = new ArrayList<ScreenCategoryOneModel.Category>();
        for (int i = 0; i<10;i++){
            ScreenCategoryOneModel.Category category = new  ScreenCategoryOneModel.Category();
            list.add(category);
        }
        model.setList(list);
    }



    @OnItemClick(R.id.list_view)
    public void onItemClick(int position){

        CommonUtils.startIntent(this,ScreenCategoryTowActivity.class, ScreenActivity.CATEGORY);

    }
}
