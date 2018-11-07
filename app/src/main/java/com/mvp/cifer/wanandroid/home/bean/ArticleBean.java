package com.mvp.cifer.wanandroid.home.bean;

import com.mvp.cifer.wanandroid.basemvp.BaseBean;

import java.io.Serializable;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/6
 * - @time   :  16:17
 * - @desc   :
 */
public class ArticleBean implements Serializable{
    private String author;

    private String chapterName;

    private String link;

    private String niceDate;

    private String title;

    private long publishTime;

    private int zan;

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
