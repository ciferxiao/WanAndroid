package com.mvp.cifer.wanandroid.collection;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvp.cifer.wanandroid.R;
import com.mvp.cifer.wanandroid.basemvp.BaseRecyclerViewAdapter;
import com.mvp.cifer.wanandroid.basemvp.BaseRecyclerViewHolder;

import butterknife.ButterKnife;

/**
 * - @author :  Xiao
 * - @date   :  2019/1/18
 * - @time   :  15:28
 * - @desc   :
 */
public class CollectionAdapter extends BaseRecyclerViewAdapter<CollectionBean.DataBean.ArticleBean> {

    private OnItemClickListener<CollectionBean.DataBean.ArticleBean> listener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener){
        this.listener = itemClickListener;
    }

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ButterKnife.bind(this, viewGroup);
        return new CollectionArticleListViewHolder(viewGroup, R.layout.article_item);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    class CollectionArticleListViewHolder extends BaseRecyclerViewHolder<CollectionBean.DataBean.ArticleBean>{
        private TextView title;
        private TextView author;
        private TextView chapter;
        private TextView time;
        private CheckBox like;

        private ImageView lab;

        CollectionArticleListViewHolder(ViewGroup viewGroup, int layoutId) {
            super(viewGroup, layoutId);
            title = itemView.findViewById(R.id.titleView);
            author = itemView.findViewById(R.id.author);
            chapter = itemView.findViewById(R.id.chapter);
            time = itemView.findViewById(R.id.time);
            like = itemView.findViewById(R.id.like);
            lab = itemView.findViewById(R.id.imageview);
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onBindViewHolder(final CollectionBean.DataBean.ArticleBean object,final  int position) {

            chapter.setText(object.getChapterName());

            if(object.getNiceDate()!= null){
                time.setText(object.getNiceDate());//可对次判断加标签
            }
            if(object.getAuthor()!= null){
                author.setText(object.getAuthor());
            }
            if(object.getTitle()!= null){
                title.setText(object.getTitle());
            }

            like.setText("（" + object.getZan() + "）");

            like.setChecked(true);


            title.setText(object.getTitle());
            title.setText(object.getTitle());

            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        listener.onCheckBoxClick(object,position,like.isChecked());
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener !=null){
                        listener.onItemClick(object,position,v);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener<T>{

        void onItemClick(T t, int position,View view);

        void onCheckBoxClick(T t,int position,boolean ischecked);

    }

}
