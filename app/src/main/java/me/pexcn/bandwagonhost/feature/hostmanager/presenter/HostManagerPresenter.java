package me.pexcn.bandwagonhost.feature.hostmanager.presenter;

import me.pexcn.bandwagonhost.base.presenter.BasePresenter;
import me.pexcn.bandwagonhost.feature.hostmanager.ui.IHostManagerView;

/**
 * Created by pexcn on 2016-06-30.
 */
public class HostManagerPresenter extends BasePresenter<IHostManagerView, Object> implements IHostManagerPresenter {
    public HostManagerPresenter(IHostManagerView view) {
        super(view);
    }

    @Override
    protected Object getModel() {
        return null;
    }

}
