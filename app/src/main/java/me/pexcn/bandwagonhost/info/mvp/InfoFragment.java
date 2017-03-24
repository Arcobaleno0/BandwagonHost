package me.pexcn.bandwagonhost.info.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import me.pexcn.android.base.mvp.BaseFragment;
import me.pexcn.bandwagonhost.BuildConfig;
import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.app.App;

/**
 * Created by pexcn on 2017-03-24.
 */
public class InfoFragment extends BaseFragment<InfoContract.Presenter> implements InfoContract.View {
    public static Fragment newInstance() {
        return new InfoFragment();
    }

    @Override
    protected InfoContract.Presenter createPresenter() {
        return new InfoPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_info;
    }

    @Override
    protected void initView(View view) {
        if (BuildConfig.DEBUG) {
            App.getRefWatcher().watch(this);
            App.getRefWatcher().watch(view);
        }
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {

    }
}
