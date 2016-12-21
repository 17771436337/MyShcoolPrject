package com.example.a.myapplication.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonUtils {
    public static double M_PI = Math.PI;

    /**
     * 判断包是否安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isInstalled(Context context, String packageName) {
        PackageManager manager = context.getPackageManager();
        try {
            manager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);

            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    /**
     * 安装应用程序
     *
     * @param context
     * @param apkFile
     */
    public static void installApp(Context context, File apkFile) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 打开应用程序
     *
     * @param context
     * @param packageName
     */
    public static void openApp(Context context, String packageName) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        context.startActivity(intent);
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
        /*
        移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
        联通：130、131、132、152、155、156、185、186
        电信：133、153、180、189、（1349卫通）
        总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
        */
        String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

    /**
     * 得到设备 的唯一标识符
     *
     * @return
     */
    public static String getSzimei(Context mContext) {
        String szimei;
        try {
            szimei = ((TelephonyManager) mContext.getSystemService(mContext.TELEPHONY_SERVICE)).getDeviceId();
        } catch (Exception e) {
            return "";
        }

        return szimei;
    }
    /**
     * 创建一个请求参数map对象
     */
    public static Map<String,String> getMapParm(){
        Map<String,String>     parm = new HashMap<String,String>();
        return parm ;
    }
/*
    *//**
     * 调用客服qq
     *//*
    public static void toGetQQAuth(Activity context, String qq) {
        QQAuth mqqAuth = QQAuth.createInstance("1105537388", context);
        WPA mWPA = new WPA(context, mqqAuth.getQQToken());
        String ESQ = qq;
        int ret = mWPA.startWPAConversation(context, ESQ, "你好");
        if (ret != 0) { //如果ret不为0，就说明调用SDK出现了错误
            Toast.makeText(context.getApplicationContext(),
                    "抱歉，联系客服出现了错误~. error:" + ret,
                    Toast.LENGTH_LONG).show();
        }
    }*/








    /**
     * intent跳转
     */
    public static void startIntent(Context context, Class clazz) {
        Intent intent = new Intent();
        intent.setClass(context, clazz);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * intent跳转
     */
    public static void startIntent(Activity context, Class clazz, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(context, clazz);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivityForResult(intent,requestCode);
    }

    /**
     * intent跳转
     */
    public static void startIntent(Context context, Class clazz, Bundle b) {
        Intent intent = new Intent();
        intent.setClass(context, clazz);
        intent.putExtras(b);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 跳转到短信登陆
     */
    public static Intent toLoginIntent() {
        Intent intent = new Intent();
        intent.setAction("ui.user.LoginSmsActivity");
        return intent;
    }




    //经纬度转墨卡托
    public static List<Double> lonLat2Mercator(double x, double y) {
        List<Double> ls = new ArrayList<>();
        double xx = (x / 180.0) * 20037508.34;

        if (y > 85.05112) {
            y = 85.05112;
        }
        if (y < -85.05112) {
            y = -85.05112;
        }
        y = (Math.PI / 180.0) * y;
        double tmp = Math.PI / 4.0 + y / 2.0;
        double yy = 20037508.34 * Math.log(Math.tan(tmp)) / Math.PI;
        ls.add(xx);
        ls.add(yy);

        return ls;
    }

    //墨卡托转经纬度
    public static double[] Mercator2lonLat(double mercatorX, double mercatorY) {
        double[] xy = new double[2];
        double x = mercatorX / 20037508.34 * 180;
        double y = mercatorY / 20037508.34 * 180;
        y = 180 / M_PI * (2 * Math.atan(Math.exp(y * M_PI / 180)) - M_PI / 2);
        xy[0] = x;
        xy[1] = y;
        return xy;
    }

    /**
     * 字符串判断是否为“”
     */
    public static boolean isNoString(String str) {
        if ("".equals(str)) {
            return true;
        } else {
            return false;
        }
    }

   /* *//**
     * 得到给出地址与现在地址间的距离
     *//*
    public static String getDistanceByLatLng(LatLng l1,int digit) {
        double jl;
        try{
           // LatLng nowlatlng = MapLongitudeUtil.getNowLatlng();
             //jl = MapLongitudeUtil.CalculatedDistance(l1, nowlatlng);
        }catch (Exception e){
            return "";
        }

        return BasicDataTypesUtil.doubleKeepTwoDecimalS(jl/digit);
    }*/
    /**
     *创建一个主键
     */
    public static Long getPK() {
        long time = new Date().getTime();
        long l = (long) ((Math.random() * 9 + 1) * 100000);
        return time+l;
    }


    public static void saveMyBitmap(String path, String bitName, Bitmap mBitmap) throws IOException {
        File f = new File(path);
        if (!f.exists())
            f.mkdirs();// 如果没有这个文件夹的话，会报file not found错误

        f = new File(path + bitName + ".png");
        f.createNewFile();
        try {
            FileOutputStream out = new FileOutputStream(f);
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            Log.e("text", e.getMessage());
        }

    }
}
