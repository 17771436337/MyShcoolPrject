package com.example.a.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */
public class MyOrderModer extends BaseModel {
    private List<Order> data;

    public List<Order> getData() {
        return data;
    }

    public void setData(List<Order> data) {
        this.data = data;
    }


    public static class Order {

    }
}
