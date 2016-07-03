package me.pexcn.bandwagonhost.feature.migrate.presenter;

import me.pexcn.bandwagonhost.base.presenter.BasePresenter;
import me.pexcn.bandwagonhost.feature.migrate.model.IMigrateModel;
import me.pexcn.bandwagonhost.feature.migrate.ui.IMigrateView;

/**
 * Created by pexcn on 2016-06-30.
 */
public class MigratePresenter extends BasePresenter<IMigrateView, IMigrateModel> implements IMigratePresenter {
    public MigratePresenter(IMigrateView view) {
        super(view);
    }

    @Override
    protected IMigrateModel getModel() {
        return null;
    }
}
