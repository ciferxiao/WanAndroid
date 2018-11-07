package com.mvp.cifer.wanandroid.basemvp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public abstract class BaseRecyclerViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseRecyclerViewHolder(ViewGroup viewGroup, int layoutId) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false));
    }

    protected abstract void onBindViewHolder(T object, int position);

    public void onBaseBindViewHolder(T object, int position) {
        onBindViewHolder(object, position);
    }


    public void setGone(int viewId, boolean show) {
        itemView.findViewById(viewId).setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void setText(int viewId, String text) {
        ((TextView) itemView.findViewById(viewId)).setText(text);
    }

    protected <T extends View> T getView(int id) {
        return (T) itemView.findViewById(id);
    }

}
