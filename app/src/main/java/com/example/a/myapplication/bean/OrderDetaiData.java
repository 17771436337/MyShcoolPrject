package com.example.a.myapplication.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/26.
 */
public class OrderDetaiData {

    private ArrayList<OrderDetai> list;
    private int icon;//图标显示

    public OrderDetaiData(int icon) {
        this.list = new ArrayList<>();
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public ArrayList<OrderDetai> getList() {
        return list;
    }

    public void setList(ArrayList<OrderDetai> list) {
        this.list = list;
    }

    public static class OrderDetai {
        private ArrayList<String> str;

        public ArrayList<String> getStr() {
            return str;
        }

        public void setStr(ArrayList<String> str) {
            this.str = str;
        }
    }

}
