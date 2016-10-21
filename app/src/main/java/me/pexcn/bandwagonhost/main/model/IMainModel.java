package me.pexcn.bandwagonhost.main.model;

import me.pexcn.bandwagonhost.bean.database.Host;

/**
 * Created by pexcn on 2016-08-09.
 */
public interface IMainModel {
    boolean isEmpty();

    void addHost(Host host);

    void deleteHost(int id);

    void updateHost(Host host);
}
