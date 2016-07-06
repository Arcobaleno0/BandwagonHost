package me.pexcn.bandwagonhost.feature.hostmanager.ui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.base.ui.BaseFragment;
import me.pexcn.bandwagonhost.bean.Host;
import me.pexcn.bandwagonhost.feature.hostmanager.adapter.HostListAdapter;
import me.pexcn.bandwagonhost.feature.hostmanager.presenter.HostManagerPresenter;
import me.pexcn.bandwagonhost.feature.hostmanager.presenter.IHostManagerPresenter;
import me.pexcn.bandwagonhost.utils.TextFilter;

/**
 * Created by pexcn on 2016-06-29.
 */
public class HostManagerFragment extends BaseFragment<IHostManagerPresenter>
        implements IHostManagerView, View.OnClickListener, DialogInterface.OnKeyListener,
        HostListAdapter.OnItemClickListener, HostListAdapter.OnItemLongClickListener,
        PopupMenu.OnMenuItemClickListener {

    private RecyclerView mRecyclerView;
    private HostListAdapter mAdapter;
    private List<Host> mHosts;

    private FloatingActionButton mFab;
    private AlertDialog mDialog;
    private TextInputEditText mTitle;
    private TextInputEditText mVeid;
    private TextInputEditText mKey;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hostmanager;
    }

    @Override
    protected IHostManagerPresenter getPresenter() {
        return new HostManagerPresenter(this);
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rcv_list);
        mFab = (FloatingActionButton) view.findViewById(R.id.fab);
    }

    @Override
    protected void initData() {
        mHosts = new ArrayList<>();
        mAdapter = new HostListAdapter(mHosts);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemLongClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(mAdapter);
        mFab.setOnClickListener(this);

        setSwipeRemoveItem();
        mPresenter.prepare();
    }

    private void setSwipeRemoveItem() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // TODO: 应该可以优化
                ArrayList<Integer> ids = (ArrayList<Integer>) mPresenter.getIds();
                int currentItem = viewHolder.getAdapterPosition();
                mPresenter.removeHost(ids.get(currentItem), currentItem);
            }
        }).attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void showInsertHostDialog() {
        @SuppressLint("InflateParams") View view = getLayoutInflater(null).inflate(R.layout.dialog_addhost, null);
        mTitle = (TextInputEditText) view.findViewById(R.id.et_title);
        mVeid = (TextInputEditText) view.findViewById(R.id.et_veid);
        mKey = (TextInputEditText) view.findViewById(R.id.et_key);
        mTitle.setFilters(new InputFilter[]{new TextFilter(mTitle)});
        mVeid.setFilters(new InputFilter[]{new TextFilter(mVeid)});
        mKey.setFilters(new InputFilter[]{new TextFilter(mKey)});
        mDialog = new AlertDialog.Builder(mActivity)
                .setView(view)
                .setCancelable(false)
                .setTitle("添加主机")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", null)
                .setOnKeyListener(this)
                .show();
        mDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(this);
    }

    @Override
    public void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(mActivity, view, Gravity.END);
        popupMenu.inflate(R.menu.menu_popupmenu);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.show();
    }

    @Override
    public void insertItem(Host host) {
        mHosts.add(host);
        mAdapter.notifyItemInserted(mHosts.size());
    }

    @Override
    public void removeItem(int position) {
        mHosts.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public void showList(List<Host> hosts) {
        mHosts.addAll(hosts);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showTips(String msg, int duration) {
        Snackbar.make(mRootView.findViewById(R.id.coordinator_layout), msg, duration).setAction("确定", this).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                showInsertHostDialog();
                break;
            case android.R.id.button1:
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
                    mPresenter.insertHost(host);
                    mDialog.dismiss();
                }
                break;
            case R.id.cv_item:
                // TODO: start new activity.

                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        showPopupMenu(v);
        return true;
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP && !event.isCanceled()) {
            dialog.cancel();
            return true;
        }
        return false;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_update:

                return true;
            case R.id.menu_remove:

                return true;
        }
        return false;
    }
}
