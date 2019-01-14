package com.mvp.cifer.wanandroid.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mvp.cifer.wanandroid.R;
import com.mvp.cifer.wanandroid.basemvp.BaseRecyclerViewAdapter;
import com.mvp.cifer.wanandroid.basemvp.BaseRecyclerViewHolder;
import com.mvp.cifer.wanandroid.project.ProjectBean;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/19
 * - @time   :  13:33
 * - @desc   :
 */
public class TabListAdapter extends BaseRecyclerViewAdapter<ProjectBean.DataBean> {

    private OnTitleClickListener listener;
    public void setOnTitleClickListener(OnTitleClickListener listener){
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TabListItemViewHolder(viewGroup, R.layout.tablayout_item);
    }

    public class TabListItemViewHolder extends BaseRecyclerViewHolder<ProjectBean.DataBean>{

        private TextView title;
        private CardView cardView;

        TabListItemViewHolder(ViewGroup viewGroup, int layoutId) {
            super(viewGroup, layoutId);
            title = itemView.findViewById(R.id.title);
            cardView = itemView.findViewById(R.id.cardView);

        }

        @Override
        protected void onBindViewHolder(ProjectBean.DataBean object, int position) {

            title.setText(object.getName());

            if(position == getCurrentPosition()){
                cardView.setCardBackgroundColor(itemView.getContext().getResources().getColor(R.color.blue));
                title.setTextColor(itemView.getContext().getResources().getColor(R.color.white));
            }else{
                cardView.setCardBackgroundColor(itemView.getContext().getResources().getColor(R.color.white));
                title.setTextColor(itemView.getContext().getResources().getColor(R.color.gray));
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        listener.onItemClick(object,position);
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

        void onItemClick(ProjectBean.DataBean object, int position);

    }


}
