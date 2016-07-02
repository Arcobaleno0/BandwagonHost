package me.pexcn.bandwagonhost.feature.hostmanager.ui;

import me.pexcn.bandwagonhost.base.ui.IBaseView;
import me.pexcn.bandwagonhost.bean.Host;

/**
 * Created by pexcn on 2016-06-29.
 */
public interface IHostManagerView extends IBaseView {
    void refreshList();

    void addHost(Host host);
}
