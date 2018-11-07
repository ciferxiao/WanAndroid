package com.mvp.cifer.wanandroid.basemvp;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Jianting
 * Date: 2017-02-28
 * Time: 12:52
 * desc:
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    protected List<T> data = new ArrayList<>();
    protected OnItemClickListener<T> listener;
    protected OnItemClickViewListener<T> viewListener;
    protected OnItemLongClickListener<T> onItemLongClickListener;

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, final int position) {
        holder.onBaseBindViewHolder(data.get(position), position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addAll(List<T> data) {
        this.data.addAll(data);
    }

    public void add(T object) {
        data.add(object);
    }

    public void clear() {
        data.clear();
    }

    public void remove(T object) {
        data.remove(object);
    }

    public void remove(int position) {
        data.remove(position);
    }

    public void removeAll(List<T> data) {
        this.data.retainAll(data);
    }

    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        this.listener = listener;
    }

    public void setOnViewListener(OnItemClickViewListener<T> viewListener) {
        this.viewListener = viewListener;
    }

    public List<T> getData() {
        return data;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<T> onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public T getData(int position) {
        return data.get(position);
    }
}