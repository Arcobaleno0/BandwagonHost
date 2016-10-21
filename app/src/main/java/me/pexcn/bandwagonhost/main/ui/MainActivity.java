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
 */

package me.pexcn.bandwagonhost.main.ui;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.bean.database.Host;
import me.pexcn.bandwagonhost.main.adapter.HostListAdapter;
import me.pexcn.bandwagonhost.main.presenter.IMainPresenter;
import me.pexcn.bandwagonhost.main.presenter.MainPresenter;
import me.pexcn.simpleutils.base.mvp.view.BaseActivity;

/**
 * Created by pexcn on 2016-06-29.
 */
public class MainActivity extends BaseActivity<IMainPresenter> implements IMainView,
        DialogInterface.OnKeyListener, View.OnClickListener, PopupMenu.OnMenuItemClickListener {
    private RecyclerView mRecyclerView;
    private HostListAdapter mAdapter;
    private List<Host> mHosts;

    private FloatingActionButton mFab;
    private AlertDialog mDialog;
    private TextInputEditText mTitle;
    private TextInputEditText mVeid;
    private TextInputEditText mKey;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public IMainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void init() {
        super.init();
        mRecyclerView = (RecyclerView) findViewById(R.id.rcv_list);
        mFab = (FloatingActionButton) findViewById(R.id.fab_add);
        mHosts = new ArrayList<>();
        mAdapter = new HostListAdapter(mHosts);
        mRecyclerView.setAdapter(mAdapter);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        getPresenter().prepare();
    }

    @Override
    public void insertItem(Host host) {
        mHosts.add(host);
        mAdapter.notifyItemInserted(mHosts.size());
    }

    @Override
    public void removeItem(int position) {
        mHosts.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public void showList(List<Host> hosts) {
        if (!mHosts.isEmpty()) {
            mHosts.clear();
        }
        mHosts.addAll(hosts);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view, Gravity.END);
        popupMenu.inflate(R.menu.menu_popupmenu);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.show();
    }

    @Override
    public void setEmptyView(boolean isShow) {
        LinearLayout emptyView = (LinearLayout) findViewById(R.id.view_empty);
        if (isShow) {
            emptyView.setVisibility(View.VISIBLE);
        } else {
            emptyView.setVisibility(View.GONE);
        }
    }

    private void showDialog() {
        View view = getLayoutInflater().inflate(R.layout.dialog_add_host, (ViewGroup) getWindow().getDecorView(), false);
        mTitle = (TextInputEditText) view.findViewById(R.id.et_title);
        mVeid = (TextInputEditText) view.findViewById(R.id.et_veid);
        mKey = (TextInputEditText) view.findViewById(R.id.et_key);
        mDialog = new AlertDialog.Builder(this)
                .setView(view)
                .setTitle("添加主机")
                .setCancelable(false)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", null)
                .setOnKeyListener(this)
                .show();
        mDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(this);
    }

    private void addHost() {
        Host host = new Host();
        host.title = mTitle.getText().toString();
        host.veid = mVeid.getText().toString();
        host.key = mKey.getText().toString();
        if (host.title.equals("") || host.veid.equals("") || host.key.equals("")) {
            if (host.title.equals("")) {
                mTitle.setError("标题不能为空");
            } else if (host.veid.equals("")) {
                mVeid.setError("VEID 不能为空");
            } else if (host.key.equals("")) {
                mKey.setError("KEY 不能为空");
            }
        } else {
            mPresenter.addHost(host);
            mDialog.dismiss();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case android.R.id.button1:
                addHost();
                break;
        }
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
