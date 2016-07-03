package me.pexcn.bandwagonhost.feature.migrate.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.base.ui.BaseFragment;
import me.pexcn.bandwagonhost.feature.migrate.presenter.IMigratePresenter;
import me.pexcn.bandwagonhost.feature.migrate.presenter.MigratePresenter;

/**
 * Created by pexcn on 2016-06-29.
 */
public class MigrateFragment extends BaseFragment<IMigratePresenter>
        implements IMigrateView, View.OnClickListener,
        DialogInterface.OnClickListener, DialogInterface.OnKeyListener, OnMapReadyCallback {
    private SupportMapFragment mMapFragment;

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
        mMapFragment.onCreate(savedInstanceState);
        mMapFragment.getMapAsync(this);
    }

    @Override
    protected void initData() {
        mPresenter.prepare();
    }

    @Override
    protected void showTips(String msg, int duration) {
        Snackbar.make(mActivity.findViewById(R.id.coordinator_layout), msg, duration).setAction("确定", this).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showSelectHostDialog(String[] hosts) {
        new AlertDialog.Builder(mActivity)
                .setCancelable(false)
                .setSingleChoiceItems(hosts, 0, this)
                .setTitle("选择主机")
                .setNegativeButton("取消", this)
                .setPositiveButton("确定", this)
                .setOnKeyListener(this)
                .show();
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
}
