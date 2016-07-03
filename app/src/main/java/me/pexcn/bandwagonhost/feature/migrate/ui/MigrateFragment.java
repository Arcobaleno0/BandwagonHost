package me.pexcn.bandwagonhost.feature.migrate.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.base.ui.BaseFragment;
import me.pexcn.bandwagonhost.feature.migrate.presenter.IMigratePresenter;
import me.pexcn.bandwagonhost.feature.migrate.presenter.MigratePresenter;

/**
 * Created by pexcn on 2016-06-29.
 */
public class MigrateFragment extends BaseFragment<IMigratePresenter> implements IMigrateView, OnMapReadyCallback {
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

    }

    @Override
    protected void showTips(String msg, int duration) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }
}
