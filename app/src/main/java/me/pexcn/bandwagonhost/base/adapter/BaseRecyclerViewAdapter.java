package me.pexcn.bandwagonhost.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by pexcn on 2016-07-04.
 */
// TODO
public abstract class BaseRecyclerViewAdapter<L extends List> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseViewHolder> {
    protected L mDatas;

    public BaseRecyclerViewAdapter(L datas) {
        this.mDatas = datas;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getItemLayout(), parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    abstract protected int getItemLayout();

    public static class BaseViewHolder extends RecyclerView.ViewHolder {
        public BaseViewHolder(View itemView) {
            super(itemView);
        }
    }
}
