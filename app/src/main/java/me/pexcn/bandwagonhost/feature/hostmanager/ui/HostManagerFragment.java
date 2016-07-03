package me.pexcn.bandwagonhost.feature.hostmanager.ui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.base.ui.BaseFragment;
import me.pexcn.bandwagonhost.bean.Host;
import me.pexcn.bandwagonhost.feature.hostmanager.adapter.HostManagerListAdapter;
import me.pexcn.bandwagonhost.feature.hostmanager.presenter.HostManagerPresenter;
import me.pexcn.bandwagonhost.feature.hostmanager.presenter.IHostManagerPresenter;
import me.pexcn.bandwagonhost.utils.TextFilter;

/**
 * Created by pexcn on 2016-06-29.
 */
public class HostManagerFragment extends BaseFragment<IHostManagerPresenter>
        implements IHostManagerView, View.OnClickListener {

    /**
     * TODO: RecyclerView 列表动画
     * TODO: 列表 notify 优化
     */

    private RecyclerView mRecyclerView;
    private HostManagerListAdapter mAdapter;
    private FloatingActionButton mFab;

    private TextInputEditText mTitle;
    private TextInputEditText mVeid;
    private TextInputEditText mKey;

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
        mFab = (FloatingActionButton) view.findViewById(R.id.fab);
    }

    @Override
    protected void initData() {
        mHosts = new ArrayList<>();
        mAdapter = new HostManagerListAdapter(mActivity, mHosts);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(mAdapter);

        mFab.setOnClickListener(this);

        mPresenter.prepare(mHosts);
    }

    @Override
    public void showTips(String msg, int duration) {
        Snackbar.make(mActivity.findViewById(R.id.coordinator_layout), msg, duration).setAction("确定", this).show();
    }

    @Override
    public void refreshList() {
        mAdapter.notifyDataSetChanged();
    }

//    @Override
//    public void addHost(Host host) {
//        mHosts.add(host);
//        mAdapter.notifyItemInserted(mHosts.size());
//    }

    @Override
    public void showAddHostDialog() {
        @SuppressLint("InflateParams") View view = getLayoutInflater(null).inflate(R.layout.dialog_addhost, null);
        mTitle = (TextInputEditText) view.findViewById(R.id.et_title);
        mVeid = (TextInputEditText) view.findViewById(R.id.et_veid);
        mKey = (TextInputEditText) view.findViewById(R.id.et_key);
        mTitle.setFilters(new InputFilter[]{new TextFilter(mTitle)});
        mVeid.setFilters(new InputFilter[]{new TextFilter(mVeid)});
        mKey.setFilters(new InputFilter[]{new TextFilter(mKey)});
        final AlertDialog dialog = new AlertDialog.Builder(mActivity)
                .setView(view)
                .setCancelable(false)
                .setTitle("添加主机")
                .setPositiveButton("确定", null)
                .setNegativeButton("取消", null)
                .create();
        dialog.show();
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP && !event.isCanceled()) {
                    dialog.cancel();
                    return true;
                }
                return false;
            }
        });
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Host host = new Host();
                host.title = mTitle.getText().toString();
                host.veid = mVeid.getText().toString();
                host.key = mKey.getText().toString();
                if ("".equals(host.title) || "".equals(host.veid) || "".equals(host.key)) {
                    if ("".equals(host.title)) {
                        mTitle.setError("标题不能为空");
                    }
                    if ("".equals(host.veid)) {
                        mVeid.setError("VEID 不能为空");
                    }
                    if ("".equals(host.key)) {
                        mKey.setError("KEY 不能为空");
                    }
                } else {
                    mPresenter.addHost(host);
                    dialog.dismiss();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                showAddHostDialog();
                break;
        }
    }
}
