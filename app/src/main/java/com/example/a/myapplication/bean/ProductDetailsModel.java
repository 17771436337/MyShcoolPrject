package com.example.a.myapplication.bean;

import java.io.Serializable;

/**
 * Created by a on 2016/12/22.
 */

public class ProductDetailsModel extends BaseModel implements Serializable{

    /**
     * o : {"img":"/wardrobe/code/wardrode","comment":"0","collection":"1","name":"小学长","head":"/wardrobe/code/wardrode/Public/Uploads/20161207/08d2cda5f369f9f045b66a7b1f41557a.jpg"}
     * e : true
     */

    private OBean o;
    private boolean e;

    public OBean getO() {
        return o;
    }

    public void setO(OBean o) {
        this.o = o;
    }

    public boolean isE() {
        return e;
    }

    public void setE(boolean e) {
        this.e = e;
    }

    public static class OBean {
        /**
         * img : /wardrobe/code/wardrode
         * comment : 0
         * collection : 1
         * name : 小学长
         * head : /wardrobe/code/wardrode/Public/Uploads/20161207/08d2cda5f369f9f045b66a7b1f41557a.jpg
         */

        private String img;
        private String comment;
        private String collection;
        private String name;
        private String head;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getCollection() {
            return collection;
        }

        public void setCollection(String collection) {
            this.collection = collection;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }
    }
}
