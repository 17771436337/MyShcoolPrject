package com.example.a.myapplication.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/14.
 */
public class LocationMoldel extends BaseModel{
    private ArrayList<Location> list;

    public ArrayList<Location> getList() {
        return list;
    }

    public void setList(ArrayList<Location> list) {
        this.list = list;
    }

    public static class Location{

    }
}
