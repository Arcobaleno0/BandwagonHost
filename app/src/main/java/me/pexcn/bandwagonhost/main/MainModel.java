package me.pexcn.bandwagonhost.main;


import java.util.List;

import me.pexcn.bandwagonhost.data.local.Host;
import me.pexcn.bandwagonhost.data.local.HostManager;
import me.pexcn.simpleutils.SimpleUtils;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by pexcn on 2016-08-09.
 */
public class MainModel implements MainContract.Model {
    private final HostManager mHostManager;

    public MainModel() {
        mHostManager = HostManager.getInstance(SimpleUtils.getAppContext());
    }

//    @Override
//    public void addHost(@NonNull Host host, OnCompletedListener listener) {
//
//    }
//
//    @Override
//    public void deleteHost(int id, OnCompletedListener listener) {
//
//    }

    @Override
    public Observable<List<Host>> getAllHosts() {
        return Observable.create((Observable.OnSubscribe<List<Host>>) subscriber -> {
            subscriber.onNext(mHostManager.queryAll());
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.io());
    }

//    @Override
//    public Observable<Host> getHost(int position) {
//        return null;
//    }
}
