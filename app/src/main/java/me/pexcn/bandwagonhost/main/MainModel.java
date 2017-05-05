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

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import me.pexcn.android.base.arch.mvp.BaseModel;
import me.pexcn.android.base.listener.OnCallbackListener;
import me.pexcn.android.utils.Utils;
import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.data.local.dao.HostDao;
import me.pexcn.bandwagonhost.data.local.entity.Host;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by pexcn on 2016-08-09.
 */
@SuppressWarnings("WeakerAccess")
public class MainModel extends BaseModel implements MainContract.Model {
    private HostDao mHostDao;

    public MainModel(Context context) {
        super(context);
        mHostDao = new HostDao();
    }

    @Override
    public void addHost(@NonNull Host host, OnCallbackListener<String> listener) {
        mHostDao.add(host);
        final String string = host.title + " " + Utils.getContext().getResources()
                .getString(R.string.snackbar_text_add_completed);
        listener.callback(string);
    }

    @Override
    public void updateHost(@NonNull Host host, OnCallbackListener<String> listener) {
        mHostDao.update(host);
        final String string = host.title + " " + Utils.getContext().getResources()
                .getString(R.string.snackbar_text_update_completed);
        listener.callback(string);
    }

    @Override
    public void deleteHost(@NonNull Host host, OnCallbackListener<String> listener) {
        mHostDao.delete(host.id);
        final String string = host.title + " " + Utils.getContext().getResources()
                .getString(R.string.snackbar_text_delete_completed);
        listener.callback(string);
    }

    @Override
    public boolean isEmpty() {
        return mHostDao.queryAll().isEmpty();
    }

    @Override
    public Observable<List<Host>> getAllHosts() {
        return Observable.create((Observable.OnSubscribe<List<Host>>) subscriber -> {
            subscriber.onNext(mHostDao.queryAll());
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.io());
    }
}
