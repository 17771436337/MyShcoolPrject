package com.example.a.myapplication.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */
public class ScreenCategoryOneModel extends BaseModel {
    private ArrayList<Category> list;
    /**
     * o : [{"id":"20","name":"下裤","img":"/wardrobe/code/wardrode/Public/Uploads/20161224/585e1caa530a7.jpg"},{"id":"19","name":"T-shirt","img":"/wardrobe/code/wardrode/Public/Uploads/20161226/58608aaa48336.jpg"},{"id":"13","name":"墨镜","img":"/wardrobe/code/wardrode/Public/Uploads/20161224/585e212a3963b.jpg"},{"id":"14","name":"下裙","img":"/wardrobe/code/wardrode/Public/Uploads/20161224/585e246e11bea.jpg"},{"id":"18","name":"外套","img":"/wardrobe/code/wardrode/Public/Uploads/20161226/586064ecda2be.jpg"},{"id":"16","name":"帽子","img":"/wardrobe/code/wardrode/Public/Uploads/20161224/585e2452cd267.jpg"},{"id":"11","name":"衬衣","img":"/wardrobe/code/wardrode/Public/Uploads/20161224/585e1caa530a7.jpg"},{"id":"15","name":"斗篷","img":"/wardrobe/code/wardrode/Public/Uploads/20161226/586065d1948b7.jpg"},{"id":"17","name":"连衣裙","img":"/wardrobe/code/wardrode/Public/Uploads/20161226/586066fe31835.jpg"}]
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
         * id : 20
         * name : 下裤
         * img : /wardrobe/code/wardrode/Public/Uploads/20161224/585e1caa530a7.jpg
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
