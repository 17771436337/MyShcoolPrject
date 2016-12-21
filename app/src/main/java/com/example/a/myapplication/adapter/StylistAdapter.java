package com.example.a.myapplication.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.a.myapplication.BaseApplication;
import com.example.a.myapplication.R;
import com.example.a.myapplication.activity.ProductDetailsActivity;
import com.example.a.myapplication.bean.StyListModel;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a on 2016/12/20.
 */

public class StylistAdapter extends RecyclerView.Adapter<StylistAdapter.PeoView>{
    private List<StyListModel.OBean> products=new ArrayList<>();
    public StylistAdapter() {
    }

    @Override
    public PeoView onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_stylist_itme, viewGroup,false);
        return new PeoView(view,i);
    }

    @Override
    public void onBindViewHolder(PeoView masonryView, int position) {
        // 随机高度, 模拟瀑布效果.
        ImageLoader.getInstance().displayImage(Config.hostString+products.get(position).getImg(),  masonryView.fragment_stylist_itme_img);
  /*      Glide.with(BaseApplication.mCurrentActivity)
                .load(Config.hostString+products.get(position).getImg())
                .asBitmap()
                .fitCenter()
                .placeholder(R.drawable.pic_gray_bg)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(  masonryView.fragment_stylist_itme_img);*/
    }
    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class PeoView extends  RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView fragment_stylist_itme_img;
        FrameLayout fragment_stylist_itme_fl;
        public int postion;

        public PeoView(View itemView,int postion){
            super(itemView);
            this.postion=postion;
            fragment_stylist_itme_img= (ImageView) itemView.findViewById(R.id.fragment_stylist_itme_img );
            fragment_stylist_itme_fl= (FrameLayout) itemView.findViewById(R.id.fragment_stylist_itme_fl );
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Bundle bundle=new Bundle();
            bundle.putString("id",String.valueOf(postion));
            CommonUtils.startIntent(BaseApplication.mCurrentActivity, ProductDetailsActivity.class,bundle);
        }
    }
    public  List<StyListModel.OBean> getDateList(){
        return products;
    }
}
