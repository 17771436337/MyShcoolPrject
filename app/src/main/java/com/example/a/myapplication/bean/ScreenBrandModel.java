package com.example.a.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/9.
 */
public class ScreenBrandModel extends BaseModel{

    private List<Brand> list;

    public List<Brand> getList() {
        return list;
    }

    public void setList(List<Brand> list) {
        this.list = list;
    }

    public static class Brand{

    }
}
