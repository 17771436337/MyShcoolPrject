package com.example.a.myapplication.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/8.
 */
public class IntegralModel extends  BaseModel{

    private ArrayList<Integral> Integral;

    public ArrayList<IntegralModel.Integral> getIntegral() {
        return Integral;
    }

    public void setIntegral(ArrayList<IntegralModel.Integral> integral) {
        Integral = integral;
    }

    public static class Integral {

    }
}
