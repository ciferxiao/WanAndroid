package com.mvp.cifer.wanandroid.project.ProjectListFragment;


import com.mvp.cifer.wanandroid.basemvp.BaseBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/19
 * - @time   :  15:40
 * - @desc   :
 */
public class ProjectListBean extends BaseBean {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private ArrayList<DataBeans> datas;


        public ArrayList<DataBeans> getDatas() {
            return datas;
        }

        public void setDatas(ArrayList<DataBeans> datas) {
            this.datas = datas;
        }

        public static class DataBeans implements Serializable{
            private String desc;

            private String title;

            private String projectLink;

            private String envelopePic;

            private String author;

            private String niceDate;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getProjectLink() {
                return projectLink;
            }

            public void setProjectLink(String projectLink) {
                this.projectLink = projectLink;
            }

            public String getEnvelopePic() {
                return envelopePic;
            }

            public void setEnvelopePic(String envelopePic) {
                this.envelopePic = envelopePic;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getNiceDate() {
                return niceDate;
            }

            public void setNiceDate(String niceDate) {
                this.niceDate = niceDate;
            }
        }
    }


}
