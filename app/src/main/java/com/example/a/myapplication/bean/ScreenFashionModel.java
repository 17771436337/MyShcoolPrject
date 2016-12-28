package com.example.a.myapplication.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */
public class ScreenFashionModel extends BaseModel implements Serializable {


    /**
     * o : [{"id":"15","name":"原宿风"},{"id":"13","name":"日系风"},{"id":"8","name":"学院风"},{"id":"18","name":"通勤风"},{"id":"17","name":" 朋克风"},{"id":"10","name":"少女风"},{"id":"11","name":"古典风"},{"id":"9","name":"OL风"},{"id":"4","name":"洛丽塔风"},{"id":"16","name":"街头风"}]
     * e : null
     */

    private Object e;
    private List<Fashion> o;

    public Object getE() {
        return e;
    }

    public void setE(Object e) {
        this.e = e;
    }

    public List<Fashion> getO() {
        return o;
    }

    public void setO(List<Fashion> o) {
        this.o = o;
    }

    public static class Fashion implements Serializable {
        /**
         * id : 15
         * name : 原宿风
         */

        private String id;
        private String name;
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
    }
}
