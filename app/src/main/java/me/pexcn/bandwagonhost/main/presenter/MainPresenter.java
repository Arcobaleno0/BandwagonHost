package me.pexcn.bandwagonhost.main.presenter;

import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.base.presenter.BasePresenter;
import me.pexcn.bandwagonhost.feature.extra.ui.ExtraFragment;
import me.pexcn.bandwagonhost.feature.hostmanager.ui.HostManagerFragment;
import me.pexcn.bandwagonhost.feature.migrate.ui.MigrateFragment;
import me.pexcn.bandwagonhost.main.ui.IMainView;

/**
 * Created by pexcn on 2016-06-29.
 */
public class MainPresenter extends BasePresenter<IMainView, Object> implements IMainPresenter {
    public MainPresenter(IMainView view) {
        super(view);
    }

    @Override
    protected Object getModel() {
        return null;
    }

    @Override
    public void prepare() {
        switchToFragment(R.id.nav_hostmanager);
    }

    @Override
    public void switchToFragment(int id) {
        switch (id) {
            case R.id.nav_hostmanager:
                mView.switchToFragment(new HostManagerFragment(), "主机管理", id);
                break;
            case R.id.nav_migrate:
                mView.switchToFragment(new MigrateFragment(), "切换机房", id);
                break;
            case R.id.nav_extra:
                mView.switchToFragment(new ExtraFragment(), "额外功能", id);
                break;
        }
    }
}
