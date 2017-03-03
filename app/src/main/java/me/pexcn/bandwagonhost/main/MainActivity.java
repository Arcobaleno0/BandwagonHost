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

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import me.pexcn.android.base.mvp.BaseActivity;
import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.adapter.HostListAdapter;
import me.pexcn.bandwagonhost.data.local.Host;

/**
 * Created by pexcn on 2016-06-29.
 */
public class MainActivity extends BaseActivity<MainContract.Presenter>
        implements MainContract.View, View.OnClickListener,
        AddHostDialogFragment.OnAddHostListener {
    private RecyclerView mRecyclerView;
    private HostListAdapter mAdapter;
    private List<Host> mHosts;
    private FloatingActionButton mFab;

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
        mFab.setOnClickListener(this);

        getPresenter().start();
    }

    @Override
    public void addItem(@NonNull Host host) {
        mHosts.add(host);
        mAdapter.notifyItemInserted(mHosts.size());
    }

//    @Override
//    public void deleteItem(int position) {
//        mHosts.remove(position);
//        mAdapter.notifyItemRemoved(position);
//    }

    @Override
    public void refreshList(@NonNull List<Host> hosts) {
        if (!mHosts.isEmpty()) {
            mHosts.clear();
        }
        mHosts.addAll(hosts);
        mAdapter.notifyDataSetChanged();
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
    public void showAddHostDialog() {
        AddHostDialogFragment.newInstance().show(getSupportFragmentManager(),
                AddHostDialogFragment.class.getSimpleName());
    }

    @Override
    public void showMessage(@NonNull String msg) {
        Snackbar.make(findViewById(R.id.coordinator), msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                showAddHostDialog();
                break;
        }
    }

    @Override
    public void onAddHost(@NonNull Host host) {
        
    }
}
