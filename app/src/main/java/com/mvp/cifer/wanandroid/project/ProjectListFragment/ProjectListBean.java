package com.mvp.cifer.wanandroid.project.ProjectListFragment;


import android.graphics.Rect;
import android.os.Parcel;
import android.support.annotation.Nullable;

import com.mvp.cifer.wanandroid.basemvp.BaseBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import previewlibrary.enitity.IThumbViewInfo;

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

    public static class DataBean implements Serializable {
        private ArrayList<DataBeans> datas;


        public ArrayList<DataBeans> getDatas() {
            return datas;
        }

        public void setDatas(ArrayList<DataBeans> datas) {
            this.datas = datas;
        }

        public static class DataBeans implements IThumbViewInfo {
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

            private Rect mBounds;

            public Rect getmBounds() {
                return mBounds;
            }

            public void setmBounds(Rect mBounds) {
                this.mBounds = mBounds;
            }

            @Override
            public String getUrl() {
                return envelopePic;
            }

            @Override
            public Rect getBounds() {
                return mBounds;
            }

            @Nullable
            @Override
            public String getVideoUrl() {
                return null;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.desc);
                dest.writeString(this.title);
                dest.writeString(this.projectLink);
                dest.writeString(this.envelopePic);
                dest.writeString(this.author);
                dest.writeParcelable(this.mBounds,flags);

            }

            public DataBeans(){};

            protected DataBeans(Parcel parcel) {
                this.desc = parcel.readString();
                this.title = parcel.readString();
                this.projectLink = parcel.readString();
                this.envelopePic = parcel.readString();
                this.author = parcel.readString();
                this.mBounds = parcel.readParcelable(Rect.class.getClassLoader());
            }

            public static final Creator<DataBeans> CREATOR = new Creator<DataBeans>() {
                @Override
                public DataBeans createFromParcel(Parcel source) {
                    return new DataBeans(source);
                }

                @Override
                public DataBeans[] newArray(int size) {
                    return new DataBeans[size];
                }
            };
        }
    }


}
