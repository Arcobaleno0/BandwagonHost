package me.pexcn.bandwagonhost.feature.extra.presenter;

import me.pexcn.bandwagonhost.base.presenter.BasePresenter;
import me.pexcn.bandwagonhost.feature.extra.ui.IExtraView;

/**
 * Created by pexcn on 2016-06-30.
 */
public class ExtraPresenter extends BasePresenter<IExtraView, Object> implements IExtraPresenter {
    public ExtraPresenter(IExtraView view) {
        super(view);
    }

    @Override
    protected Object getModel() {
        return null;
    }
}
