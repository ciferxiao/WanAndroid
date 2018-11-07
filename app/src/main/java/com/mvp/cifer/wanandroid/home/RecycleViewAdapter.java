package com.mvp.cifer.wanandroid.home;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mvp.cifer.wanandroid.R;
import com.mvp.cifer.wanandroid.basemvp.BaseRecyclerViewAdapter;
import com.mvp.cifer.wanandroid.basemvp.BaseRecyclerViewHolder;
import com.mvp.cifer.wanandroid.home.bean.ArticleBean;
import com.mvp.cifer.wanandroid.home.bean.HomeBean;

import butterknife.BindView;
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

        ArticleListViewHolder(ViewGroup viewGroup, int layoutId) {
            super(viewGroup, layoutId);
            title = itemView.findViewById(R.id.titleView);
        }

        @Override
        protected void onBindViewHolder(HomeBean.DataBean.ArticleBean object, int position) {
            Log.d("xiao111"," title == " + object.getTitle() );

            title.setText(object.getTitle());
        }
    }

}
