package me.pexcn.bandwagonhost.main;

import android.support.annotation.NonNull;

import java.util.List;

import me.pexcn.android.base.mvp.BaseContract;
import me.pexcn.bandwagonhost.data.local.Host;
import me.pexcn.bandwagonhost.listener.OnCompletedListener;
import rx.Observable;

/**
 * Created by Administrator on 2017-02-18 0018.
 */
public class MainContract implements BaseContract {
    interface View extends BaseContract.View<MainContract.Presenter> {
        void addItem(@NonNull Host host);

        void refreshList(@NonNull List<Host> hosts);

        void showAddHostDialog();

        void showEmptyView(boolean shown);

        void showMessage(@NonNull String msg);
    }

    interface Presenter extends BaseContract.Presenter<MainContract.View, MainContract.Model> {
        void addHost(@NonNull Host host);
    }

    interface Model extends BaseContract.Model {
        void addHost(@NonNull Host host, OnCompletedListener listener);

        boolean isEmpty();

        Observable<List<Host>> getAllHosts();
    }
}
