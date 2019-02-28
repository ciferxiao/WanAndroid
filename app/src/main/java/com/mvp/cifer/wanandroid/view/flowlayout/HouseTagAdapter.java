package com.mvp.cifer.wanandroid.view.flowlayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mvp.cifer.wanandroid.R;

import java.util.List;


public class HouseTagAdapter extends TagAdapter<String> {

    private LayoutInflater inflater;
    private List<String> data;
    private OnTagViewCallBacks onTagViewCallBacks;

    public HouseTagAdapter(List<String> data, Context context, OnTagViewCallBacks onTagViewCallBacks) {
        super(data);
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        this.onTagViewCallBacks = onTagViewCallBacks;
    }

    @Override
    public View getView(FlowLayout parent, final int position, String s) {

        View view = inflater.inflate(R.layout.item_flow_textview, null, false);

        ((TextView) view.findViewById(R.id.tag)).setText(s);

        view.findViewById(R.id.flow_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTagViewCallBacks == null) {
                    return;
                }
                onTagViewCallBacks.onDelete(position);
            }
        });
        view.findViewById(R.id.tag_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTagViewCallBacks == null) {
                    return;
                }
                onTagViewCallBacks.onClick(position);
            }
        });
        return view;
    }
}
