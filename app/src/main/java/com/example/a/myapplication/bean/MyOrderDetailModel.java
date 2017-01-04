package com.example.a.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/3.
 */
public class MyOrderDetailModel extends BaseModel {

    /**
     * o : [{"bid":"39","bname":"盖尔斯（GUESS）","logo":"/Public/Uploads/20161228/58635f9b3becb.jpg","shops":[{"id":"160","name":"墨蓝裙","img":"/Public/Uploads/20161230/thumb_5866393ca7b5b.jpg","price":"700.00","orprice":"699.00","size":"M","color":"水蓝","sum":"1"}]}]
     * e : {"aid":null,"province":null,"city":null,"area":null,"address":null,"name":null,"phone":null,"id":"160","freight":"0.00","favprice":"0.00","coupon":null,"order":"201701031509204899","priceSum":"700.00","time":"2017-01-03 15:09:20","audit":"0","shopSum":"1"}
     */

    private EBean e;
    private List<OrderDetails> o;

    public EBean getE() {
        return e;
    }

    public void setE(EBean e) {
        this.e = e;
    }

    public List<OrderDetails> getO() {
        return o;
    }

    public void setO(List<OrderDetails> o) {
        this.o = o;
    }

    public static class EBean {
        /**
         * aid : null
         * province : null
         * city : null
         * area : null
         * address : null
         * name : null
         * phone : null
         * id : 160
         * freight : 0.00
         * favprice : 0.00
         * coupon : null
         * order : 201701031509204899
         * priceSum : 700.00
         * time : 2017-01-03 15:09:20
         * audit : 0
         * shopSum : 1
         */

        private String aid;
        private String province;
        private String city;
        private String area;
        private String address;
        private String name;
        private String phone;
        private String id;
        private String freight;
        private String favprice;
        private String coupon;
        private String order;
        private String priceSum;
        private String time;
        private String audit;
        private String shopSum;

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFreight() {
            return freight;
        }

        public void setFreight(String freight) {
            this.freight = freight;
        }

        public String getFavprice() {
            return favprice;
        }

        public void setFavprice(String favprice) {
            this.favprice = favprice;
        }

        public String getCoupon() {
            return coupon;
        }

        public void setCoupon(String coupon) {
            this.coupon = coupon;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getPriceSum() {
            return priceSum;
        }

        public void setPriceSum(String priceSum) {
            this.priceSum = priceSum;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getAudit() {
            return audit;
        }

        public void setAudit(String audit) {
            this.audit = audit;
        }

        public String getShopSum() {
            return shopSum;
        }

        public void setShopSum(String shopSum) {
            this.shopSum = shopSum;
        }
    }

    public static class OrderDetails {
        /**
         * bid : 39
         * bname : 盖尔斯（GUESS）
         * logo : /Public/Uploads/20161228/58635f9b3becb.jpg
         * shops : [{"id":"160","name":"墨蓝裙","img":"/Public/Uploads/20161230/thumb_5866393ca7b5b.jpg","price":"700.00","orprice":"699.00","size":"M","color":"水蓝","sum":"1"}]
         */

        private String bid;
        private String bname;
        private String logo;
        private List<Order> shops;

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

        public List<Order> getShops() {
            return shops;
        }

        public void setShops(List<Order> shops) {
            this.shops = shops;
        }

        public static class Order {
            /**
             * id : 160
             * name : 墨蓝裙
             * img : /Public/Uploads/20161230/thumb_5866393ca7b5b.jpg
             * price : 700.00
             * orprice : 699.00
             * size : M
             * color : 水蓝
             * sum : 1
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
}
