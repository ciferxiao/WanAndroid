package com.mvp.cifer.wanandroid.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mvp.cifer.wanandroid.R;
import com.mvp.cifer.wanandroid.basemvp.BaseRecyclerViewAdapter;
import com.mvp.cifer.wanandroid.basemvp.BaseRecyclerViewHolder;
import com.mvp.cifer.wanandroid.knowledge.bean.Knowledgebean;
import com.mvp.cifer.wanandroid.utils.CommonUtil;
import com.mvp.cifer.wanandroid.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/8
 * - @time   :  10:34
 * - @desc   :
 */
public class KnowledgeAdapter extends BaseRecyclerViewAdapter<Knowledgebean.DataBean> {

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new KnowledgeListViewHolder(viewGroup, R.layout.knowledge_item);
    }

    class KnowledgeListViewHolder extends BaseRecyclerViewHolder<Knowledgebean.DataBean> {

        private TextView title;
        private TextView subtitle;
        List<String> subtitles ;
        List<Knowledgebean.DataBean.ChildrenBean> list ;

        KnowledgeListViewHolder(ViewGroup viewGroup, int id) {
            super(viewGroup, id);
            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);

        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onBindViewHolder(final Knowledgebean.DataBean object,final int position) {

            title.setText(object.getName());
            title.setTextColor(CommonUtils.randomColor());

            list = object.getChildren();
            subtitles  = new ArrayList<>();

            for(Knowledgebean.DataBean.ChildrenBean childrenBean : list){
                    subtitles.add(childrenBean.getName());
            }

            StringBuilder string = new StringBuilder();
            for (int i = 0; i<subtitles.size();i++){
                string.append(subtitles.get(i)).append("  ");
            }

            subtitle.setText(string.toString());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        listener.onClick(object,position,v);
                    }
                }
            });

        }
    }


}
