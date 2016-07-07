package com.sqq.pulltorefresh.sqqrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/6/29.
 */
public abstract class BaseSqqAdapter extends  RecyclerView.Adapter<BaseSqqViewHolder>{
    public int TYPE_VIEWPAGER = 119;
    public int TYPE_FOOTER = 120;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    OnItemClickListener onItemClickListener;

    @Override
    public BaseSqqViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("sqqq","another");
        if (viewType == TYPE_VIEWPAGER) {
            return new BaseSqqViewHolder(LayoutInflater.from(parent.getContext()).inflate(getViewpagerLayoutID(), parent, false));
        }else if(viewType == TYPE_FOOTER){
            return new BaseSqqViewHolder(LayoutInflater.from(parent.getContext()).inflate(getFooterLayoutID(), parent, false));
        }

        return new BaseSqqViewHolder(LayoutInflater.from(parent.getContext()).inflate(getLayoutID(), parent, false));
    }

    @Override
    public void onBindViewHolder(final BaseSqqViewHolder holder, final int position) {
        if (onItemClickListener != null) {
            holder.getmConvertView().setClickable(true);
            holder.getmConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(position);
                }
            });
        }
        onBindView(holder, position);

    }

    protected abstract void onBindView(BaseSqqViewHolder holder, int position);

    protected abstract int getLayoutID();

    public int getViewpagerLayoutID() {
        return -1;
    }
    public int getFooterLayoutID() {
        return -1;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
