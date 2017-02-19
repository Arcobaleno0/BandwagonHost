package me.pexcn.bandwagonhost.main;


import me.pexcn.bandwagonhost.bean.database.Host;
import me.pexcn.bandwagonhost.database.HostManager;
import me.pexcn.simpleutils.SimpleUtils;

/**
 * Created by pexcn on 2016-08-09.
 */
public class MainModel implements MainContract.Model {
    private HostManager mHostManager;

    public MainModel() {
        mHostManager = HostManager.getInstance(SimpleUtils.getAppContext());
    }

    @Override
    public void insertHost(Host host) {

    }

    @Override
    public void removeHost(int position) {

    }

//    @Override
//    public boolean isEmpty() {
//        return mHostManager.queryAll().size() == 0;
//    }

}
