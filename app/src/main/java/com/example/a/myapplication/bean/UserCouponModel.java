package com.example.a.myapplication.bean;

/**
 * Created by Administrator on 2016/12/26.
 */
public class UserCouponModel extends BaseModel {

    /**
     * o : {"id":"1","stus":"2","price":"100.00"}
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
         * id : 1
         * stus : 2
         * price : 100.00
         */

        private String id;
        private String stus;
        private String price;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStus() {
            return stus;
        }

        public void setStus(String stus) {
            this.stus = stus;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
