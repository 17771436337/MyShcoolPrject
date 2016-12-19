package com.example.a.myapplication.adapter;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.myapplication.R;
import com.example.a.myapplication.holder.BaseHolder;
import com.example.a.myapplication.holder.ShopDialogHolder;
import com.example.a.myapplication.util.UIUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */
public class ShopDialogAdapter extends SuperBaseAdapter<String> implements AdapterView.OnItemClickListener {

    List data;

    public ShopDialogAdapter(List<String> datas) {
        super(datas);
        data = datas;
    }

    @Override
    protected BaseHolder<String> getItemHolder(int position) {
        return new ShopDialogHolder();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(UIUtils.getContext(),"position:"+position+"   id:"+id,1000).show();
        for (int i = 0 ; i < data.size();i++){
            if (position == i){
                Toast.makeText(UIUtils.getContext(),"position:"+position+"   id:"+id,1000).show();
                view.setBackgroundResource(R.drawable.collect_radiobutton_background_shape_yes);
                ( (TextView) view).setTextColor(UIUtils.getContext().getResources().getColor(R.color.white));
            }      else{
                view.setBackgroundResource(R.drawable.collect_radiobutton_background_shape_no);
                ( (TextView) view).setTextColor(UIUtils.getContext().getResources().getColor(R.color.black_text));
            }
        }
    }
}
