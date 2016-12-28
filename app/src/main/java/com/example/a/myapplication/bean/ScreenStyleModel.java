package com.example.a.myapplication.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */
public class ScreenStyleModel extends BaseModel implements Serializable {


    /**
     * o : [{"id":"24","name":"墨蓝","logo":"/Public/Uploads/20161228/586360898af9d.jpg"},{"id":"20","name":"安踏","logo":"/Public/Uploads/20161228/586360ce4c30c.jpg"},{"id":"23","name":"KONIKA","logo":"/Public/Uploads/20161228/58636098c0d45.jpg"},{"id":"22","name":"新百伦","logo":"/Public/Uploads/20161228/586360a86f868.jpg"},{"id":"21","name":"特步","logo":"/Public/Uploads/20161228/586360bea28c4.jpg"},{"id":"29","name":"OCHIRLY","logo":"/Public/Uploads/20161228/5863603ca40fc.jpg"},{"id":"28","name":"红蜻蜓","logo":"/Public/Uploads/20161228/58636047e01a7.jpg"},{"id":"27","name":"达芙妮","logo":"/Public/Uploads/20161228/5863605495c45.jpg"},{"id":"26","name":"美特斯·邦威","logo":"/Public/Uploads/20161228/586360685adaa.jpg"},{"id":"39","name":"盖尔斯（GUESS）","logo":"/Public/Uploads/20161228/58635f9b3becb.jpg"}]
     * e : null
     */

    private Object e;
    private List<Brand> o;

    public Object getE() {
        return e;
    }

    public void setE(Object e) {
        this.e = e;
    }

    public List<Brand> getO() {
        return o;
    }

    public void setO(List<Brand> o) {
        this.o = o;
    }

    public static class Brand implements Serializable {
        /**
         * id : 24
         * name : 墨蓝
         * logo : /Public/Uploads/20161228/586360898af9d.jpg
         */

        private String id;
        private String name;
        private String img;
        private boolean is;

        public boolean is() {
            return is;
        }

        public void setIs(boolean is) {
            this.is = is;
        }

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

        public void setImg(String logo) {
            this.img = logo;
        }
    }
}
