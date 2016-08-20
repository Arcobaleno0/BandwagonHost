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

package me.pexcn.bandwagonhost.main.presenter;

import android.support.design.widget.Snackbar;

import java.util.List;

import me.pexcn.bandwagonhost.base.presenter.BasePresenter;
import me.pexcn.bandwagonhost.database.Host;
import me.pexcn.bandwagonhost.main.model.IMainModel;
import me.pexcn.bandwagonhost.main.model.MainModel;
import me.pexcn.bandwagonhost.main.ui.IMainView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pexcn on 2016-06-29.
 */
public class MainPresenter extends BasePresenter<IMainView, IMainModel> implements IMainPresenter {
    public MainPresenter(IMainView view) {
        super(view);
    }

    @Override
    protected IMainModel getModel() {
        return new MainModel();
    }

    @Override
    public void prepare() {
        Observable.create((Observable.OnSubscribe<List<Host>>) subscriber -> {
            subscriber.onNext(mModel.queryAllHost());
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(hosts -> {
            if (hosts.size() == 0) {

                mView.showTips("无数据，请点击右下角的按钮添加", Snackbar.LENGTH_INDEFINITE);
            }
        });
    }
}
