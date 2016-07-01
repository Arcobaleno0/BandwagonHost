package me.pexcn.bandwagonhost.feature.hostmanager.model;

import me.pexcn.bandwagonhost.feature.hostmanager.listener.OnAddHostStateListener;

/**
 * Created by pexcn on 2016-07-01.
 */
public interface IHostManagerModel {
    void addHost(String title, String veid, String key, OnAddHostStateListener listener);
}
