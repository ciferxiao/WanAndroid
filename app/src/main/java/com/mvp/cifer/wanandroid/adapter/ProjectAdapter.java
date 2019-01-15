package com.mvp.cifer.wanandroid.adapter;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/19
 * - @time   :  14:53
 * - @desc   :
 */
import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvp.cifer.wanandroid.R;
import com.mvp.cifer.wanandroid.app.WanAndroidApp;
import com.mvp.cifer.wanandroid.basemvp.BaseRecyclerViewAdapter;
import com.mvp.cifer.wanandroid.basemvp.BaseRecyclerViewHolder;
import com.mvp.cifer.wanandroid.project.ProjectBean;
import com.mvp.cifer.wanandroid.project.ProjectListFragment.ProjectListBean;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/19
 * - @time   :  13:33
 * - @desc   :
 */
public class ProjectAdapter extends BaseRecyclerViewAdapter<ProjectListBean.DataBean.DataBeans> {

    private OnTitleClickListener listener;
    public void onItemClickListener(OnTitleClickListener listener){
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TabListItemViewHolder(viewGroup, R.layout.project_item);
    }

    public class TabListItemViewHolder extends BaseRecyclerViewHolder<ProjectListBean.DataBean.DataBeans>{

        private TextView title;
        private ImageView imageview;
        private TextView details;
        private TextView others;

        TabListItemViewHolder(ViewGroup viewGroup, int layoutId) {
            super(viewGroup, layoutId);
            title = itemView.findViewById(R.id.title);
            imageview = itemView.findViewById(R.id.imageview);
            details = itemView.findViewById(R.id.detail);
            others = itemView.findViewById(R.id.others);
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onBindViewHolder(final ProjectListBean.DataBean.DataBeans bean, int position) {
            WanAndroidApp.getInstance().getDisplayer(itemView.getContext())
                    .normalLoad(imageview.getContext(),imageview,bean.getEnvelopePic(),0,R.drawable.ic_about);

            details.setText(bean.getDesc());

            title.setText(bean.getTitle());
            others.setText(bean.getNiceDate() + "    " + bean.getAuthor());

            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        listener.onLookBigPic(imageview,bean.getEnvelopePic());
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        listener.onItemClick(bean,position,itemView);
                    }
                }
            });
        }
    }

    private int position;
    public void setCurrentPosition(int position){
        this.position = position;
    }

    private int getCurrentPosition(){
        return position;
    }

    public interface OnTitleClickListener{

        void onItemClick(ProjectListBean.DataBean.DataBeans object, int position,View itemView);

        //查看大图
        void onLookBigPic(ImageView view ,String url);
    }


}
