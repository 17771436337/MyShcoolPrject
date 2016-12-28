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
     * 推片头链接/wardrobe/code/wardrode/Public/Uploads/20161217/5854db3aa9577.png
     */
    public final static String hostImgString = "http://115.28.94.239/wardrobe/code/wardrode/";//图片地址前缀

    public final static String NATIVE = hostString + "wardrobe/code/wardrode/Public/Uploads/images/icon_up_img.png";//固定图片地址

    /**
     * 支付宝二维码图片地址
     */
    public final static String QR_IMG = "http://115.28.94.239/wardrobe/code/wardrode/Public/Uploads/images/paycode.png";

    /**
     * 造型师列表
     */
    public final static String QITTMELIST = hostString + "App/Item/qItemList";

    public final static String listCount = "10";


    /**
     * 首页专属分类
     */
    public final static String getExclusives = Config.hostString + "App/Exclusive/getExclusives";

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
     * 个人支付宝
     */
    public static final String addPayLog = Config.hostString + "App/Pay/addPayLog";

    /**
     * 支付订单
     */
    public final static String pay = Config.hostString + "App/Order/pay";


    /**
     * 提交订单--使用优惠券
     */
    public static final String useCoupon = Config.hostString + "App/Order/useCoupon";

    /**
     * 点击二维码个人支付时，获取订单后四位
     */
    public static final String getOrderLastFour = Config.hostString + "App/Order/getOrderLastFour";


    /**
     * 求单品详情-查看解答	http://115.28.94.239/wardrobe/code/wardrode/index.php?s=/App/Item/answerList
     */
    public final static String ANSWERLIST = hostString + "App/Item/answerList";

    /**
     * 获取一品类列表
     */
    public final static String getFirstCategory = hostString + "App/Category/getFirstCategory";

    /**
     * 获取二级品类列表
     */
    public final static String getSecondCategory = hostString + "App/Category/getSecondCategory";

    /**
     * 获取流行列表
     */
    public final static String getPopulars = hostString + "App/Exclusive/getPopulars";

    /**
     * 获取颜色列表
     */
    public final static String getColors = hostString + "App/Exclusive/getColors";

    /**
     * 获取品牌列表
     */
    public final static String getBrands = hostString + "App/Brand/getBrands";

    /**
     * 获取风格头像列表
     */
    public final static String getIdol = hostString + "App/Exclusive/getIdol";

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
    /**
     * 用户性别
     */
    public final static String SEX = "sex";

    /**
     * 编辑地址
     */
    public static final String saveaddress = Config.hostString + "App/Address/saveaddress";

    /**
     * 地址管理列表
     */
    public static final String getaddress = Config.hostString + "App/Address/getaddress";

    /**
     * 新增地址
     */
    public static final String addaddress = Config.hostString + "App/Address/addaddress";


    /**
     * 用户生日
     */
    public final static String AGE = "age";
    /**
     * 求单品详情	http://115.28.94.239/wardrobe/code/wardrode/index.php?s=/App/Item/itemDetails
     */
    public final static String StylistDe = hostString + "App/Item/itemDetails";
    /**
     * 评论列表	http://115.28.94.239/wardrobe/code/wardrode/index.php?s=/App/Item/commentList
     */
    public final static String COMMENTLIST = hostString + "App/Item/commentList";


    /**
     * 我的关注接口
     */
    public static final String getfocuson = Config.hostString + "App/Focuson/getfocuson";


    /**
     * 取消关注
     */
    public static final String savefocuson = Config.hostString + "App/Focuson/savefocuson";

    /**
     * 加关注
     */
    public static final String addfocuson = Config.hostString + "App/Focuson/addfocuson";

    /**
     * 完善资料/编辑资料
     */
    public static final String saveProfile = Config.hostString + "App/User/saveProfile";

    /**
     * 结算
     */
    public static final String submit = Config.hostString + "App/Order/submit";
    /**
     * 商品详情(点击加入购物车前)
     */
    public static final String shopDetails = Config.hostString + "App/Shop/shopDetails";
    /**
     * 商品详情(加入购物车页面)
     */
    public static final String cartShopInfo = Config.hostString + "App/Shop/cartShopInfo";
    /**
     * 商品详情-加入购物车
     */
    public static final String addCart = Config.hostString + "App/Order/addCart";


}

