/*
 * BandwagonHost - A bandwagonhost.com client for Android
 * Copyright (C) 2016 Xingyu Chen <pexcn97@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package me.pexcn.bandwagonhost.feature.manager.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.bean.Profile;

/**
 * Created by pexcn on 2016-07-01.
 */
public class ProfileListAdapter extends RecyclerView.Adapter<ProfileListAdapter.ViewHolder>
        implements View.OnClickListener, View.OnLongClickListener {
    private List<Profile> mProfiles;

    public ProfileListAdapter(List<Profile> profiles) {
        this.mProfiles = profiles;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile, parent, false);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mProfiles.get(position).title);
    }

    @Override
    public int getItemCount() {
        return mProfiles.size();
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
