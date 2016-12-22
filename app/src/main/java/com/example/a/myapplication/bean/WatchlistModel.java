package com.example.a.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/22.
 */
public class WatchlistModel extends BaseModel {


    /**
     * o : [{"id":"2","nid":"2","fid":"1","time":"0","state":"1","fhead":null,"fname":null},{"id":"3","nid":"2","fid":"3","time":"0","state":"1","fhead":"","fname":"咿呀"}]
     * e : null
     */

    private Object e;

    private List<OBean> o;

    public List<OBean> getO() {
        return o;
    }

    public void setO(List<OBean> o) {
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
         * id : 2
         * nid : 2
         * fid : 1
         * time : 0
         * state : 1
         * fhead : null
         * fname : null
         */

        private String id;
        private String nid;
        private String fid;
        private String time;
        private String state;
        private String fhead;
        private String fname;

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
    }
}
