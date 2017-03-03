package me.pexcn.bandwagonhost.main;


import android.support.annotation.NonNull;

import java.util.List;

import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.data.local.Host;
import me.pexcn.bandwagonhost.data.local.HostManager;
import me.pexcn.bandwagonhost.listener.OnCompletedListener;
import rx.Observable;
import rx.schedulers.Schedulers;

import static me.pexcn.simpleutils.SimpleUtils.getAppContext;

/**
 * Created by pexcn on 2016-08-09.
 */
public class MainModel implements MainContract.Model {
    private final HostManager mHostManager;

    public MainModel() {
        mHostManager = HostManager.getInstance(getAppContext());
    }

    @Override
    public void addHost(@NonNull Host host, OnCompletedListener listener) {
        mHostManager.add(host);
        String string = getAppContext().getResources().getString(R.string.snackbar_add_completed);
        listener.onCompleted(host.title + " " + string);
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
