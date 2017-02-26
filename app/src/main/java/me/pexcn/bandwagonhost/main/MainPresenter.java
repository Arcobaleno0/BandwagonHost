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

package me.pexcn.bandwagonhost.main;

import java.util.List;

import me.pexcn.android.base.mvp.BasePresenter;
import me.pexcn.bandwagonhost.bean.database.Host;
import me.pexcn.simpleutils.common.LogUtils;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by pexcn on 2016-06-29.
 */
public class MainPresenter extends BasePresenter<MainContract.View, MainContract.Model> implements MainContract.Presenter {
    public MainPresenter(MainContract.View view) {
        super(view);
    }

    @Override
    public MainContract.Model createModel() {
        return new MainModel();
    }

    @Override
    public void start() {
        LogUtils.d("MVP start()...");
    }

    @Override
    public void insertHost(Host host) {
        getModel().insertHost(host);
        getView().insertItem(host);
    }

    @Override
    public void removeHost(int position) {
        getModel().removeHost(position);
        getView().removeItem(position);
    }

    @Override
    public void loadHostList() {
        getModel().getAllHosts()
                .flatMap((Func1<List<Host>, Observable<?>>) Observable::from);
    }
}
