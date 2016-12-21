package com.example.a.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */
public class MyCollecModer extends BaseModel {


    /**
     * o : [{"id":"1","img":"/Public/Uploads/20161217/5854db3aa9577.png","collection":"1","name":"小学长","head":"/Public/Uploads/20161207/08d2cda5f369f9f045b66a7b1f41557a.jpg"}]
     * e :
     */

    private String e;
    private List<Colledt> o;


    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public List<Colledt> getO() {
        return o;
    }

    public void setO(List<Colledt> o) {
        this.o = o;
    }


    public static class Colledt {
        /**
         * id : 1
         * img : /Public/Uploads/20161217/5854db3aa9577.png
         * collection : 1
         * name : 小学长
         * head : /Public/Uploads/20161207/08d2cda5f369f9f045b66a7b1f41557a.jpg
         */

        private String id;
        private String img;
        private String collection;
        private String name;
        private String head;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
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
