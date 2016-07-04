package me.pexcn.bandwagonhost.feature.hostmanager.model;

import java.util.List;

import me.pexcn.bandwagonhost.bean.Host;

/**
 * Created by pexcn on 2016-07-01.
 */
public interface IHostManagerModel {
    boolean isEmpty();

    void insertHost(List<Host> hosts, Host host);

    void removeHost(List<Host> hosts, int id, int position);

    void loadList(List<Host> hosts);

    int[] getHostIds();
}
