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

package me.pexcn.bandwagonhost.manager.presenter;

import java.util.List;

import me.pexcn.bandwagonhost.bean.database.Host;
import me.pexcn.bandwagonhost.manager.model.IManagerModel;
import me.pexcn.bandwagonhost.manager.ui.IManagerView;
import me.pexcn.simpleutils.base.mvp.presenter.BasePresenter;

/**
 * Created by pexcn on 2016-06-30.
 */
public class ManagerPresenter extends BasePresenter<IManagerView, IManagerModel> implements IManagerPresenter {
    public ManagerPresenter(IManagerView view) {
        super(view);
    }

    @Override
    public IManagerModel createModel() {
        return null;
    }

    @Override
    public void prepare() {
        if (getModel().isHostEmpty()) {

        } else {
            getView().showHostList(getModel().getHostList());
        }
    }

    @Override
    public void insertHost(Host host) {
        getModel().insertHost(host);
        getView().insertItem(host);
    }

    @Override
    public void removeHost(int id, int position) {
        getModel().removeHost(id);
        getView().removeItem(position);
    }

    @Override
    public List<Integer> getHostIds() {
        return getModel().getHostIds();
    }
}
