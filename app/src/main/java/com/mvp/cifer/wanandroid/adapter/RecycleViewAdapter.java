package com.mvp.cifer.wanandroid.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvp.cifer.wanandroid.R;
import com.mvp.cifer.wanandroid.basemvp.BaseRecyclerViewAdapter;
import com.mvp.cifer.wanandroid.basemvp.BaseRecyclerViewHolder;
import com.mvp.cifer.wanandroid.home.bean.HomeBean;

import butterknife.ButterKnife;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/6
 * - @time   :  14:03
 * - @desc   :
 */
public class RecycleViewAdapter extends BaseRecyclerViewAdapter<HomeBean.DataBean.ArticleBean>{

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ButterKnife.bind(this, viewGroup);
        return new ArticleListViewHolder(viewGroup, R.layout.article_item);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    class ArticleListViewHolder extends BaseRecyclerViewHolder<HomeBean.DataBean.ArticleBean>{
        private TextView title;
        private TextView author;
        private TextView chapter;
        private TextView time;
        private ImageView like;

        private ImageView lab;


        ArticleListViewHolder(ViewGroup viewGroup, int layoutId) {
            super(viewGroup, layoutId);
            title = itemView.findViewById(R.id.titleView);
            author = itemView.findViewById(R.id.author);
            chapter = itemView.findViewById(R.id.chapter);
            time = itemView.findViewById(R.id.time);
            like = itemView.findViewById(R.id.like);

        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onBindViewHolder(final HomeBean.DataBean.ArticleBean object,final  int position) {

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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener !=null){
                        listener.onClick(object,position);
                    }
                }
            });
        }
    }

}
