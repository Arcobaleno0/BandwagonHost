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

import android.os.Bundle;
import android.support.annotation.NonNull;

import me.pexcn.android.base.mvp.BasePresenter;
import me.pexcn.bandwagonhost.data.local.Host;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by pexcn on 2016-06-29.
 */
public class MainPresenter extends BasePresenter<MainContract.View, MainContract.Model>
        implements MainContract.Presenter {
    public MainPresenter(MainContract.View view) {
        super(view);
    }

    @Override
    public MainContract.Model createModel() {
        return new MainModel();
    }

    @Override
    public void start() {
        if (getModel().isDBEmpty()) {
            getView().showEmptyView(true);
        } else {
            getModel().getAllHosts()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(hosts -> getView().refreshList(hosts));
        }
    }

    @Override
    public void addHost(@NonNull Host host) {
        getModel().addHost(host, msg -> {
            getView().addItem(host);
            getView().showMessage(msg);
            if (!getModel().isDBEmpty()) {
                getView().showEmptyView(false);
            }
        });
    }

    @Override
    public void updateHost(@NonNull Host host) {
        getModel().getHostById(host.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(host_ -> {
                    final Bundle args = new Bundle();
                    args.putInt(HostDialogFragment.ARGS_ID, host_.id);
                    args.putString(HostDialogFragment.ARGS_TITLE, host_.title);
                    args.putString(HostDialogFragment.ARGS_VEID, host_.veid);
                    args.putString(HostDialogFragment.ARGS_KEY, host_.key);
                    getView().showHostDialog(args);
                });
    }
}
