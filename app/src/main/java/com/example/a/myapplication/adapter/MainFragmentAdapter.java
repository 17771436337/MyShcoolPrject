package com.example.a.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.MainFragmentModel;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.ScreenUtils;
import com.example.a.myapplication.util.UIUtils;

import java.util.List;

import static android.support.v7.widget.RecyclerView.ViewHolder;

/**
 * Created by Administrator on 2016/12/20.
 */
public class MainFragmentAdapter extends RecyclerView.Adapter<MainFragmentAdapter.ItemViewHolder> {

    RelativeLayout.LayoutParams layoutParams;
    private LayoutInflater mInflater;
    private List<MainFragmentModel.OBean> mDatas;
    private OnItemClickListener mOnItemClickListener;

    public MainFragmentAdapter(Context context, List<MainFragmentModel.OBean> mDatas) {
        this.mDatas = mDatas;
        layoutParams = new RelativeLayout.LayoutParams(ScreenUtils.getScreenW(), ScreenUtils.getScreenH() / 3 * 2);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder itemViewHolder, final int i) {

        itemViewHolder.r1.setLayoutParams(layoutParams);
        itemViewHolder.mTextView.setText(mDatas.get(i).getName());
        Glide.with(UIUtils.getContext()).load(Config.hostImgString + mDatas.get(i).getImg())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .crossFade().into(itemViewHolder.img);

        if (mOnItemClickListener != null) {
            /**
             * 这里加了判断，itemViewHolder.itemView.hasOnClickListeners()
             * 目的是减少对象的创建，如果已经为view设置了click监听事件,就不用重复设置了
             * 不然每次调用onBindViewHolder方法，都会创建两个监听事件对象，增加了内存的开销
             */
            if (!itemViewHolder.itemView.hasOnClickListeners()) {
                Log.e("ListAdapter", "setOnClickListener");
                itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = itemViewHolder.getPosition();
                        mOnItemClickListener.onItemClick(v, pos);
                    }
                });
                itemViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = itemViewHolder.getPosition();
                        mOnItemClickListener.onItemLongClick(v, pos);
                        return true;
                    }
                });
            }
        }

    }

    @Override
    public int getItemCount() {
        return mDatas.size();

    }

    /**
     * 向指定位置添加元素
     *
     * @param position
     * @param value
     */
    public void add(int position, MainFragmentModel.OBean value) {
        if (position > mDatas.size()) {
            position = mDatas.size();
        }
        if (position < 0) {
            position = 0;
        }
        mDatas.add(position, value);
        /**
         * 使用notifyItemInserted/notifyItemRemoved会有动画效果
         * 而使用notifyDataSetChanged()则没有
         */
        notifyItemInserted(position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    /**
     * 处理item的点击事件和长按事件
     */
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);

        public void onItemLongClick(View view, int position);
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        /**
         * 使用RecyclerView，ViewHolder是可以复用的。这根使用ListView的VIewHolder复用是一样的
         * ViewHolder创建的个数好像是可见item的个数+3
         */
        Log.e("ListAdapter", "onCreateViewHolder");
        ItemViewHolder holder = new ItemViewHolder(mInflater.inflate(
                R.layout.item_main_list, viewGroup, false));

        return holder;
    }


    class ItemViewHolder extends ViewHolder {

        private RelativeLayout r1;
        private TextView mTextView;
        private ImageView img;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.text);
            img = (ImageView) itemView.findViewById(R.id.img);
            r1 = (RelativeLayout) itemView.findViewById(R.id.r1);
        }
    }
}
