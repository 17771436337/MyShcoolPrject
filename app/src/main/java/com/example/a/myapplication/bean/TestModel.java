package com.example.a.myapplication.bean;

import java.util.List;

/**
 * Created by a on 2016/12/1.
 */

public class TestModel extends BaseModel {


    private List<Peo> peos;

    public List<Peo> getPeos() {
        return peos;
    }

    public void setPeos(List<Peo> peos) {
        this.peos = peos;
    }

    public static class Peo {
        private String name;
        private String age;
        private String sex;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
