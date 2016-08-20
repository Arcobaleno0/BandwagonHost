package me.pexcn.bandwagonhost.main.model;

import java.util.List;

import me.pexcn.bandwagonhost.App;
import me.pexcn.bandwagonhost.database.Host;
import me.pexcn.bandwagonhost.database.HostManager;

/**
 * Created by pexcn on 2016-08-09.
 */
public class MainModel implements IMainModel {
    private HostManager mHostManager;

    public MainModel() {
        mHostManager = HostManager.getInstance(App.getContext());
    }

    @Override
    public void addHost(Host host) {

    }

    @Override
    public void deleteHost(int id) {

    }

    @Override
    public void updateHost(Host host) {

    }

    @Override
    public List<Host> queryAllHost() {
        return mHostManager.queryAll();
    }
}
