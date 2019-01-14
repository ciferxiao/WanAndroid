package com.lzy.imagepicker;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * - @author :  JianTing
 * - @date   :  2018/8/27
 * - @time   :  16: 10
 * - @desc   :  预加载进程service （只为启动图片加载进程，无其他意义）
 */
public class HideService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
