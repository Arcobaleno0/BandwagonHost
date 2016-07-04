package me.pexcn.bandwagonhost.feature.hostmanager.model;

import android.content.Context;

import java.util.List;

import me.pexcn.bandwagonhost.bean.Host;
import me.pexcn.bandwagonhost.database.HostDatabase;

/**
 * Created by pexcn on 2016-07-01.
 */
public class HostManagerModel implements IHostManagerModel {
    private Context mContext;
    private HostDatabase mDatabase;
    private List<Host> mList;

    public HostManagerModel(Context context) {
        this.mContext = context;
        mDatabase = HostDatabase.getInstance(mContext);
        mList = mDatabase.queryAll();
    }

    @Override
    public boolean hasHost() {
        return !mDatabase.queryAll().isEmpty();
    }

    @Override
    public void addHost(List<Host> hosts, Host host) {
        mDatabase.insert(host);
        hosts.add(host);
    }

    @Override
    public void loadList(List<Host> hosts) {
        hosts.addAll(mList);
    }
}
