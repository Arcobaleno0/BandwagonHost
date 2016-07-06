package me.pexcn.bandwagonhost.feature.hostmanager.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.bean.Host;

/**
 * Created by pexcn on 2016-07-01.
 */
public class HostListAdapter extends RecyclerView.Adapter<HostListAdapter.ViewHolder>
        implements View.OnClickListener, View.OnLongClickListener {
    private List<Host> mHosts;

    public HostListAdapter(List<Host> hosts) {
        this.mHosts = hosts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_host, parent, false);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mHosts.get(position).title);
    }

    @Override
    public int getItemCount() {
        return mHosts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

    /**
     * Implement OnItemClickListener and OnItemLongClickListener.
     */

    private OnItemClickListener mOnClickListener;
    private OnItemLongClickListener mOnLongClickListener;

    @Override
    public void onClick(View v) {
        if (mOnClickListener != null) {
            mOnClickListener.onClick(v);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        return mOnLongClickListener != null && mOnLongClickListener.onLongClick(v);
    }

    public void setOnItemClickListener(@Nullable OnItemClickListener listener) {
        this.mOnClickListener = listener;
    }

    public void setOnItemLongClickListener(@Nullable OnItemLongClickListener listener) {
        this.mOnLongClickListener = listener;
    }

    public interface OnItemClickListener extends View.OnClickListener {
    }

    public interface OnItemLongClickListener extends View.OnLongClickListener {
    }
}
