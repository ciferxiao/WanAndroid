package com.mvp.cifer.wanandroid.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvp.cifer.wanandroid.R;
import com.mvp.cifer.wanandroid.basemvp.BaseRecyclerViewAdapter;
import com.mvp.cifer.wanandroid.basemvp.BaseRecyclerViewHolder;
import com.mvp.cifer.wanandroid.knowledge.bean.Knowledgebean;
import com.mvp.cifer.wanandroid.knowledgePart.PartBean;

import java.util.ArrayList;
import java.util.List;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/9
 * - @time   :  15:50
 * - @desc   :
 */
public class PartAdapter extends BaseRecyclerViewAdapter<PartBean.DataBean.DataBeans> {

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PartListViewHolder(viewGroup, R.layout.article_item);
    }

    public class PartListViewHolder extends BaseRecyclerViewHolder<PartBean.DataBean.DataBeans> {

        private TextView title;
        private TextView author;
        private TextView chapter;
        private TextView time;
        private CheckBox like;

        private ImageView lab;

        List<Knowledgebean.DataBean.ChildrenBean> list ;

        PartListViewHolder(ViewGroup viewGroup, int id) {
            super(viewGroup, id);
            title = itemView.findViewById(R.id.titleView);
            author = itemView.findViewById(R.id.author);
            chapter = itemView.findViewById(R.id.chapter);
            time = itemView.findViewById(R.id.time);
            like = itemView.findViewById(R.id.like);

        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onBindViewHolder(final PartBean.DataBean.DataBeans object,final int position) {
            chapter.setText(object.getChapterName() + "/" + object.getSuperChapterName());

            if(object.getNiceDate()!= null){
                time.setText(object.getNiceDate());//可对次判断加标签
            }
            if(object.getAuthor()!= null){
                author.setText(object.getAuthor());
            }
            if(object.getTitle()!= null){
                title.setText(object.getTitle());
            }

            title.setText(object.getTitle());
            title.setText(object.getTitle());

            like.setText("（" + object.getZan() + "）");

            if(object.isCollect()){
                like.setChecked(true);
            }else{
                like.setChecked(false);
            }


            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        listener.onClick(object,position,v);
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener !=null){
                        listener.onClick(object,position,v);
                    }
                }
            });

        }
    }

}
