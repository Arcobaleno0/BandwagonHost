package me.pexcn.bandwagonhost.main.model;

import me.pexcn.bandwagonhost.main.listener.OnAddHostStateListener;

/**
 * Created by pexcn on 2016-07-01.
 */
public interface IMainModel {
    boolean hasHostData();

    void addHost(String title, String veid, String key, OnAddHostStateListener listener);
}
