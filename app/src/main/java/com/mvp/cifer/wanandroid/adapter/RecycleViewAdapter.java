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
import com.mvp.cifer.wanandroid.home.bean.HomeBean;

import butterknife.ButterKnife;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/6
 * - @time   :  14:03
 * - @desc   :
 */
public class RecycleViewAdapter extends BaseRecyclerViewAdapter<HomeBean.DataBean.ArticleBean>{
    private final static String url = "http://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png";

    private OnItemClickListener<HomeBean.DataBean.ArticleBean> listener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener){
        this.listener = itemClickListener;
    }

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
        private CheckBox like;

        private ImageView lab;


        ArticleListViewHolder(ViewGroup viewGroup, int layoutId) {
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

            like.setText("（" + object.getZan() + "）");

            if(object.isCollect()){
                like.setChecked(true);
            }else{
                like.setChecked(false);
            }

            //Glide框架测试
/*            WanAndroidApp.getInstance().getDisplayer(itemView.getContext()).normalLoad(itemView.getContext(),lab,
                    url,0,
                    R.drawable.ic_about);*/

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

    public void setLikeCount(int position,boolean isChecked){
        HomeBean.DataBean.ArticleBean bean = data.get(position);

        if (isChecked){
            bean.setZan(bean.getZan()+1);
        }else{
            bean.setZan(bean.getZan()-1);
        }
        notifyItemChanged(position);
    }


    public interface OnItemClickListener<T>{

        void onItemClick(T t, int position,View view);

        void onCheckBoxClick(T t,int position,boolean ischecked);

    }

}
