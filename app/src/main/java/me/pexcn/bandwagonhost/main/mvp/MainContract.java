/*
 * BandwagonHost - A bandwagonhost.com client for Android
 * Copyright (C) 2016 Xingyu Chen <pexcn97@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package me.pexcn.bandwagonhost.main.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import me.pexcn.android.base.listener.OnCallbackListener;
import me.pexcn.android.base.mvp.BaseContract;
import me.pexcn.bandwagonhost.data.local.Host;
import rx.Observable;

/**
 * Created by Administrator on 2017-02-18 0018.
 */
@SuppressWarnings("WeakerAccess")
public class MainContract implements BaseContract {
    public interface View extends BaseContract.View<MainContract.Presenter> {
        void addItem(@NonNull Host host);

        void deleteItem(int position);

        void updateItem(int position, @NonNull Host host);

        void showHostDialog(@Nullable Bundle args);

        void showEmptyView(boolean shown);

        void showMessage(@NonNull String msg);

        void refreshList(@NonNull List<Host> hosts);

        void showFab();

        void hideFab();
    }

    public interface Presenter extends BaseContract.Presenter<MainContract.View, MainContract.Model> {
        void addHost(@NonNull Host host);

        void deleteHost(int position, @NonNull Host host);

        void updateHost(int position, @NonNull Host host);
    }

    public interface Model extends BaseContract.Model {
        void addHost(@NonNull Host host, OnCallbackListener<String> listener);

        void deleteHost(@NonNull Host host, OnCallbackListener<String> listener);

        void updateHost(@NonNull Host host, OnCallbackListener<String> listener);

        boolean isEmpty();

        Observable<List<Host>> getAllHosts();
    }
}
