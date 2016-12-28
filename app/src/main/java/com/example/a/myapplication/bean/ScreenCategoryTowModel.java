package com.example.a.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */
public class ScreenCategoryTowModel extends BaseModel{


    /**
     * o : [{"id":"17","name":"连衣裙","img":"/Public/Uploads/20161226/586066fe31835.jpg"}]
     * e : null
     */

    private Object e;
    private List<Category> o;

    public Object getE() {
        return e;
    }

    public void setE(Object e) {
        this.e = e;
    }

    public List<Category> getO() {
        return o;
    }

    public void setO(List<Category> o) {
        this.o = o;
    }

    public static class Category {
        /**
         * id : 17
         * name : 连衣裙
         * img : /Public/Uploads/20161226/586066fe31835.jpg
         */

        private String id;
        private String name;
        private String img;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
