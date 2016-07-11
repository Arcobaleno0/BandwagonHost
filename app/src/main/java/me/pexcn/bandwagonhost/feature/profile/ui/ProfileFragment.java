/*
 * BandwagonHost - A bandwagonhost.com client for Android
 * Copyright (C) 2016 Xingyu Chen (pexcn) <pexcn97@gmail.com>
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

package me.pexcn.bandwagonhost.feature.profile.ui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.base.ui.BaseFragment;
import me.pexcn.bandwagonhost.bean.Profile;
import me.pexcn.bandwagonhost.feature.profile.adapter.ProfileAdapter;
import me.pexcn.bandwagonhost.feature.profile.presenter.ProfilePresenter;
import me.pexcn.bandwagonhost.feature.profile.presenter.IProfilePresenter;
import me.pexcn.bandwagonhost.utils.TextFilter;

/**
 * Created by pexcn on 2016-06-29.
 */
public class ProfileFragment extends BaseFragment<IProfilePresenter>
        implements IProfileView, View.OnClickListener, DialogInterface.OnKeyListener,
        ProfileAdapter.OnItemClickListener, ProfileAdapter.OnItemLongClickListener,
        PopupMenu.OnMenuItemClickListener {

    private RecyclerView mRecyclerView;
    private ProfileAdapter mAdapter;
    private List<Profile> mProfiles;

    private FloatingActionButton mFab;
    private AlertDialog mDialog;
    private TextInputEditText mTitle;
    private TextInputEditText mVeid;
    private TextInputEditText mKey;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    protected IProfilePresenter getPresenter() {
        return new ProfilePresenter(this);
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rcv_list);
        mFab = (FloatingActionButton) view.findViewById(R.id.fab);

        mProfiles = new ArrayList<>();
        mAdapter = new ProfileAdapter(mProfiles);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemLongClickListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        mFab.setOnClickListener(this);
        setSwipeRemoveItem();
    }

    @Override
    protected void initData() {
        mPresenter.prepare();
    }

    private void setSwipeRemoveItem() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // TODO: 应该可以优化
                ArrayList<Integer> ids = (ArrayList<Integer>) mPresenter.getIds();
                int currentItem = viewHolder.getAdapterPosition();
                mPresenter.removeHost(ids.get(currentItem), currentItem);
            }
        }).attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void showInsertHostDialog() {
        @SuppressLint("InflateParams") View view = getLayoutInflater(null).inflate(R.layout.dialog_add_profile, null);
        mTitle = (TextInputEditText) view.findViewById(R.id.et_title);
        mVeid = (TextInputEditText) view.findViewById(R.id.et_veid);
        mKey = (TextInputEditText) view.findViewById(R.id.et_key);
        mTitle.setFilters(new InputFilter[]{new TextFilter(mTitle)});
        mVeid.setFilters(new InputFilter[]{new TextFilter(mVeid)});
        mKey.setFilters(new InputFilter[]{new TextFilter(mKey)});
        mDialog = new AlertDialog.Builder(mActivity)
                .setView(view)
                .setCancelable(false)
                .setTitle("添加主机")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", null)
                .setOnKeyListener(this)
                .show();
        mDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(this);
    }

    @Override
    public void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(mActivity, view, Gravity.END);
        popupMenu.inflate(R.menu.menu_popupmenu);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.show();
    }

    @Override
    public void insertItem(Profile profile) {
        mProfiles.add(profile);
        mAdapter.notifyItemInserted(mProfiles.size());

        mRecyclerView.smoothScrollToPosition(mProfiles.size());
    }

    @Override
    public void removeItem(int position) {
        mProfiles.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public void showList(List<Profile> profiles) {
        mProfiles.addAll(profiles);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showTips(String msg, int duration) {
        // noinspection ConstantConditions
        Snackbar.make(getView(), msg, duration).setAction("确定", this).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                showInsertHostDialog();
                break;
            case android.R.id.button1:
                Profile profile = new Profile();
                profile.title = mTitle.getText().toString();
                profile.veid = mVeid.getText().toString();
                profile.key = mKey.getText().toString();
                if ("".equals(profile.title) || "".equals(profile.veid) || "".equals(profile.key)) {
                    if ("".equals(profile.title)) {
                        mTitle.setError("标题不能为空");
                    }
                    if ("".equals(profile.veid)) {
                        mVeid.setError("VEID 不能为空");
                    }
                    if ("".equals(profile.key)) {
                        mKey.setError("KEY 不能为空");
                    }
                } else {
                    mPresenter.insertHost(profile);
                    mDialog.dismiss();
                }
                break;
            case R.id.cv_item:
                // TODO: start new activity.

                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        showPopupMenu(v);
        return true;
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP && !event.isCanceled()) {
            dialog.cancel();
            return true;
        }
        return false;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_update:

                return true;
            case R.id.menu_remove:

                return true;
        }
        return false;
    }
}
