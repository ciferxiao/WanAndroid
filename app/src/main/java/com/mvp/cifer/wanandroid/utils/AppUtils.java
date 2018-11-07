package com.mvp.cifer.wanandroid.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * - @author :  Xiao
 * - @date   :  2018/10/31
 * - @time   :  14:45
 * - @desc   :
 */
public class AppUtils {
    public static String MD5(String inStr) {

        String encryptText = null;
        try {
            MessageDigest m = MessageDigest.getInstance("md5");
            m.update(inStr.getBytes("UTF8"));
            byte s[] = m.digest();
            m.digest(inStr.getBytes("UTF8"));
            return hex(s);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encryptText;
    }

    // 返回十六进制字符串
    private static String hex(byte[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; ++i) {
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }
}
