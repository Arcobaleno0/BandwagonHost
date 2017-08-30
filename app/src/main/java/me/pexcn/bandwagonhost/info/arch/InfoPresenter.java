package me.pexcn.bandwagonhost.info.arch;

import me.pexcn.android.base.arch.mvp.BasePresenter;

/**
 * Created by pexcn on 2017-03-24.
 */
public class InfoPresenter extends BasePresenter<InfoContract.View, InfoContract.Model>
        implements InfoContract.Presenter {
    public InfoPresenter(InfoContract.View view) {
        super(view);
    }

    @Override
    public InfoContract.Model createModel() {
        return new InfoModel(getContext());
    }

    @Override
    public void start() {

    }
}
