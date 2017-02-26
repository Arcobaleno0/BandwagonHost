package me.pexcn.bandwagonhost.main;


import android.support.annotation.NonNull;

import java.util.List;

import me.pexcn.bandwagonhost.bean.database.Host;
import me.pexcn.bandwagonhost.database.HostManager;
import me.pexcn.simpleutils.SimpleUtils;
import rx.Observable;

/**
 * Created by pexcn on 2016-08-09.
 */
public class MainModel implements MainContract.Model {
    private HostManager mHostManager;

    public MainModel() {
        mHostManager = HostManager.getInstance(SimpleUtils.getAppContext());
    }

    @Override
    public void insertHost(@NonNull Host host) {

    }

    @Override
    public void removeHost(int position) {

    }

    @Override
    public Observable<List<Host>> getAllHosts() {
        return null;
    }

    @Override
    public Observable<Host> getHost() {
        return null;
    }
}
