package com.example.a.myapplication.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.a.myapplication.holder.BaseHolder;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.List;

public abstract class SuperBaseAdapter<T> extends BaseAdapter{ //implements AdapterView.OnItemClickListener {
    private final static int TYPE_LOAD_MORE = 0;
    private final static int TYPE_NORMAL_ITEM = 1;
    private List<T> mDatas;
    private PullToRefreshBase mListView;

    public SuperBaseAdapter(PullToRefreshBase listView, List<T> datas) {
        this.mDatas = datas;
        this.mListView = listView;
       // mListView.setOnRereshListListenr(this);
    }

    public SuperBaseAdapter( List<T> datas) {
        this.mDatas = datas;

    }

    @Override
    public int getCount() {
        if (mDatas != null) {
            return mDatas.size();
        }// 添加加载更多的item
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mDatas != null) {
            return mDatas.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder holder = null;
        if (convertView == null) {
            holder = getItemHolder(position);
            convertView = holder.getRootView();
        } else {// 有复用
            holder = (BaseHolder) convertView.getTag();
        }
        T data = mDatas.get(position);
        // 给holder中的view设置数据
        holder.setData(data);
        return convertView;
    }

    protected abstract BaseHolder getItemHolder(int position);
  /*  @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }*/
}
