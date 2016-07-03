package me.pexcn.bandwagonhost.feature.migrate.ui;

import android.view.View;

import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.base.ui.BaseFragment;
import me.pexcn.bandwagonhost.feature.migrate.presenter.IMigratePresenter;
import me.pexcn.bandwagonhost.feature.migrate.presenter.MigratePresenter;

/**
 * Created by pexcn on 2016-06-29.
 */
public class MigrateFragment extends BaseFragment<IMigratePresenter> implements IExtraView {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_migrate;
    }

    @Override
    protected IMigratePresenter getPresenter() {
        return new MigratePresenter(this);
    }


    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void showTips(String msg, int duration) {

    }
}
