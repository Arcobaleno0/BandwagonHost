package me.pexcn.bandwagonhost.main.presenter;

import me.pexcn.bandwagonhost.base.presenter.IBasePresenter;

/**
 * Created by pexcn on 2016-06-29.
 */
public interface IMainPresenter extends IBasePresenter {
    void prepare();

    void addHost(String title, String veid, String key);

    void switchToFragment(int id);
}
