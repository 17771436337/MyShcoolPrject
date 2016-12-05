package com.example.a.myapplication.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;


import com.example.a.myapplication.BaseApplication;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class SystemServiceUtil {

//  public static SearchManager getSearchManager() {
//    return (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//  }

//  public static InputMethodManager getInputMethodManager() {
//    return (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//  }
//
//  public static WifiManager getWifiManager() {
//    return (WifiManager) getSystemService(Context.WIFI_SERVICE);
//  }
//
//  public static LayoutInflater getLayoutInflater() {
//    return (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//  }

//  public static Object getSystemService(String serviceName) {
//    return BaseApplication.getSystemService(serviceName);
//  }
//
//  public static PackageManager getPackageManager() {
//    return BaseApplication.getInstance().getPackageManager();
//  }

//  public static String getVersion() {
//    Application app = BaseApplication.getInstance();
//    String version = "";
//    try {
//      version = BaseApplication.getInstance()
//          .getPackageManager()
//          .getPackageInfo(BaseApplication.getInstance().getPackageName(), 0).versionName;
//    } catch (PackageManager.NameNotFoundException e) {
//    }
//    return version;
//  }

//  public static int getVersionCode() {
//    Application app = BaseApplication.getInstance();
//    int version = 0;
//    try {
//      version = app.getPackageManager().getPackageInfo(app.getPackageName(), 0).versionCode;
//    } catch (PackageManager.NameNotFoundException e) {
//
//    }
//    return version;
//  }

//  public static String getChannelCode() {
//    Application app = BaseApplication.getInstance();
//
//    try {
//      ApplicationInfo ai = app.getPackageManager()
//          .getApplicationInfo(app.getPackageName(), PackageManager.GET_META_DATA);
//      Object value = ai.metaData.get("CHANNEL");
//      if (value != null) {
//        return value.toString();
//      }
//    } catch (Exception e) {
//      //
//    }

//    return "";
//  }

//  public static Display getDisplay() {
//    WindowManager wm =
//        (WindowManager) BaseApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
//    return wm.getDefaultDisplay();
//  }

//  public static void getHWD() {
//    WindowManager wm =
//        (WindowManager) BaseApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
//    Display display = wm.getDefaultDisplay();
//    DisplayMetrics metric = new DisplayMetrics();
//    display.getMetrics(metric);
//    //Constant.width = metric.widthPixels;
//    //Constant.height = metric.heightPixels;
//    //Constant.density = metric.density;
//  }

  /**
   * �?测网络状�?
   *
   * @return boolean
   */
  public static boolean checkNetworkStatus() {
    boolean resp = false;
    final ConnectivityManager connMgr = (ConnectivityManager)BaseApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetInfo = connMgr.getActiveNetworkInfo();
    if (activeNetInfo != null && activeNetInfo.isAvailable()) {
      resp = true;
    }
    return resp;
  }

  /**
   *
   * @author Jason
   * @time 2010-9-14
   */
  private static URL url;
  private static HttpURLConnection con;
  private static int state = -1;

  /**
   * @return URL
   */
  public static synchronized boolean isConnect(String urlStr) {
    int counts = 0;
    if (urlStr == null || urlStr.length() <= 0) {
      return false;
    }
    while (counts < 1) {
      try {
        url = new URL(urlStr);
        con = (HttpURLConnection) url.openConnection();
        state = con.getResponseCode();
        System.out.println(counts + "= " + state);
        if (state == 200) {
          System.out.println("URL可用�?");
          return true;
        }
        break;
      } catch (Exception ex) {
        counts++;
        System.out.println("URL不可用，连接�? " + counts + " �?");
        urlStr = null;
        return false;
      }
    }
    return false;
  }

  /**
   * 判断当前应用程序处于前台还是后台
   */
  public static boolean isApplicationBroughtToBackground(final Context context) {
    ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
    if (!tasks.isEmpty()) {
      ComponentName topActivity = tasks.get(0).topActivity;
      if (!topActivity.getPackageName().equals(context.getPackageName())) {
        return true;
      }
    }

    return false;
  }

  public static boolean hasSimCard(Context ct) {
    TelephonyManager tm = (TelephonyManager) ct.getSystemService(Context.TELEPHONY_SERVICE);
    if (TelephonyManager.SIM_STATE_UNKNOWN == tm.getSimState()) {
      return true;
    }
    return false;
  }

  /**
   * 检测网络是否可用
   * @return
   */
  public static boolean isNetworkConnected() {
    ConnectivityManager cm = (ConnectivityManager)BaseApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo ni = cm.getActiveNetworkInfo();
    return ni != null && ni.isConnectedOrConnecting();
  }
  /**
   * 获取当前网络类型
   * @return 0：没有网络   1：WIFI网络   2：WAP网络    3：NET网络
   */
  public static final int NETTYPE_WIFI = 0x01;
  public static final int NETTYPE_CMWAP = 0x02;
  public static final int NETTYPE_CMNET = 0x03;
  public int getNetworkType() {
    int netType = 0;
    ConnectivityManager connectivityManager = (ConnectivityManager) BaseApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
    if (networkInfo == null) {
      return netType;
    }
    int nType = networkInfo.getType();
    if (nType == ConnectivityManager.TYPE_MOBILE) {
      String extraInfo = networkInfo.getExtraInfo();
      if(!StringUtils.isEmpty(extraInfo)){
        if (extraInfo.toLowerCase().equals("cmnet")) {
          netType = NETTYPE_CMNET;
        } else {
          netType = NETTYPE_CMWAP;
        }
      }
    } else if (nType == ConnectivityManager.TYPE_WIFI) {
      netType = NETTYPE_WIFI;
    }
    return netType;
  }
}
