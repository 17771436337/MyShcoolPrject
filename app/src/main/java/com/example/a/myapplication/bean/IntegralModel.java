package com.example.a.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */
public class IntegralModel extends BaseModel {


    /**
     * o : [{"type":"1","describe":"邀请好友送积分","integral":"300","time":"2016-12-09 10:38"}]
     * e :
     */

    private String e;
    private List<OBean> o;

    public String getE() {
        return e;
    }

    public void setE(String e) {
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
         * type :  1邀请好友2购物3分享商品4分享APP 5好物置换
         * describe : 邀请好友送积分
         * integral : 300
         * time : 2016-12-09 10:38
         */

        private String type;
        private String describe;
        private String integral;
        private String time;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
