package com.mvp.cifer.wanandroid.utils;

import android.content.Context;
import android.os.Bundle;

public class BusMessageEvent {
    public enum EventMode {
        INTENT
    }

    public EventMode eventMode;
    public Context context;
    public String actionKey;
    public String actionType;
    public Bundle data;
}
