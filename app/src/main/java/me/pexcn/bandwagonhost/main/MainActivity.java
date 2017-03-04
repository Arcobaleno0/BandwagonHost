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

package me.pexcn.bandwagonhost.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import me.pexcn.android.base.mvp.BaseActivity;
import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.adapter.HostListAdapter;
import me.pexcn.bandwagonhost.data.local.Host;
import me.pexcn.simpleutils.common.LogUtils;

/**
 * Created by pexcn on 2016-06-29.
 */
public class MainActivity extends BaseActivity<MainContract.Presenter>
        implements MainContract.View, HostDialogFragment.OnHostListener {
    @SuppressWarnings("FieldCanBeLocal")
    private RecyclerView mRecyclerView;
    @SuppressWarnings("FieldCanBeLocal")
    private FloatingActionButton mFab;
    private HostListAdapter mAdapter;
    private List<Host> mHosts;

    @Override
    protected MainContract.Presenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        super.init();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mHosts = new ArrayList<>();
        mAdapter = new HostListAdapter(mHosts);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mAdapter.setOnItemClickListener((view, position) -> {

        });
        mAdapter.setOnItemLongClickListener((view, position) -> {
            final PopupMenu menu = new PopupMenu(MainActivity.this, view, Gravity.END);
            menu.inflate(R.menu.menu_popupmenu);
            menu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.menu_update:
                        final Bundle args = new Bundle();
                        args.putParcelable(HostDialogFragment.ARGS_HOST, mHosts.get(position));
                        showHostDialog(args);
                        return true;
                    case R.id.menu_remove:

                        return true;
                }
                return false;
            });
            menu.show();
        });
        mFab.setOnClickListener(v -> showHostDialog(null));

        getPresenter().start();
    }

    @Override
    public void addItem(@NonNull Host host) {
        mHosts.add(host);
        mAdapter.notifyItemInserted(mHosts.size());
    }

    @Override
    public void deleteItem(int position) {
        mHosts.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public void updateItem(int position, @NonNull Host host) {
        mHosts.set(position, host);
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public void showHostDialog(@Nullable Bundle args) {
        HostDialogFragment.newInstance(args)
                .show(getSupportFragmentManager(), HostDialogFragment.class.getSimpleName());
    }

    @Override
    public void showEmptyView(boolean shown) {
        final LinearLayout emptyView = (LinearLayout) findViewById(R.id.view_empty);
        if (shown) {
            emptyView.setVisibility(View.VISIBLE);
        } else {
            emptyView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showMessage(@NonNull String msg) {
        Snackbar.make(findViewById(R.id.coordinator), msg, Snackbar.LENGTH_SHORT)
                .setAction(getResources().getString(android.R.string.ok), v -> {
                }).show();
    }

    @Override
    public void refreshList(@NonNull List<Host> hosts) {
        if (!mHosts.isEmpty()) {
            mHosts.clear();
        }
        mHosts.addAll(hosts);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public void onAddHost(@NonNull Host host) {
        getPresenter().addHost(host);
        LogUtils.d("Add --> " + host.id + ", " + host.title + ", " + host.veid + ", " + host.key);
    }

    @Override
    public void onUpdateHost(@NonNull Host host) {
        getPresenter().updateHost(host.id, mHosts.indexOf(host));
        LogUtils.d("Update --> " + host.id + ", " + host.title + ", " + host.veid + ", " + host.key);
    }
}
