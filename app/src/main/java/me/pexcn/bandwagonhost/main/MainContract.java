package me.pexcn.bandwagonhost.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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

        void showHostDialog(@Nullable Bundle args);

        void showEmptyView(boolean shown);

        void showMessage(@NonNull String msg);

        void refreshList(@NonNull List<Host> hosts);
    }

    interface Presenter extends BaseContract.Presenter<MainContract.View, MainContract.Model> {
        void addHost(@NonNull Host host);

        void updateHost(@NonNull Host host);
    }

    interface Model extends BaseContract.Model {
        boolean isDBEmpty();

        void addHost(@NonNull Host host, OnCompletedListener listener);

        void updateHost(@NonNull Host host, OnCompletedListener listener);

        Observable<List<Host>> getAllHosts();

        Observable<Host> getHostById(int id);
    }
}
