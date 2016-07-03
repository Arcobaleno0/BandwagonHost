package me.pexcn.bandwagonhost.feature.hostmanager.model;

import java.util.List;

import me.pexcn.bandwagonhost.bean.Host;

/**
 * Created by pexcn on 2016-07-01.
 */
public interface IHostManagerModel {
    interface OnAddHostFinishListener {
        void onFinish(Host host);
    }

    boolean hasHost();

    void addHost(Host host, OnAddHostFinishListener listener);

    void loadList(List<Host> hosts);
}
