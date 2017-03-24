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

package me.pexcn.bandwagonhost.manager.mvp;

import me.pexcn.android.base.mvp.BaseContract;

/**
 * Created by Administrator on 2017-02-18 0018.
 */
@SuppressWarnings("WeakerAccess")
public class ManagerContract implements BaseContract {
    interface View extends BaseContract.View<ManagerContract.Presenter> {

    }

    interface Presenter extends BaseContract.Presenter<ManagerContract.View, ManagerContract.Model> {

    }

    interface Model extends BaseContract.Model {

    }
}
