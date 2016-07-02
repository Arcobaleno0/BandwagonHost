package me.pexcn.bandwagonhost.main.model;

import me.pexcn.bandwagonhost.bean.Host;

/**
 * Created by pexcn on 2016-07-01.
 */
public interface IMainModel {
    interface OnAddHostFinishListener {
        void onFinish(Host host);
    }

    boolean hasHost();

    void addHost(Host host, OnAddHostFinishListener listener);
}
