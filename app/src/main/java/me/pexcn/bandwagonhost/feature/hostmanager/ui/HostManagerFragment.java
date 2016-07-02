package me.pexcn.bandwagonhost.feature.hostmanager.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.adapter.HostManagerListAdapter;
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
    private List<Host> mHosts = new ArrayList<>();

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
        mAdapter = new HostManagerListAdapter(mActivity, mHosts);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(mAdapter);

        mPresenter.loadList(mHosts);
    }

    @Override
    public void refreshList() {
        mAdapter.notifyDataSetChanged();
    }
}
