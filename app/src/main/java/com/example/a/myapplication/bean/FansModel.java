package com.example.a.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */
public class FansModel extends BaseModel {
    private List<MyFans> fans;
    /**
     * o : [{"id":"1","nid":"3","fid":"2","time":"1481251087","state":"1"}]
     * e :
     */

    private String e;
    private List<MyFans> o;


    public String getE() {
        return e;
    }

    public void setE(String e) {
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
         */

        private String id;
        private String nid;
        private String fid;
        private String time;
        private String state;

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
    }
}
