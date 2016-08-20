package me.pexcn.bandwagonhost.main.model;

import java.util.List;

import me.pexcn.bandwagonhost.database.Host;

/**
 * Created by pexcn on 2016-08-09.
 */
public interface IMainModel {
    void addHost(Host host);

    void deleteHost(int id);

    void updateHost(Host host);

    List<Host> queryAllHost();
}
