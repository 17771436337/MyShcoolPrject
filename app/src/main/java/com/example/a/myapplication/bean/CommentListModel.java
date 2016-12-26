package com.example.a.myapplication.bean;

import java.util.List;

/**
 * Created by a on 2016/12/24.
 */

public class CommentListModel extends BaseModel {


    /**
     * o : [{"head":"/wardrobe/code/wardrode/Public/Uploads/20161207/08d2cda5f369f9f045b66a7b1f41557a.jpg","name":"洋","time":"2016-12-14 10:22","content":"哈哈哈哈哈哈"},{"head":"/wardrobe/code/wardrode/Public/Uploads/20161207/08d2cda5f369f9f045b66a7b1f41557a.jpg","name":"洋","time":"2016-12-14 10:21","content":"这个我稀罕"},{"head":"/wardrobe/code/wardrode/Public/Uploads/20161207/08d2cda5f369f9f045b66a7b1f41557a.jpg","name":"小学长","time":"2016-12-14 10:21","content":"这个评论不错"}]
     * e : true
     */

    private boolean e;
    private List<OBean> o;

    public boolean isE() {
        return e;
    }

    public void setE(boolean e) {
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
         * head : /wardrobe/code/wardrode/Public/Uploads/20161207/08d2cda5f369f9f045b66a7b1f41557a.jpg
         * name : 洋
         * time : 2016-12-14 10:22
         * content : 哈哈哈哈哈哈
         */

        private String head;
        private String name;
        private String time;
        private String content;

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
