package com.example.a.myapplication.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */
public class ScreenColorModel extends BaseModel implements Serializable {

    /**
     * o : [{"id":"12","name":"深蓝","img":"/Public/Uploads/20161228/5863559751714.jpg"},{"id":"9","name":"墨蓝","img":"/Public/Uploads/20161228/5863566300e04.jpg"},{"id":"11","name":"翡翠绿","img":"/Public/Uploads/20161228/5863553b56c2b.jpg"},{"id":"5","name":"红色","img":"/Public/Uploads/20161228/58635458c60dd.jpg"},{"id":"8","name":"咖啡色","img":"/Public/Uploads/20161228/58635529b5979.jpg"},{"id":"6","name":"绿色","img":"/Public/Uploads/20161228/586354f99615b.jpg"},{"id":"4","name":"黄色","img":"/Public/Uploads/20161228/5863566f40298.jpg"},{"id":"3","name":"橙色","img":"/Public/Uploads/20161228/586354df03e4c.jpg"},{"id":"2","name":"粉色","img":"/Public/Uploads/20161228/58635449b4b20.jpg"},{"id":"7","name":"白色","img":"/Public/Uploads/20161228/586355c09a850.jpg"}]
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

    public static class OBean implements Serializable {
        /**
         * id : 12
         * name : 深蓝
         * img : /Public/Uploads/20161228/5863559751714.jpg
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

        public void setImg(String img) {
            this.img = img;
        }
    }
}

