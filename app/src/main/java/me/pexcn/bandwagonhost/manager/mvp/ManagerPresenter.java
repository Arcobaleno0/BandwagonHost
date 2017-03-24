package me.pexcn.bandwagonhost.manager.mvp;

import me.pexcn.android.base.mvp.BasePresenter;

/**
 * Created by pexcn on 2017-03-24.
 */
public class ManagerPresenter extends BasePresenter<ManagerContract.View, ManagerContract.Model>
        implements ManagerContract.Presenter {
    public ManagerPresenter(ManagerActivity view) {
        super(view);
    }

    @Override
    public ManagerContract.Model createModel() {
        return new ManagerModel(getContext());
    }

    @Override
    public void start() {

    }
}
