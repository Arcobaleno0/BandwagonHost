package me.pexcn.bandwagonhost.main.presenter;

import me.pexcn.bandwagonhost.base.presenter.IBasePresenter;
import me.pexcn.bandwagonhost.bean.Host;

/**
 * Created by pexcn on 2016-06-29.
 */
public interface IMainPresenter extends IBasePresenter {
    void prepare();

    void switchToFragment(int id);

    void addHost(Host host);
}
