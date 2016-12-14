package com.example.a.myapplication.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/12.
 */
public class ScreenFashionModel extends BaseModel{

    public ArrayList<Fashion> getList() {
        return list;
    }

    public void setList(ArrayList<Fashion> list) {
        this.list = list;
    }

    private ArrayList<Fashion> list;

    public static class Fashion{

    }
}
