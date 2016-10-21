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

import me.pexcn.bandwagonhost.bean.database.Host;
import me.pexcn.bandwagonhost.main.model.IMainModel;
import me.pexcn.bandwagonhost.main.ui.IMainView;
import me.pexcn.simpleutils.base.mvp.presenter.IBasePresenter;

/**
 * Created by pexcn on 2016-06-29.
 */
public interface IMainPresenter extends IBasePresenter<IMainView, IMainModel> {
    void prepare();

    void addHost(Host host);

    void deleteHost(int id);

    void updateHost(Host host);
}
