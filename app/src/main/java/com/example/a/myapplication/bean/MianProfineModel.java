package com.example.a.myapplication.bean;

/**
 * Created by Administrator on 2016/12/20.
 */
public class MianProfineModel extends BaseModel{


    /**
     * o : {"id":"4","name":"","head":"","fans":"0","focus":"0"}
     * e :
     */

    private OBean o;
    private String e;

    public OBean getO() {
        return o;
    }

    public void setO(OBean o) {
        this.o = o;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public static class OBean {
        /**
         * id : 4
         * name :
         * head :
         * fans : 0
         * focus : 0
         */

        private String id;
        private String name;
        private String head;
        private String fans;
        private String focus;

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

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getFans() {
            return fans;
        }

        public void setFans(String fans) {
            this.fans = fans;
        }

        public String getFocus() {
            return focus;
        }

        public void setFocus(String focus) {
            this.focus = focus;
        }
    }
}
