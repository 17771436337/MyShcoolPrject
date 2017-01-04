package com.example.a.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */
public class MyOrderModer extends BaseModel {
    /**
     * o : [{"brand":"Jack Johns","logo":"/wardrobe/code/wardrode/wardrobe/code/wardrode/Public/Uploads/20161216/585389752e992.jpg","orderid":"1","order":"2016121310371810197555","img":"/wardrobe/code/wardrode","name":"韩版羽绒夹克","price":"500.00","sum":"1","time":"1481120000","totalprice":"500.00","audit":"0"}]
     * e :
     */

    private String e;
    private List<Order> o;


    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public List<Order> getO() {
        return o;
    }

    public void setO(List<Order> o) {
        this.o = o;
    }


    public static class Order {
        /**
         * brand : Jack Johns
         * logo : /wardrobe/code/wardrode/wardrobe/code/wardrode/Public/Uploads/20161216/585389752e992.jpg
         * orderid : 1
         * order : 2016121310371810197555
         * img : /wardrobe/code/wardrode
         * name : 韩版羽绒夹克
         * price : 500.00
         * sum : 1
         * time : 1481120000
         * totalprice : 500.00
         * audit : 0
         */

        private String id;


        private String brand;
        private String logo;
        private String orderid;
        private String order;
        private String img;
        private String name;
        private String price;
        private String sum;
        private String time;
        private String totalprice;
        private String audit;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSum() {
            return sum;
        }

        public void setSum(String sum) {
            this.sum = sum;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTotalprice() {
            return totalprice;
        }

        public void setTotalprice(String totalprice) {
            this.totalprice = totalprice;
        }

        public String getAudit() {
            return audit;
        }

        public void setAudit(String audit) {
            this.audit = audit;
        }
    }
}
