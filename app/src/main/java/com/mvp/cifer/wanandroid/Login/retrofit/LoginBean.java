package com.mvp.cifer.wanandroid.Login.retrofit;

import com.mvp.cifer.wanandroid.basemvp.BaseBean;

import java.util.List;

/**
 * - @author :  Xiao
 * - @date   :  2018/10/31
 * - @time   :  10:10
 * - @desc   :
 *
 */
//{
//    "errorCode":1,
//    "appName": "WanAndroid",
//    "versionCode": 1,
//    "version": "0.1-beta",
//    "summary": "更新说明",
//    "updateTime": "2018/03/11",
//    "url": "https://raw.githubusercontent.com/jixiaoyong/Notes-Files/master/download/apk/WanAndroid.apk"
//}
public class LoginBean extends BaseBean{
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean{
        private List chapterTops;

        private List collectIds;

        private String email;

        private String icon;

        private Object id;

        private String password;

        private String username;

        public List getChapterTops() {
            return chapterTops;
        }

        public void setChapterTops(List chapterTops) {
            this.chapterTops = chapterTops;
        }

        public List getCollectIds() {
            return collectIds;
        }

        public void setCollectIds(List collectIds) {
            this.collectIds = collectIds;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

}
