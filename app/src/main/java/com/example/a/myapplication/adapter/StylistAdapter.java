package com.example.a.myapplication.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a.myapplication.BaseApplication;
import com.example.a.myapplication.R;
import com.example.a.myapplication.activity.ProductDetailsActivity;
import com.example.a.myapplication.bean.StyListModel;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.view.RoundImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a on 2016/12/20.
 */

public class StylistAdapter extends RecyclerView.Adapter<StylistAdapter.PeoView>{
    private List<StyListModel.OBean> products=new ArrayList<>();
    public String mUrl;
    public StylistAdapter(String  url) {
        this.mUrl=url;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public PeoView onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_stylist_itme, viewGroup,false);
        return new PeoView(view,i);
    }

    @Override
    public void onBindViewHolder(PeoView masonryView,final int position) {
        String url="";
        if(0==position&&mUrl.equals(Config.QITTMELIST)){
            url=products.get(position).getImg();
        }else{
            url=Config.hostImgString+products.get(position).getImg();
        }
        url=Config.hostImgString+products.get(position).getImg();
        ImageLoader.getInstance().displayImage(url,  masonryView.fragment_stylist_itme_img);
  /*      Glide.with(BaseApplication.mCurrentActivity)
                .load(Config.hostString+products.get(position).getImg())
                .asBitmap()
                .fitCenter()
                .placeholder(R.drawable.pic_gray_bg)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(  masonryView.fragment_stylist_itme_img);*/
        ImageLoader.getInstance().displayImage(url,  masonryView.fragment_stylist_itme_head);
        masonryView.fragment_stylist_itme_head_name.setText(products.get(position).getName());
        masonryView.fragment_stylist_itme_head_num.setText(products.get(position).getCollection());
        masonryView.fragment_stylist_itme_fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putString("id",null!=products.get(position).getId()?products.get(position).getId():products.get(position).getItemid());
                bundle.putString("is_collection",products.get(position).getCollection());
                CommonUtils.startIntent(BaseApplication.mCurrentActivity, ProductDetailsActivity.class,bundle);
            }
        });
    }
    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class PeoView extends  RecyclerView.ViewHolder{
        ImageView fragment_stylist_itme_img;
        FrameLayout fragment_stylist_itme_fl;
        RoundImageView fragment_stylist_itme_head;
        TextView fragment_stylist_itme_head_name,fragment_stylist_itme_head_num;
        public int postion;

        public PeoView(View itemView,int postion){
            super(itemView);
            this.postion=postion;
            fragment_stylist_itme_img= (ImageView) itemView.findViewById(R.id.fragment_stylist_itme_img );
            fragment_stylist_itme_fl= (FrameLayout) itemView.findViewById(R.id.fragment_stylist_itme_fl );
            fragment_stylist_itme_head= (RoundImageView) itemView.findViewById(R.id.fragment_stylist_itme_head );
            fragment_stylist_itme_head_name= (TextView) itemView.findViewById(R.id.fragment_stylist_itme_head_name );
            fragment_stylist_itme_head_num= (TextView) itemView.findViewById(R.id.fragment_stylist_itme_head_num );
        }
    }
    public  List<StyListModel.OBean> getDateList(){
        return products;
    }


}
