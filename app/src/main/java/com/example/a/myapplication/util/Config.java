package com.example.a.myapplication.util;

/**
 * Created by Administrator on 2016/12/19.
 */
public class Config {
    /**
     * 服务器
     */
    public final static String hostString = "http://115.28.94.239/wardrobe/code/wardrode/index.php?s=/";

    /**
     * 发送验证码
     * phone:手机号
     * type：1 注册 2忘记密码 3绑定手机
     */
    public static String sendCode(String phone, int type) {
        return Config.hostString + "App/User/sendPhoneCode/phone/" + phone + "/type/" + type;
    }

    /**
     * 注册
     */
    public static final String register = Config.hostString + "App/User/register";

    /**
     * 登录
     */
    public static final String login = Config.hostString + "App/User/login";

    /**
     * 退出登录
     */
    public static final String logout = Config.hostString + "App/User/logout";

    /**
     * 忘记密码
     */
    public static final String lostPwd = Config.hostString + "App/User/lostPwd";


    /**
     * 忘记密码
     */
    public static final String editPwd = Config.hostString + "App/User/editPwd";

    /**
     * 意见反馈
     */
    public static final String addfeedback = Config.hostString + "App/Feedback/addfeedback";

    //---------------------------------------------------------------------


    //********存入缓存的对应内容的键***********
    /**
     * 用户ID
     */
    public final static String ID = "id";
    /**
     * 用户名字
     */
    public final static String NAME = "name";
    /**
     * 用户头像
     */
    public final static String HEAD = "head";
    /**
     * 用户手机号
     */
    public final static String PHONE = "phone";
    /**
     * 用户密码
     */
    public final static String PASSWORD = "password";
}
