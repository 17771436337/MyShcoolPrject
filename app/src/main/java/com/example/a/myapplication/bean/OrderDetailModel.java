package com.example.a.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/24.
 */
public class OrderDetailModel extends BaseModel {


    private List<Shop> o;
    private List<EBean> e;

    public List<Shop> getO() {
        return o;
    }

    public void setO(List<Shop> o) {
        this.o = o;
    }

    public List<EBean> getE() {
        return e;
    }

    public void setE(List<EBean> e) {
        this.e = e;
    }

    public static class Shop {
        /**
         * bid : 1
         * bname : zara
         * logo : /Public/Uploads/20161222/585ba1de428bf.jpg
         * shops : [{"id":"5","name":"时尚羽绒","img":"","price":"100.00","orprice":"150.00","size":"1","color":"2","sum":"500"}]
         */

        private String bid;
        private String bname;
        private String logo;
        private List<ShopsBean> shops;

        public String getBid() {
            return bid;
        }

        public void setBid(String bid) {
            this.bid = bid;
        }

        public String getBname() {
            return bname;
        }

        public void setBname(String bname) {
            this.bname = bname;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public List<ShopsBean> getShops() {
            return shops;
        }

        public void setShops(List<ShopsBean> shops) {
            this.shops = shops;
        }

        public static class ShopsBean {
            /**
             * id : 5
             * name : 时尚羽绒
             * img :
             * price : 100.00
             * orprice : 150.00
             * size : 1
             * color : 2
             * sum : 500
             */

            private String id;
            private String name;
            private String img;
            private String price;
            private String orprice;
            private String size;
            private String color;
            private String sum;

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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getOrprice() {
                return orprice;
            }

            public void setOrprice(String orprice) {
                this.orprice = orprice;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getSum() {
                return sum;
            }

            public void setSum(String sum) {
                this.sum = sum;
            }
        }
    }

    public static class EBean {
        /**
         * shopSum : 4
         * priceSum : 1200.00
         * orderid : 5
         */

        private String shopSum;
        private String priceSum;
        private int orderid;
        private String freight;

        public String getFreight() {
            return freight;
        }

        public void setFreight(String freight) {
            this.freight = freight;
        }

        public String getShopSum() {
            return shopSum;
        }

        public void setShopSum(String shopSum) {
            this.shopSum = shopSum;
        }

        public String getPriceSum() {
            return priceSum;
        }

        public void setPriceSum(String priceSum) {
            this.priceSum = priceSum;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }
    }
}
