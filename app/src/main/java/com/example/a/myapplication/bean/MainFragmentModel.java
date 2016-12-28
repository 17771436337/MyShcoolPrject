package com.example.a.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/27.
 */
public class MainFragmentModel extends BaseModel {
    /**
     * o : [{"id":"3","name":"腿长的怎么搭配都可以？就看你怎么搭配","img":"/Public/Uploads/images/t6.png"},{"id":"2","name":"穿上9个潮牌，权志龙都让你多看两眼","img":"/Public/Uploads/images/t2.png"},{"id":"1","name":"波密西亚裙","img":"/Public/Uploads/images/t4.png"}]
     * e : null
     */

    private Object e;
    private List<OBean> o;

    public Object getE() {
        return e;
    }

    public void setE(Object e) {
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
         * id : 3
         * name : 腿长的怎么搭配都可以？就看你怎么搭配
         * img : /Public/Uploads/images/t6.png
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
