package com.example.a.myapplication.util;

/**
 * Created by Administrator on 2016/12/19.
 */
public class Config {
    /**
     * 链接请求服务器
     */
    public final static String hostString = "http://115.28.94.239/wardrobe/code/wardrode/index.php?s=/";

    /**
     * 推片头链接
     */
    public final static String hostImgString = "http://115.28.94.239/";

    public final static String NATIVE = hostString + "wardrobe/code/wardrode/Public/Uploads/images/icon_up_img.png";//固定图片地址

    /**
     * 造型师列表
     */
    public final static String QITTMELIST = hostString + "wardrobe/code/wardrode/index.php?s=/App/Item/qItemList";

    public final static String listCount = "10";

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

    /**
     * 获取用户个人信息
     */
    public static final String getProfile = Config.hostString + "App/User/getProfile";

    /**
     * 消息提醒开关
     */
    public static final String messageremind = Config.hostString + "App/Message/messageremind";

    /**
     * 我的积分
     */
    public static final String my_Integral = Config.hostString + "App/User/my_Integral";

    /**
     * 我的粉丝列表
     */
    public static final String getfans = Config.hostString + "App/Focuson/getfans";


    /**
     * 订单列表
     */
    public static final String orderList = Config.hostString + "App/Order/orderList";


    /**
     * 我的收藏
     */
    public static final String myCollect = Config.hostString + "App/User/myCollect";

    /**
     * 购物车列表
     */
    public static final String cartList = Config.hostString + "App/Order/cartList";


    /**
     * 地址管理列表
     */
    public static final String getaddress = Config.hostString + "App/Address/getaddress";


    /**
     * 编辑地址
     */
    public static final String saveaddress = Config.hostString + "App/Address/saveaddress";

    /**
     * 新增地址
     */
    public static final String addaddress = Config.hostString + "App/Address/addaddress";

    /**
     * 个人支付宝
     */
    public static final String addPayLog = Config.hostString + "App/Pay/addPayLog";
    /**
     * 评论列表	http://115.28.94.239/wardrobe/code/wardrode/index.php?s=/App/Item/commentList
     */
    public final static String COMMENTLIST = hostString + "App/Item/commentList";


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

    /**
     * 消息提醒开关
     */
    public final static String REMIND = "remind";
}
