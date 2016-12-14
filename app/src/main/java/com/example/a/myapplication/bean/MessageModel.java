package com.example.a.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/9.
 */
public class MessageModel extends BaseModel{

    private List<Message> list;

    public List<Message> getList() {
        return list;
    }

    public void setList(List<Message> list) {
        this.list = list;
    }


    public static class Message{

    }
}
