package me.pexcn.bandwagonhost.feature.hostmanager.presenter;

import android.support.design.widget.Snackbar;
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
    public void prepare(List<Host> hosts) {
        if (mModel.isEmpty()) {
            mView.showTips("无数据\n" + "请先点击右下角的 + 号添加主机", Snackbar.LENGTH_INDEFINITE);
        }
        mView.setSwipeRemoveItem();
        mModel.loadList(hosts);
    }

    @Override
    public void insertHost(List<Host> hosts, Host host) {
        mModel.insertHost(hosts, host);
        mView.refreshList(hosts.size());
        mView.showTips(host.title + " " + "添加成功", Snackbar.LENGTH_LONG);
    }

    @Override
    public void removeHost(List<Host> hosts, int id, int position) {
        mModel.removeHost(hosts, id, position);
    }

    @Override
    public int[] getHostIds() {
        return mModel.getHostIds();
    }
}
