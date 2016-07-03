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

    public HostManagerModel(Context context) {
        this.mContext = context;
        mDatabase = HostDatabase.getInstance(mContext);
    }

    @Override
    public boolean hasHost() {
        return !mDatabase.queryAll().isEmpty();
    }

    @Override
    public void addHost(Host host, OnAddHostFinishListener listener) {
        mDatabase.insert(host);
        listener.onFinish(host);
    }

    @Override
    public void loadList(List<Host> hosts) {
        if (!hosts.isEmpty()) {
            hosts.clear();
        }
        hosts.addAll(mDatabase.queryAll());
    }
}
