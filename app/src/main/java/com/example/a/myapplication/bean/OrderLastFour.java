package com.example.a.myapplication.bean;

/**
 * Created by Administrator on 2016/12/26.
 */
public class OrderLastFour extends BaseModel {

    /**
     * o : {"order":"5510"}
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
         * order : 5510
         */

        private String order;

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }
    }
}
