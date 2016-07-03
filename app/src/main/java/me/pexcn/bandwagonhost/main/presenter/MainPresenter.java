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
        mView.setToolbarTitle("主机管理");
        mView.setNavCheckedItem(R.id.nav_hostmanager);
        mView.switchFragment(new HostManagerFragment());
    }

    @Override
    public void switchModule(int id) {
        switch (id) {
            case R.id.nav_hostmanager:
                mView.setToolbarTitle("主机管理");
                mView.setNavCheckedItem(id);
                mView.switchFragment(new HostManagerFragment());
                break;
            case R.id.nav_migrate:
                mView.setToolbarTitle("切换机房");
                mView.setNavCheckedItem(id);
                mView.switchFragment(new MigrateFragment());
                break;
            case R.id.nav_extra:
                mView.setToolbarTitle("额外功能");
                mView.setNavCheckedItem(id);
                mView.switchFragment(new ExtraFragment());
                break;
        }
    }
}
