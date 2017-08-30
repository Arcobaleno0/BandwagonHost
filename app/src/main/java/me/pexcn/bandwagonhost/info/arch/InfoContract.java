package me.pexcn.bandwagonhost.info.arch;

import me.pexcn.android.base.arch.mvp.BaseContract;

/**
 * Created by pexcn on 2017-03-24.
 */
public interface InfoContract extends BaseContract {
    interface View extends BaseContract.View<InfoContract.Presenter> {

    }

    interface Presenter extends BaseContract.Presenter<InfoContract.View, InfoContract.Model> {

    }

    interface Model extends BaseContract.Model {

    }
}
