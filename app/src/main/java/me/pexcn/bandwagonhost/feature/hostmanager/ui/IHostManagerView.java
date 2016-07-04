package me.pexcn.bandwagonhost.feature.hostmanager.ui;

/**
 * Created by pexcn on 2016-06-29.
 */
public interface IHostManagerView {
    void refreshList(int position);

    void showAddHostDialog();

    void showTips(String msg, int duration);
}
