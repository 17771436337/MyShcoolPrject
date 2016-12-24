package com.example.a.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */
public class ShopModel extends BaseModel {


    /**
     * o : [{"bid":"3","bname":"Jack Johns","logo":"/wardrobe/code/wardrode/wardrobe/code/wardrode/Public/Uploads/20161216/585389752e992.jpg","shops":[{"name":"韩版羽绒夹克","img":"/wardrobe/code/wardrode/wardrobe/code/wardrode","price":"500.00","orprice":"750.00","size":"L","color":"红色"},{"name":"韩版羽绒夹克","img":"","price":"500.00","orprice":"750.00","size":"XL","color":"黄色"}]},{"bid":"1","bname":"zara","logo":"/wardrobe/code/wardrode/wardrobe/code/wardrode/Public/Uploads/20161216/585363441250f.jpg","shops":[{"name":"时尚羽绒","img":"","price":"100.00","orprice":"150.00","size":"M","color":"蓝色"},{"name":"时尚羽绒","img":"/wardrobe/code/wardrode/wardrobe/code/wardrode","price":"100.00","orprice":"150.00","size":"XL","color":"绿色"}]}]
     * e : null
     */

    private List<EBean> e;
    private List<Shop> o;


    public List<EBean> getE() {
        return e;
    }

    public void setE(List<EBean> e) {
        this.e = e;
    }

    public List<Shop> getO() {
        return o;
    }

    public void setO(List<Shop> o) {
        this.o = o;
    }

    public static class Shop {
        /**
         * bid : 3
         * bname : Jack Johns
         * logo : /wardrobe/code/wardrode/wardrobe/code/wardrode/Public/Uploads/20161216/585389752e992.jpg
         * shops : [{"name":"韩版羽绒夹克","img":"/wardrobe/code/wardrode/wardrobe/code/wardrode","price":"500.00","orprice":"750.00","size":"L","color":"红色"},{"name":"韩版羽绒夹克","img":"","price":"500.00","orprice":"750.00","size":"XL","color":"黄色"}]
         */

        private String bid;
        private String bname;
        private String logo;
        private boolean is;  //判断是否选中

        private List<Content> shops;

        public boolean is() {
            return is;
        }

        public void setIs(boolean is) {
            this.is = is;
        }

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

        public List<Content> getShops() {
            return shops;
        }

        public void setShops(List<Content> shops) {
            this.shops = shops;
        }

        public static class Content {
            /**
             * name : 韩版羽绒夹克
             * img : /wardrobe/code/wardrode/wardrobe/code/wardrode
             * price : 500.00
             * orprice : 750.00
             * size : L
             * color : 红色
             * "sum": "1"
             */

            private String id;
            private String name;
            private String img;
            private String price;
            private String orprice;
            private String size;
            private String color;
            private String sum;
            private boolean is;  //判断是否选中

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public boolean is() {
                return is;
            }

            public void setIs(boolean is) {
                this.is = is;
            }

            public String getSum() {
                return sum;
            }

            public void setSum(String sum) {
                this.sum = sum;
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
