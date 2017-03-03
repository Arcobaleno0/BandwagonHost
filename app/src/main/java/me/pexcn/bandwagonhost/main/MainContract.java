package me.pexcn.bandwagonhost.main;

import android.support.annotation.NonNull;

import java.util.List;

import me.pexcn.android.base.mvp.BaseContract;
import me.pexcn.bandwagonhost.data.local.Host;
import rx.Observable;

/**
 * Created by Administrator on 2017-02-18 0018.
 */
public class MainContract implements BaseContract {
    interface View extends BaseContract.View<MainContract.Presenter> {
        void addItem(@NonNull Host host);

//        void deleteItem(int position);

        void refreshList(@NonNull List<Host> hosts);

        void showEmptyView(boolean shown);

        void showAddHostDialog();

        void showMessage(@NonNull String msg);
    }

    interface Presenter extends BaseContract.Presenter<MainContract.View, MainContract.Model> {

    }

    interface Model extends BaseContract.Model {
        Observable<List<Host>> getAllHosts();
    }
}
