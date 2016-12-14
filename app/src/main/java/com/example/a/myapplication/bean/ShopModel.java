package com.example.a.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */
public class ShopModel extends BaseModel {

    private List<Shop> list;

    public List<Shop> getList() {
        return list;
    }

    public void setList(List<Shop> list) {
        this.list = list;
    }


    public static class Shop {
        private List<Content> list ;

        public List<Content> getList() {
            return list;
        }

        public void setList(List<Content> list) {
            this.list = list;
        }

        public static class Content {

        }
    }
}
