package me.pexcn.bandwagonhost.feature.hostmanager.model;

import java.util.List;

import me.pexcn.bandwagonhost.bean.Host;

/**
 * Created by pexcn on 2016-07-01.
 */
public interface IHostManagerModel {
    List<Host> loadList(List<Host> hosts);
}
