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

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import me.pexcn.android.base.mvp.BaseActivity;
import me.pexcn.bandwagonhost.BuildConfig;
import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.app.App;
import me.pexcn.bandwagonhost.app.Constants;
import me.pexcn.bandwagonhost.data.local.entity.Host;
import me.pexcn.bandwagonhost.manager.ManagerActivity;
import me.pexcn.simpleutils.common.LogUtils;

/**
 * Created by pexcn on 2016-06-29.
 */
public class MainActivity extends BaseActivity<MainContract.Presenter>
        implements MainContract.View, HostDialogFragment.OnHostListener {
    @SuppressWarnings("FieldCanBeLocal")
    private RecyclerView mRecyclerView;
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

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);

        // OnRecyclerViewItemClick
        mAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(MainActivity.this, ManagerActivity.class);
            intent.putExtra(Constants.EXTRA_KEY_HOST, mHosts.get(position));
            startActivity(intent);
        });

        // OnRecyclerViewItemLongClick
        mAdapter.setOnItemLongClickListener((view, position) -> {
            final PopupMenu menu = new PopupMenu(MainActivity.this, view, Gravity.END);
            menu.inflate(R.menu.menu_popupmenu);
            menu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.menu_update:
                        if (BuildConfig.DEBUG) {
                            LogUtils.d("update => position => " + position);
                        }
                        final Bundle args = new Bundle();
                        args.putParcelable(Constants.EXTRA_KEY_HOST, mHosts.get(position));
                        showHostDialog(args);
                        return true;
                    case R.id.menu_delete:
                        if (BuildConfig.DEBUG) {
                            LogUtils.d("delete => position => " + position);
                        }
                        getPresenter().deleteHost(position, mHosts.get(position));
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
        mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount() - position);
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
        Snackbar.make(findViewById(R.id.coordinator), msg, Snackbar.LENGTH_LONG).show();
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
    public void showFab() {
        mFab.show();
    }

    @Override
    public void hideFab() {
        mFab.hide();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);

        MenuItem dayNightModeItem = menu.findItem(R.id.menu_day_night_mode);
        if (App.getNightMode()) {
            dayNightModeItem.setTitle(getString(R.string.toolbar_text_day_mode));
        } else {
            dayNightModeItem.setTitle(getString(R.string.toolbar_text_night_mode));
        }
        dayNightModeItem.setOnMenuItemClickListener(item -> {
            if (App.getNightMode()) {
                App.setNightMode(false);
            } else {
                App.setNightMode(true);
            }
            getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
            recreate();
            return true;
        });

        return true;
    }

    @Override
    public void onAdd(@NonNull Host host) {
        getPresenter().addHost(host);
    }

    @Override
    public void onUpdate(@NonNull Host host) {
        getPresenter().updateHost(mHosts.indexOf(host), host);
    }
}