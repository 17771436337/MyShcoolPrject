package com.example.a.myapplication.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/15.
 *
 */
public class AllCommentModel extends BaseModel {
    private ArrayList<Comment> list;

    public ArrayList<Comment> getList() {
        return list;
    }

    public void setList(ArrayList<Comment> list) {
        this.list = list;
    }


    public static class Comment {

    }
}
