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

package me.pexcn.bandwagonhost.feature.migrate.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.base.ui.BaseFragment;
import me.pexcn.bandwagonhost.feature.migrate.presenter.IMigratePresenter;
import me.pexcn.bandwagonhost.feature.migrate.presenter.MigratePresenter;
import me.pexcn.bandwagonhost.main.ui.MainActivity;

/**
 * Created by pexcn on 2016-06-29.
 */
public class MigrateFragment extends BaseFragment<IMigratePresenter>
        implements IMigrateView, View.OnClickListener, DialogInterface.OnClickListener, DialogInterface.OnKeyListener, OnMapReadyCallback {
    private SupportMapFragment mMapFragment;
    private FloatingActionMenu mFloatingActionMenu;
    private FloatingActionButton mSelectProfile;
    private FloatingActionButton mSwitchDataCenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_migrate;
    }

    @Override
    protected IMigratePresenter getPresenter() {
        return new MigratePresenter(this);
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        mMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mFloatingActionMenu = (FloatingActionMenu) view.findViewById(R.id.fab_menu);
        mMapFragment.onCreate(savedInstanceState);
        mMapFragment.getMapAsync(this);
    }

    @Override
    protected void initData() {
        mPresenter.prepare();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void showTips(String msg, int duration) {
        Snackbar.make(getView(), msg, duration).setAction("确定", this).show();
    }

    @Override
    public void hideTips() {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

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
    public void showSelectHostDialog(String[] profile) {
        new AlertDialog.Builder(mActivity)
                .setCancelable(false)
                .setSingleChoiceItems(profile, 0, this)
                .setTitle("选择主机")
                .setNegativeButton("取消", this)
                .setPositiveButton("确定", this)
                .setOnKeyListener(this)
                .show();
    }

    @Override
    public void setToolbarTitle(String title) {
        ((MainActivity) mActivity).setToolbarTitle(title);
    }
}
