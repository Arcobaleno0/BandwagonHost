package me.pexcn.bandwagonhost.main;

import me.pexcn.android.base.mvp.BaseContract;
import me.pexcn.bandwagonhost.bean.database.Host;

/**
 * Created by Administrator on 2017-02-18 0018.
 */
public class MainContract implements BaseContract {
    interface View extends BaseContract.View<MainContract.Presenter> {
        void insertItem(Host host);

        void removeItem(int position);

        void setEmptyView(boolean showable);

        void showAddHostDialog();
    }

    interface Presenter extends BaseContract.Presenter<MainContract.View, MainContract.Model> {
        void insertHost(Host host);

        void removeHost(int position);
    }

    interface Model extends BaseContract.Model {
        void insertHost(Host host);

        void removeHost(int position);
    }
}
