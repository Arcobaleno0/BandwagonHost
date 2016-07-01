package me.pexcn.bandwagonhost.main.presenter;

import android.content.Context;
import android.support.design.widget.Snackbar;

import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.base.presenter.BasePresenter;
import me.pexcn.bandwagonhost.feature.extra.ui.ExtraFragment;
import me.pexcn.bandwagonhost.feature.hostmanager.ui.HostManagerFragment;
import me.pexcn.bandwagonhost.feature.migrate.ui.MigrateFragment;
import me.pexcn.bandwagonhost.main.listener.OnAddHostStateListener;
import me.pexcn.bandwagonhost.main.model.IMainModel;
import me.pexcn.bandwagonhost.main.model.MainModel;
import me.pexcn.bandwagonhost.main.ui.IMainView;

/**
 * Created by pexcn on 2016-06-29.
 */
public class MainPresenter extends BasePresenter<IMainView, IMainModel> implements IMainPresenter, OnAddHostStateListener {
    public MainPresenter(IMainView view) {
        super(view);
    }

    @Override
    protected IMainModel getModel() {
        return new MainModel((Context) mView);
    }

    @Override
    public void prepare() {
        if (!mModel.hasHostData()) {
            mView.showTips("无数据\n" + "请先点击右下角的 + 号添加主机", Snackbar.LENGTH_INDEFINITE);
        }
    }

    @Override
    public void addHost(String title, String veid, String key) {
        mModel.addHost(title, veid, key, this);
    }

    @Override
    public void switchToFragment(int id) {
        switch (id) {
            case R.id.nav_host:
                mView.switchToFragment(new HostManagerFragment(), "主机管理");
                break;
            case R.id.nav_migrate:
                mView.switchToFragment(new MigrateFragment(), "切换机房");
                break;
            case R.id.nav_extra:
                mView.switchToFragment(new ExtraFragment(), "额外功能");
                break;
        }
    }

    @Override
    public void onFinished(String title) {
        mView.showTips(title + "添加成功", Snackbar.LENGTH_LONG);
        // TODO: 刷新列表
    }
}
