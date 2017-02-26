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

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import me.pexcn.android.base.mvp.BaseActivity;
import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.bean.database.Host;
import me.pexcn.bandwagonhost.main.adapter.HostListAdapter;
import me.pexcn.bandwagonhost.main.fragment.AddHostDialogFragment;

/**
 * Created by pexcn on 2016-06-29.
 */
public class MainActivity extends BaseActivity<MainContract.Presenter>
        implements MainContract.View, View.OnClickListener,
        AddHostDialogFragment.AddHostListener {
    private RecyclerView mHostList;
    private HostListAdapter mHostListAdapter;
    private List<Host> mHosts;
    private FloatingActionButton mAddHostFab;

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

        mHostList = (RecyclerView) findViewById(R.id.rcv_list);
        mAddHostFab = (FloatingActionButton) findViewById(R.id.fab_add);

        mHosts = new ArrayList<>();
        mHostListAdapter = new HostListAdapter(mHosts);

        mHostList.setAdapter(mHostListAdapter);
        mAddHostFab.setOnClickListener(v -> showAddHostDialog());
    }

    @Override
    public void insertItem(Host host) {
        mHosts.add(host);
        mHostListAdapter.notifyItemInserted(mHosts.size());
    }

    @Override
    public void removeItem(int position) {
        mHosts.remove(position);
        mHostListAdapter.notifyItemRemoved(position);
    }

    @Override
    public void showList() {

    }

    @Override
    public void setEmptyView(boolean showable) {
        final LinearLayout emptyView = (LinearLayout) findViewById(R.id.view_empty);
        if (showable) {
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onAddedHost(Host host) {
        insertItem(host);
    }
}
