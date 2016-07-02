package me.pexcn.bandwagonhost.feature.hostmanager.presenter;

import android.support.v4.app.Fragment;

import java.util.List;

import me.pexcn.bandwagonhost.base.presenter.BasePresenter;
import me.pexcn.bandwagonhost.bean.Host;
import me.pexcn.bandwagonhost.feature.hostmanager.model.HostManagerModel;
import me.pexcn.bandwagonhost.feature.hostmanager.model.IHostManagerModel;
import me.pexcn.bandwagonhost.feature.hostmanager.ui.IHostManagerView;

/**
 * Created by pexcn on 2016-06-30.
 */
public class HostManagerPresenter extends BasePresenter<IHostManagerView, IHostManagerModel> implements IHostManagerPresenter {
    public HostManagerPresenter(IHostManagerView view) {
        super(view);
    }

    @Override
    protected IHostManagerModel getModel() {
        return new HostManagerModel(((Fragment) mView).getActivity());
    }

    @Override
    public void loadList(List<Host> hosts) {
        mModel.loadList(hosts);
        mView.refreshList();
    }

}
