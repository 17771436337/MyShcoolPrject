package com.example.a.myapplication.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by a on 2016/12/23.
 */

public class ProductTitleMessageModel extends BaseModel{

    /**
     * o : [{"rid":"5850a2072d0b1","name":"小学长","head":"/Public/Uploads/20161207/08d2cda5f369f9f045b66a7b1f41557a.jpg","details":[{"brand":"Jack Johns","category":"上衣","id":"1"},{"brand":"zara","category":"下装","id":"2"}]},{"rid":"5850a5ecc3efe","name":"小学长","head":"/Public/Uploads/20161207/08d2cda5f369f9f045b66a7b1f41557a.jpg","details":[{"brand":"Jack Johns","category":"上衣","id":"1"},{"brand":"zara","category":"下装","id":"2"}]}]
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

    public static class OBean  implements Serializable {
        /**
         * rid : 5850a2072d0b1
         * name : 小学长
         * head : /Public/Uploads/20161207/08d2cda5f369f9f045b66a7b1f41557a.jpg
         * details : [{"brand":"Jack Johns","category":"上衣","id":"1"},{"brand":"zara","category":"下装","id":"2"}]
         */

        private String rid;
        private String name;
        private String head;
        private List<DetailsBean> details;

        public String getRid() {
            return rid;
        }

        public void setRid(String rid) {
            this.rid = rid;
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

        public List<DetailsBean> getDetails() {
            return details;
        }

        public void setDetails(List<DetailsBean> details) {
            this.details = details;
        }

        public static class DetailsBean {
            /**
             * brand : Jack Johns
             * category : 上衣
             * id : 1
             */

            private String brand;
            private String category;
            private String id;

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

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}
