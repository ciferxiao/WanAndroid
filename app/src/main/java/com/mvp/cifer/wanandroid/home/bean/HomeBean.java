package com.mvp.cifer.wanandroid.home.bean;

import com.mvp.cifer.wanandroid.basemvp.BaseBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/1
 * - @time   :  16:09
 * - @desc   :
 */
public class HomeBean  extends BaseBean{
    private DataBean data;

    public DataBean getDataBean() {
        return data;
    }

    public void setDataBean(DataBean dataBean) {
        this.data = dataBean;
    }

    public static class DataBean extends BaseBean{
        private String curPage;

        private int offset;

        private int pageCount;

        private int size;

        private int total;

        private ArrayList<ArticleBean> datas;

        public String getCurPage() {
            return curPage;
        }

        public void setCurPage(String curPage) {
            this.curPage = curPage;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public ArrayList<ArticleBean> getDatas() {
            return datas;
        }

        public void setDatas(ArrayList<ArticleBean> datas) {
            this.datas = datas;
        }

        public static class ArticleBean implements Serializable{
            private String author;

            private String chapterName;

            private String link;

            private String niceDate;

            private String title;

            private long publishTime;

            private int zan;

            private String superChapterName;

            public String getSuperChapterName() {
                return superChapterName;
            }

            public void setSuperChapterName(String superChapterName) {
                this.superChapterName = superChapterName;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getChapterName() {
                return chapterName;
            }

            public void setChapterName(String chapterName) {
                this.chapterName = chapterName;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getNiceDate() {
                return niceDate;
            }

            public void setNiceDate(String niceDate) {
                this.niceDate = niceDate;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public int getZan() {
                return zan;
            }

            public void setZan(int zan) {
                this.zan = zan;
            }

        }




    }


}
