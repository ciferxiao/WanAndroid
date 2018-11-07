package com.mvp.cifer.wanandroid.basemvp;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/5
 * - @time   :  14:30
 * - @desc   :
 */
public class BaseBean {
    private int errorCode;

    private String errorMsg;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
