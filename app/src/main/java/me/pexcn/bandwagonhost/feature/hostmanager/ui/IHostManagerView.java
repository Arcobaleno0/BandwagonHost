package me.pexcn.bandwagonhost.feature.hostmanager.ui;

/**
 * Created by pexcn on 2016-06-29.
 */
public interface IHostManagerView {
    void showTips(String msg, int duration);

    void refreshList();

    // void addHost(Host host);

    void showAddHostDialog();
}
