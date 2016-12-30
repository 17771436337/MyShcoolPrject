package com.example.a.myapplication.bean;

import java.util.List;

/**
 * Created by a on 2016/12/27.
 */

public class ShopDetailsModel extends BaseModel{

    /**
     * o : {"name":"精品女装","price":"500.00","brand":"#优衣库","category":"#西裤s","color":"#绿色","popular":"#拼接","idol":"#Wendy Nguyen","imgs":[{"img":"/Public/Uploads/20161217/5854ddf3220c2.png"},{"img":"/Public/Uploads/20161217/5854ea39651ea.png"}]}
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
         * name : 精品女装
         * price : 500.00
         * brand : #优衣库
         * category : #西裤s
         * color : #绿色
         * popular : #拼接
         * idol : #Wendy Nguyen
         * imgs : [{"img":"/Public/Uploads/20161217/5854ddf3220c2.png"},{"img":"/Public/Uploads/20161217/5854ea39651ea.png"}]
         */

        private String name;
        private String price;
        private String brand;
        private String brands;
        private String category;
        private String categorys;
        private String color;
        private String colors;
        private String popular;
        private String populars;
        private String idol;
        private String idols;
        private List<ImgsBean> imgs;

        public String getBrands() {
            return brands;
        }

        public void setBrands(String brands) {
            this.brands = brands;
        }

        public String getCategorys() {
            return categorys;
        }

        public void setCategorys(String categorys) {
            this.categorys = categorys;
        }

        public String getColors() {
            return colors;
        }

        public void setColors(String colors) {
            this.colors = colors;
        }

        public String getPopulars() {
            return populars;
        }

        public void setPopulars(String populars) {
            this.populars = populars;
        }

        public String getIdols() {
            return idols;
        }

        public void setIdols(String idols) {
            this.idols = idols;
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

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getPopular() {
            return popular;
        }

        public void setPopular(String popular) {
            this.popular = popular;
        }

        public String getIdol() {
            return idol;
        }

        public void setIdol(String idol) {
            this.idol = idol;
        }

        public List<ImgsBean> getImgs() {
            return imgs;
        }

        public void setImgs(List<ImgsBean> imgs) {
            this.imgs = imgs;
        }

        public static class ImgsBean {
            /**
             * img : /Public/Uploads/20161217/5854ddf3220c2.png
             */

            private String img;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
