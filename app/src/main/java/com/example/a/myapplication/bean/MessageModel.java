package com.example.a.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/9.
 */
public class MessageModel extends BaseModel {


    /**
     * o : [{"type;//"4","content;//"Chairman #关注了你","time;//"2016-12-28 17:05","info;//{"nid;//"8"}},{"type;//"1","content;//"你的商品发货啦","time;//"2016-12-28 16:01","info;//{"id;//"16","img;//"/Public/Uploads/20161226/58606b8edad7f.jpg","brand;//"盖尔斯（GUESS）","category;//"连衣裙"}},{"type;//"1","content;//"你的商品发货啦","time;//"2016-12-28 15:59","info;//{"id;//"18","img;//"/Public/Uploads/20161226/58608aaa48336.jpg","brand;//"范思哲（versace）","category;//"衬衣"}},{"type;//"1","content;//"你的商品发货啦","time;//"2016-12-28 15:55","info;//{"id;//"19","img;//"/Public/Uploads/20161226/5860ae560de96.jpg","brand;//"KONIKA","category;//"外套"}},{"type;//"1","content;//"你的商品发货啦","time;//"2016-12-27 18:12","info;//{"id;//"9","img;//"/Public/Uploads/20161217/5854ddf3220c2.png","brand;//"古风","category;//"西裤"}},{"type;//"2","content;//"洋#收藏了你的","time;//"2016-12-08 07:06","info;//{"id;//"1","img;//"/Public/Uploads/20161217/5854db3aa9577.png","brand;//"古孜（gucci）","category;//"下裙"}},{"type;//"6","content;//"洋#为你解答了","time;//"2016-12-08 01:50","info;//{"sid;//"1"}},{"type;//"5","content;//"洋#为你解答了","time;//"2016-12-08 01:50","info;//{"rid;//"585"}},{"type;//"4","content;//"洋#关注了你","time;//"2016-12-08 01:41","info;//{"nid;//"3"}},{"type;//"3","content;//"洋#评论你","time;//"2016-12-08 01:33","info;//false}]
     * e : null
     */

    private Object e;
    private List<Message> o;

    public Object getE() {
        return e;
    }

    public void setE(Object e) {
        this.e = e;
    }

    public List<Message> getO() {
        return o;
    }

    public void setO(List<Message> o) {
        this.o = o;
    }

    public static class Message {
        /**
         * type : 4
         * content : Chairman #关注了你
         * time : 2016-12-28 17:05
         * info : {"nid;//"8"}
         */

        private String type;
        private String content;
        private String time;
        private InfoBean info;


        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * nid : 8
             */

            private String nid;


            private String id;//"16",
            private String img;//"/Public/Uploads/20161226/58606b8edad7f.jpg",
            private String brand;//"盖尔斯（GUESS）",
            private String category;//"连衣裙"


            private String sid;//"1"

            private String rid;//


            private String comment;//": "这个评论不错",
            private String itemid;//": "1"



            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getItemid() {
                return itemid;
            }

            public void setItemid(String itemid) {
                this.itemid = itemid;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getSid() {
                return sid;
            }

            public void setSid(String sid) {
                this.sid = sid;
            }

            public String getNid() {
                return nid;
            }

            public void setNid(String nid) {
                this.nid = nid;
            }
        }
    }
}
