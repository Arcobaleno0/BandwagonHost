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

package me.pexcn.bandwagonhost.feature.migrate.presenter;

import android.support.v4.app.Fragment;

import me.pexcn.bandwagonhost.base.presenter.BasePresenter;
import me.pexcn.bandwagonhost.feature.migrate.model.IMigrateModel;
import me.pexcn.bandwagonhost.feature.migrate.model.MigrateModel;
import me.pexcn.bandwagonhost.feature.migrate.ui.IMigrateView;

/**
 * Created by pexcn on 2016-06-30.
 */
public class MigratePresenter extends BasePresenter<IMigrateView, IMigrateModel> implements IMigratePresenter {
    public MigratePresenter(IMigrateView view) {
        super(view);
    }

    @Override
    protected IMigrateModel getModel() {
        return new MigrateModel(((Fragment) mView).getActivity());
    }

    @Override
    public void prepare() {
        // mView.showSelectHostDialog(mModel.getProfiletTitle());
    }
}
