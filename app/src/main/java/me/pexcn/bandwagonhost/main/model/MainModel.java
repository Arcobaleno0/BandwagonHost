package me.pexcn.bandwagonhost.main.model;


import me.pexcn.bandwagonhost.bean.database.Host;
import me.pexcn.bandwagonhost.database.HostManager;
import me.pexcn.simpleutils.SimpleUtils;

/**
 * Created by pexcn on 2016-08-09.
 */
public class MainModel implements IMainModel {
    private HostManager mHostManager;

    public MainModel() {
        mHostManager = HostManager.getInstance(SimpleUtils.getAppContext());
    }

    @Override
    public boolean isEmpty() {
        return mHostManager.queryAll().size() == 0;
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
}
