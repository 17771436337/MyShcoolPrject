package com.example.a.myapplication.bean;

import java.util.List;

/**
 * Created by a on 2016/12/20.
 */

public class StyListModel extends BaseModel {

    /**
     * o : [{"id":"1","img":"/wardrobe/code/wardrode","collection":"1","head":"/wardrobe/code/wardrode/Public/Uploads/20161207/08d2cda5f369f9f045b66a7b1f41557a.jpg","name":"小学长"}]
     * e : true
     */

    private boolean e;
    private List<OBean> o;

    public boolean isE() {
        return e;
    }

    public void setE(boolean e) {
        this.e = e;
    }

    public List<OBean> getO() {
        return o;
    }

    public void setO(List<OBean> o) {
        this.o = o;
    }

    public static class OBean {
        /**
         * id : 1
         * img : /wardrobe/code/wardrode
         * collection : 1
         * head : /wardrobe/code/wardrode/Public/Uploads/20161207/08d2cda5f369f9f045b66a7b1f41557a.jpg
         * name : 小学长
         */

        private String id;
        private String itemid;
        private String img;
        private String collection;
        private String head;
        private String name;
        private String is_collection;

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

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getItemid() {
            return itemid;
        }

        public void setItemid(String itemid) {
            this.itemid = itemid;
        }

        public String getIs_collection() {
            return is_collection;
        }

        public void setIs_collection(String is_collection) {
            this.is_collection = is_collection;
        }
    }
}
