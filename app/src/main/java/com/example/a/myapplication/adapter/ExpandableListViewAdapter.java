package com.example.a.myapplication.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a.myapplication.R;
import com.example.a.myapplication.activity.OrederDetailsActivity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/26.
 */
public class ExpandableListViewAdapter extends BaseExpandableListAdapter implements TextWatcher {
    ArrayList<ArrayList<String>> list;

    public static String price = 0.00 + "";

    OrederDetailsActivity orederDetailsActivity;

    public ExpandableListViewAdapter(OrederDetailsActivity orederDetailsActivity, ArrayList<ArrayList<String>> list) {
        this.list = list;
        this.orederDetailsActivity = orederDetailsActivity;
    }


    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewGroupHolder holder;
        if (convertView == null) {
            holder = new ViewGroupHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_group, null);
            holder.arrow = (ImageView) convertView.findViewById(R.id.arrow);
            holder.favorable = (TextView) convertView.findViewById(R.id.favorable);
            convertView.setTag(holder);
        } else {

            holder = (ViewGroupHolder) convertView.getTag();
        }


        holder.favorable.setText("(优惠总数：" + price + ")");


        if (orederDetailsActivity.isShow) {
            holder.arrow.setImageResource(R.drawable.icon_down_arrow);
        } else {
            holder.arrow.setImageResource(R.drawable.icon_up_arrow);

        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ViewChildHolder holder;
        if (convertView == null) {

            holder = new ViewChildHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_child, null);
            holder.discount = (EditText) convertView.findViewById(R.id.discount);
            convertView.setTag(holder);

        } else {

            holder = (ViewChildHolder) convertView.getTag();
        }
        holder.discount.addTextChangedListener(this);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {


    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        addTextChange.addTextChangedListener(s);

    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    class ViewGroupHolder {
        ImageView arrow;
        TextView favorable;
    }

    class ViewChildHolder {
        EditText discount;
    }


    public interface AddTextChange {
        void addTextChangedListener(CharSequence s);
    }

    public AddTextChange addTextChange;

    public void addTextChange(AddTextChange addTextChange) {
        this.addTextChange = addTextChange;
    }


}
