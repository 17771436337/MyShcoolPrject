package com.example.a.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */
public class FansModel extends BaseModel {

    /**
     * o : [{"id":"1","nid":"3","fid":"2","time":"1481251087","state":"1","fhead":"/Public/Uploads/20161222/585ba1d0105f3.jpg","fname":"小学长","is_focus":"1"},{"id":"5","nid":"4","fid":"2","time":"1482399375","state":"1","fhead":"/Public/Uploads/20161222/585ba1d0105f3.jpg","fname":"小学长","is_focus":"0"},{"id":"6","nid":"5","fid":"2","time":"1482399379","state":"1","fhead":"/Public/Uploads/20161222/585ba1d0105f3.jpg","fname":"小学长","is_focus":"0"},{"id":"7","nid":"6","fid":"2","time":"1482399384","state":"1","fhead":"/Public/Uploads/20161222/585ba1d0105f3.jpg","fname":"小学长","is_focus":"0"},{"id":"8","nid":"7","fid":"2","time":"1482399387","state":"1","fhead":"/Public/Uploads/20161222/585ba1d0105f3.jpg","fname":"小学长","is_focus":"0"}]
     * e : null
     */

    private Object e;
    private List<MyFans> o;

    public Object getE() {
        return e;
    }

    public void setE(Object e) {
        this.e = e;
    }

    public List<MyFans> getO() {
        return o;
    }

    public void setO(List<MyFans> o) {
        this.o = o;
    }

    public static class MyFans {
        /**
         * id : 1
         * nid : 3
         * fid : 2
         * time : 1481251087
         * state : 1
         * fhead : /Public/Uploads/20161222/585ba1d0105f3.jpg
         * fname : 小学长
         * is_focus : 1
         */

        private String id;
        private String nid;
        private String fid;
        private String time;
        private String state;
        private String fhead;
        private String fname;
        private String is_focus;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNid() {
            return nid;
        }

        public void setNid(String nid) {
            this.nid = nid;
        }

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getFhead() {
            return fhead;
        }

        public void setFhead(String fhead) {
            this.fhead = fhead;
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getIs_focus() {
            return is_focus;
        }

        public void setIs_focus(String is_focus) {
            this.is_focus = is_focus;
        }
    }
}
