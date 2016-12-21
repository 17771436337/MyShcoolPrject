package com.example.a.myapplication.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */
public class LocationMoldel extends BaseModel {

    /**
     * o : [{"id":"35","uid":"2","name":"小学长","phone":"15071312445","code":"420000","province":"湖北省","city":"武汉市","area":"江夏区","address":"大学园路化徐村","time":"1482298744","state":"1"}]
     * e :
     */

    private String e;
    private List<Location> o;


    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public List<Location> getO() {
        return o;
    }

    public void setO(List<Location> o) {
        this.o = o;
    }


    public static class Location implements Serializable{
        /**
         * id : 35
         * uid : 2
         * name : 小学长
         * phone : 15071312445
         * code : 420000
         * province : 湖北省
         * city : 武汉市
         * area : 江夏区
         * address : 大学园路化徐村
         * time : 1482298744
         * state : 1
         */

        private String id;
        private String uid;
        private String name;
        private String phone;
        private String code;
        private String province;
        private String city;
        private String area;
        private String address;
        private String time;
        private String state;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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
