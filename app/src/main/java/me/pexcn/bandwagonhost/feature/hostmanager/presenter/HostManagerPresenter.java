/*
 * BandwagonHost - A bandwagonhost.com client for Android
 * Copyright (C) 2016 Xingyu Chen (pexcn) <pexcn97@gmail.com>
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
 *
 */

package me.pexcn.bandwagonhost.feature.hostmanager.presenter;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import java.util.List;

import me.pexcn.bandwagonhost.base.presenter.BasePresenter;
import me.pexcn.bandwagonhost.bean.Host;
import me.pexcn.bandwagonhost.feature.hostmanager.model.HostManagerModel;
import me.pexcn.bandwagonhost.feature.hostmanager.model.IHostManagerModel;
import me.pexcn.bandwagonhost.feature.hostmanager.ui.IHostManagerView;

/**
 * Created by pexcn on 2016-06-30.
 */
public class HostManagerPresenter extends BasePresenter<IHostManagerView, IHostManagerModel> implements IHostManagerPresenter {
    public HostManagerPresenter(IHostManagerView view) {
        super(view);
    }

    @Override
    protected IHostManagerModel getModel() {
        return new HostManagerModel(((Fragment) mView).getActivity());
    }

    @Override
    public void prepare() {
        if (mModel.isEmpty()) {
            mView.showTips("无数据\n" + "点击右下角的 + 号以添加主机", Snackbar.LENGTH_INDEFINITE);
        } else {
            mView.showList(mModel.loadList());
        }
    }

    @Override
    public void insertHost(Host host) {
        mModel.insertHost(host);
        mView.insertItem(host);
        mView.showTips(host.title + " " + "添加成功", Snackbar.LENGTH_LONG);
    }

    @Override
    public void removeHost(int id, int position) {
        mModel.removeHost(id);
        mView.removeItem(position);
    }

    @Override
    public List<Integer> getIds() {
        return mModel.getIds();
    }
}
