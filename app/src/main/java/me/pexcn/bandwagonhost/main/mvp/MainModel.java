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

import android.content.Context;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.List;

import me.pexcn.android.base.listener.OnCallbackListener;
import me.pexcn.android.base.mvp.BaseModel;
import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.data.local.Host;
import me.pexcn.bandwagonhost.data.local.HostManager;
import me.pexcn.simpleutils.Utils;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by pexcn on 2016-08-09.
 */
@SuppressWarnings("WeakerAccess")
public class MainModel extends BaseModel implements MainContract.Model {
    private HostManager mHostManager;

    public MainModel(Context context) {
        super(context);
        mHostManager = HostManager.getInstance(new WeakReference<>(context).get());
    }

    @Override
    public void addHost(@NonNull Host host, OnCallbackListener<String> listener) {
        mHostManager.add(host);
        final String string = host.title + " " + Utils.getContext().getResources()
                .getString(R.string.snackbar_text_add_completed);
        listener.callback(string);
    }

    @Override
    public void updateHost(@NonNull Host host, OnCallbackListener<String> listener) {
        mHostManager.update(host);
        final String string = host.title + " " + Utils.getContext().getResources()
                .getString(R.string.snackbar_text_update_completed);
        listener.callback(string);
    }

    @Override
    public void deleteHost(@NonNull Host host, OnCallbackListener<String> listener) {
        mHostManager.delete(host.id);
        final String string = host.title + " " + Utils.getContext().getResources()
                .getString(R.string.snackbar_text_delete_completed);
        listener.callback(string);
    }

    @Override
    public boolean isEmpty() {
        return mHostManager.queryAll().isEmpty();
    }

    @Override
    public Observable<List<Host>> getAllHosts() {
        return Observable.create((Observable.OnSubscribe<List<Host>>) subscriber -> {
            subscriber.onNext(mHostManager.queryAll());
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.io());
    }
}
