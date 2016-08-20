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

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.base.ui.BaseActivity;
import me.pexcn.bandwagonhost.database.Host;
import me.pexcn.bandwagonhost.main.adapter.HostListAdapter;
import me.pexcn.bandwagonhost.main.presenter.IMainPresenter;
import me.pexcn.bandwagonhost.main.presenter.MainPresenter;

/**
 * Created by pexcn on 2016-06-29.
 */
public class MainActivity extends BaseActivity<IMainPresenter> implements IMainView {
    private RecyclerView mRecyclerView;
    private HostListAdapter mAdapter;
    private List<Host> mHosts;

    private FloatingActionButton mFab;

    private Snackbar mSnackbar;

    private IMainPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected boolean hasParentActivity() {
        return false;
    }

    @Override
    protected void init() {
        mPresenter = new MainPresenter(this);
        mSnackbar = Snackbar.make(findViewById(R.id.coordinator), "", Snackbar.LENGTH_INDEFINITE);

        mPresenter.prepare();
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
        if (mHosts.isEmpty()) {
            mHosts.clear();
        }
        mHosts.addAll(hosts);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDialog() {
//        View view = getLayoutInflater().inflate(R.layout.dialog_add_host, (ViewGroup) findViewById(android.R.id.content));
//        new AlertDialog.Builder(this)
//                .setView(view)
//                .setCancelable(false)
//                .setTitle("添加主机")
//                .setNegativeButton("取消", null)
//                .setPositiveButton("确定", null)
//                .setOnKeyListener(null)
//                .show();
    }

    @Override
    public void showPopupMenu(View view) {
//        PopupMenu popupMenu = new PopupMenu(this, view, Gravity.END);
//        popupMenu.inflate(R.menu.menu_popupmenu);
//        popupMenu.setOnMenuItemClickListener(item -> {
//            switch (item.getItemId()) {
//                case R.id.menu_update:
//
//                    return true;
//                case R.id.menu_remove:
//
//                    return true;
//            }
//            return false;
//        });
//        popupMenu.show();
    }

    @Override
    public void showEmptyView() {

    }

    @Override
    public void showTips(String msg, int duration) {
        mSnackbar.setText(msg).setAction("确定", null).show();
    }

    @Override
    public void hideTips() {
        mSnackbar.dismiss();
    }
}
