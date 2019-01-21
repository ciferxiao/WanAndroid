package com.mvp.cifer.wanandroid.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mvp.cifer.wanandroid.basemvp.BaseBean;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/5
 * - @time   :  11:25
 * - @desc   :
 */

public class CommonUtil {

    /**
     * json转list
     */
    public static <T extends BaseBean> ArrayList<T> strToList(String dataStr, Class<T> targetClass) {
        List<T> list = null;
        try {
            list = JSON.parseArray(dataStr, targetClass);
        } catch (com.alibaba.fastjson.JSONException exception) {
            return new ArrayList<T>();
        }

        if (list != null) {
            return new ArrayList<T>(list);
        } else {
            return new ArrayList<T>();
        }
    }

    /**
     * json转bean
     *
     * @param dataStr
     * @param targetClass
     * @return
     */
    public static <T extends BaseBean> T strToBean(String dataStr, Class<T> targetClass) {
        T t = null;
        try {
            t = JSON.parseObject(dataStr, targetClass);
        } catch (com.alibaba.fastjson.JSONException exception) {
            t = null;
        }

        if (t == null) {
            try {
                t = targetClass.newInstance();
            } catch (InstantiationException e) {
                Log.d("xiao111"," e1  == " + e.toString());
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                Log.d("xiao111"," e2  == " + e.toString());
                e.printStackTrace();
            }
        }
        return t;
    }

    /**
     * json转map
     *
     * @param jsonStr
     * @return
     */
    public static TreeMap<String, Object> jsonToMap(String jsonStr) {
        TreeMap<String, Object> map = new TreeMap<String, Object>();
        // 最外层解析
        JSONObject json = JSON.parseObject(jsonStr);
        for (Object k : json.keySet()) {
            Object v = json.get(k);
            // 如果内层还是数组的话，继续解析
            if (v instanceof JSONArray) {
                List<TreeMap<String, Object>> list = new ArrayList<TreeMap<String, Object>>();
                Iterator<Object> it = ((JSONArray) v).iterator();
                while (it.hasNext()) {
                    JSONObject json2 = (JSONObject) it.next();
                    list.add(jsonToMap(json2.toString()));
                }
                map.put(k.toString(), list);
            } else {
                map.put(k.toString(), v);
            }
        }
        return map;
    }

    /**
     * Method name: saveDouleLength <BR>
     * Description: 保留小数位数 <BR>
     * Remark: <BR>
     *
     * @return double<BR>
     */
    public static String formatNumber(int doubleLength, double initValue) {
        if (doubleLength <= 0) {
            return (Double.valueOf(initValue).intValue()) + "";
        }
        StringBuffer formatStr = new StringBuffer("#.");
        for (int i = 0; i < doubleLength; i++) {
            formatStr.append("0");
        }
        DecimalFormat df = new DecimalFormat(formatStr.toString());
        String resultString = df.format(initValue);
        if (resultString.endsWith(".00")) {
            return new StringBuffer(resultString).delete(resultString.length() - 3, resultString.length()).toString();
        }
        if (resultString.startsWith(".")) {
            return "0" + resultString;
        }
        return resultString;
    }


    /**
     * Method name: getCount <BR>
     * Description: 获取汉字大小 <BR>
     * Remark: <BR>
     *
     * @param s
     * @return int<BR>
     */
    public static int getCount(String s) {
        int count = 0;
        int sum = 0;
        char[] cStr = s.toCharArray();
        for (int i = 0, j = cStr.length; i < j; i++) {
            if (cStr[i] > 127) {
                count++;
            } else {
                sum++;
            }
        }
        return count + sum / 2;
    }

    /**
     * 隐藏软键盘
     *
     * @param activity
     */
    public static void hideIme(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    /**
     * 判断软键盘是否弹出
     */
    public static boolean isSHowKeyboard(Activity mContext) {
        //获取当前屏幕内容的高度
        int screenHeight = mContext.getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        mContext.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return screenHeight - rect.bottom != 0;
    }

    /**
     * 弹出软键盘
     *
     * @param activity
     */
    public static void showIme(final Activity activity) {

        new Thread() {

            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                activity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // 弹出键盘
                        InputMethodManager m = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                        m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                });

            }
        }.start();
    }

    /**
     * 强制隐藏输入法键盘
     */
    public static void hideInput(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Method name: Formatmoney <BR>
     * Description: 去掉小数点后面的0 <BR>
     * Remark: <BR>
     *
     * @param money
     * @return String<BR>
     */
    public static String formatMoney(String money) {
        try {
            DecimalFormat format = new DecimalFormat("#");
            String wallet = format.format(Double.valueOf(money));
            money = "" + wallet;
        } catch (Exception e) {
            money = "" + money;
        }
        if (money.startsWith(".")) {
            money = money.replace(".", "0.");
        }
        return money;
    }

    /**
     * Method name: Formatmoney <BR>
     * Description: 保留小数点后面2位0 <BR>
     * Remark: <BR>
     *
     * @param money
     * @return String<BR>
     */
    public static String formatCoupon(String money) {
        try {
            DecimalFormat format = new DecimalFormat("#.00");
            String wallet = format.format(Double.valueOf(money));
            money = "" + wallet;
        } catch (Exception e) {
            money = "" + money;
        }
        if (money.startsWith(".")) {
            money = money.replace(".", "0.");
        }
        return money;
    }

    /**
     * 返回是否运行状态 true:程序在前台运行 false 后台
     */
    public static Boolean isForeground(Context mContext) {
        return isAppOnForeground(mContext) == 1;
    }

    /**
     * 返回app运行状态 1:程序在前台运行 2:程序在后台运行 3:程序未启动 注意：需要配置权限<uses-permission android:name="android.permission.GET_TASKS" />
     */
    public static int isAppOnForeground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(20);
        // 判断程序是否在栈顶
        if (list.get(0).topActivity.getPackageName().equals(context.getPackageName())) {
            return 1;
        } else {
            // 判断程序是否在栈里
            for (ActivityManager.RunningTaskInfo info : list) {
                if (info.topActivity.getPackageName().equals(context.getPackageName())) {
                    return 2;
                }
            }
            return 3;// 栈里找不到，返回3
        }
    }

    // 判断Service服务是否已经运行
    public static boolean isServiceRunning(Context context, String serviceName) {
        ActivityManager myManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>) myManager.getRunningServices(100);
        for (int i = 0; i < runningService.size(); i++) {
            if (runningService.get(i).service.getClassName().toString().equals(serviceName)) {
                return true;
            }
        }
        return false;
    }

    // 判断当前运行的Activity
    public static String getRunningActivityName(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        String runningActivity = activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
        return runningActivity;
    }


    // 给银行卡加空格
    public static String bankCardSpace(String bankCard) {
        StringBuilder phoneSb = new StringBuilder(bankCard);
        int size = bankCard.length() / 4;
        for (int i = 0; i < size; i++) {
            phoneSb.insert(i * 4 + 4 + i, " ");
        }
        return phoneSb.toString();
    }


    public static boolean isNumeric(String s) {
        Pattern pattern = compile("[0-9]*");
        Matcher isNum = pattern.matcher(s);
        return isNum.matches();

    }

    public static boolean isChinese(String s) {
        return s.matches("[\\u4e00-\\u9fa5]+");
    }

    public static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;

    /**
     * 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换算法 将 GCJ-02 坐标转换成 BD-09 坐标
     *
     * @param lat
     * @param lon
     */
    public static double[] gcj02_To_Bd09(double lat, double lon) {
        double x = lon, y = lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
        double tempLon = z * Math.cos(theta) + 0.0065;
        double tempLat = z * Math.sin(theta) + 0.006;
        double[] gps = {tempLat, tempLon};
        return gps;
    }

    /**
     * 系统时间
     */
    public static String getCurrentTime() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        return sdf.format(d);
    }

    public static String getLastModifiedFomat(String timeStamp, String type) {
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));   // 时间戳转换成时间
        return sd;
    }


}
