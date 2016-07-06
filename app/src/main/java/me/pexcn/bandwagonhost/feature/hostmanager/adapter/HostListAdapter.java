package me.pexcn.bandwagonhost.feature.hostmanager.adapter;

import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.bean.Host;
import me.pexcn.bandwagonhost.utils.common.LogUtils;

/**
 * Created by pexcn on 2016-07-01.
 */
public class HostListAdapter extends RecyclerView.Adapter<HostListAdapter.ViewHolder> {
    private List<Host> mHosts;

    public HostListAdapter(List<Host> hosts) {
        this.mHosts = hosts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_host, parent, false);
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


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener,
            PopupMenu.OnMenuItemClickListener {
        TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnLongClickListener(this);
            mTextView = (TextView) itemView.findViewById(R.id.tv_title);
        }

        private void showPopupMenu() {
            PopupMenu popupMenu = new PopupMenu(itemView.getContext(), itemView, Gravity.END);
            popupMenu.inflate(R.menu.menu_popupmenu);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
        }

        @Override
        public boolean onLongClick(View v) {
            if (v == itemView) {
                showPopupMenu();
                return true;
            }
            return false;
        }

        @Override
        public void onClick(View v) {

        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_update:
                    LogUtils.d("更新  -> " + mTextView.getText().toString());
                    return true;
                case R.id.menu_remove:
                    LogUtils.d("删除 --> " + mTextView.getText().toString());
                    return true;
            }
            return false;
        }
    }
}
