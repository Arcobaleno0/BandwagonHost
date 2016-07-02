package me.pexcn.bandwagonhost.main.model;

import android.content.Context;

import me.pexcn.bandwagonhost.bean.Host;
import me.pexcn.bandwagonhost.database.HostDatabase;

/**
 * Created by pexcn on 2016-07-01.
 */
public class MainModel implements IMainModel {
    private Context mContext;
    private HostDatabase mDatabase;

    public MainModel(Context context) {
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
}
