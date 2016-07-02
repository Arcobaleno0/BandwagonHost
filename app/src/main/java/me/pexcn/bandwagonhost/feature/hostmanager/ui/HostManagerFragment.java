package me.pexcn.bandwagonhost.feature.hostmanager.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.feature.hostmanager.adapter.HostManagerListAdapter;
import me.pexcn.bandwagonhost.base.ui.BaseFragment;
import me.pexcn.bandwagonhost.bean.Host;
import me.pexcn.bandwagonhost.feature.hostmanager.presenter.HostManagerPresenter;
import me.pexcn.bandwagonhost.feature.hostmanager.presenter.IHostManagerPresenter;

/**
 * Created by pexcn on 2016-06-29.
 */
public class HostManagerFragment extends BaseFragment<IHostManagerPresenter> implements IHostManagerView {
    private RecyclerView mRecyclerView;
    private HostManagerListAdapter mAdapter;
    private List<Host> mHosts;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hostmanager;
    }

    @Override
    protected IHostManagerPresenter getPresenter() {
        return new HostManagerPresenter(this);
    }

    @Override
    protected void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rcv_list);
    }

    @Override
    protected void initData() {
        mHosts = new ArrayList<>();
        mAdapter = new HostManagerListAdapter(mActivity, mHosts);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        // TODO: 列表动画
        mRecyclerView.setAdapter(mAdapter);

        mPresenter.prepare(mHosts);
    }

    @Override
    public void refreshList() {
        // TODO: notify优化
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void addHost(Host host) {
        mHosts.add(host);
        // TODO: notify优化
        mAdapter.notifyItemInserted(mHosts.size());
    }
}
