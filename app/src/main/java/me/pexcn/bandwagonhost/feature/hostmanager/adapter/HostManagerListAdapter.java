package me.pexcn.bandwagonhost.feature.hostmanager.adapter;

import android.content.Context;
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
public class HostManagerListAdapter extends RecyclerView.Adapter<HostManagerListAdapter.ViewHolder> {
    private Context mContext;
    private List<Host> mHosts;

    public HostManagerListAdapter(Context context, List<Host> hosts) {
        this.mContext = context;
        this.mHosts = hosts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_host, parent, false);
        return new HostManagerListAdapter.ViewHolder(view);
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
}
