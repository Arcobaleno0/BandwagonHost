package me.pexcn.bandwagonhost.feature.hostmanager.presenter;

import java.util.List;

import me.pexcn.bandwagonhost.base.presenter.IBasePresenter;
import me.pexcn.bandwagonhost.bean.Host;

/**
 * Created by pexcn on 2016-06-30.
 */
public interface IHostManagerPresenter extends IBasePresenter {
    void prepare(List<Host> hosts);

    void insertHost(List<Host> hosts, Host host);

    void removeHost(List<Host> hosts, int id, int position);

    int[] getHostIds();
}
