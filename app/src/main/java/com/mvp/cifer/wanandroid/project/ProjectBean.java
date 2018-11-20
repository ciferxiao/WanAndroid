package com.mvp.cifer.wanandroid.project;

import com.mvp.cifer.wanandroid.basemvp.BaseBean;
import com.mvp.cifer.wanandroid.home.bean.HomeBean;

import java.io.Serializable;
import java.util.List;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/16
 * - @time   :  14:50
 * - @desc   :
 */
public class ProjectBean extends BaseBean {
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private int id;

        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
