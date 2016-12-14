package com.example.a.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */
public class FansModel extends BaseModel {
    private List<MyFans> fans;

    public List<MyFans> getFans() {
        return fans;
    }

    public void setFans(List<MyFans> fans) {
        this.fans = fans;
    }

    public static class MyFans {
        private String img;
        private String name;
        private int type;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
