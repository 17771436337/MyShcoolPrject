package com.example.a.myapplication.bean;

import java.util.List;

/**
 * Created by a on 2016/12/27.
 */

public class CartShopInfoModel  extends BaseModel{

    /**
     * o : {"img":"/Public/Uploads/20161230/thumb_5866393ca7b5b.jpg","name":"墨蓝裙","price":"700.00","color":"水蓝","size":["M","L","XL"]}
     * e : null
     */

    private OBean o;
    private Object e;

    public OBean getO() {
        return o;
    }

    public void setO(OBean o) {
        this.o = o;
    }

    public Object getE() {
        return e;
    }

    public void setE(Object e) {
        this.e = e;
    }

    public static class OBean {
        /**
         * img : /Public/Uploads/20161230/thumb_5866393ca7b5b.jpg
         * name : 墨蓝裙
         * price : 700.00
         * color : 水蓝
         * size : ["M","L","XL"]
         */

        private String img;
        private String name;
        private String price;
        private String color;
        private List<String> size;

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public List<String> getSize() {
            return size;
        }

        public void setSize(List<String> size) {
            this.size = size;
        }
    }
}
