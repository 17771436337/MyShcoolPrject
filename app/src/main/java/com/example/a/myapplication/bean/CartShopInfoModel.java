package com.example.a.myapplication.bean;

/**
 * Created by a on 2016/12/27.
 */

public class CartShopInfoModel  extends BaseModel{

    /**
     * o : {"img":"/wardrobe/code/wardrode/Public/Uploads/20161217/5854ddf3220c2.png","name":"精品女装","price":"500.00","color":"绿色","S":"S","L":"L","XL":"XL"}
     * e :
     */

    private OBean o;
    private String e;

    public OBean getO() {
        return o;
    }

    public void setO(OBean o) {
        this.o = o;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public static class OBean {
        /**
         * img : /wardrobe/code/wardrode/Public/Uploads/20161217/5854ddf3220c2.png
         * name : 精品女装
         * price : 500.00
         * color : 绿色
         * S : S
         * L : L
         * XL : XL
         */

        private String img;
        private String name;
        private String price;
        private String color;
        private String S;
        private String L;
        private String XL;

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

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getS() {
            return S;
        }

        public void setS(String S) {
            this.S = S;
        }

        public String getL() {
            return L;
        }

        public void setL(String L) {
            this.L = L;
        }

        public String getXL() {
            return XL;
        }

        public void setXL(String XL) {
            this.XL = XL;
        }
    }
}
