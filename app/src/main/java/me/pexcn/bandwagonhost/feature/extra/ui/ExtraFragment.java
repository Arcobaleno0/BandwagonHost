package me.pexcn.bandwagonhost.feature.extra.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.base.ui.BaseFragment;
import me.pexcn.bandwagonhost.feature.extra.presenter.ExtraPresenter;
import me.pexcn.bandwagonhost.feature.extra.presenter.IExtraPresenter;

/**
 * Created by pexcn on 2016-06-29.
 */
public class ExtraFragment extends BaseFragment<IExtraPresenter> implements IExtraView {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_extra;
    }

    @Override
    protected IExtraPresenter getPresenter() {
        return new ExtraPresenter(this);
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void showTips(String msg, int duration) {

    }
}
