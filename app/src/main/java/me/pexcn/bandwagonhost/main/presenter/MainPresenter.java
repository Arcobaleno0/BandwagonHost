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

import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.base.presenter.BasePresenter;
import me.pexcn.bandwagonhost.feature.extra.ui.ExtraFragment;
import me.pexcn.bandwagonhost.feature.manager.ui.ManagerFragment;
import me.pexcn.bandwagonhost.feature.migrate.ui.MigrateFragment;
import me.pexcn.bandwagonhost.main.ui.IMainView;

/**
 * Created by pexcn on 2016-06-29.
 */
public class MainPresenter extends BasePresenter<IMainView, Object> implements IMainPresenter {
    public MainPresenter(IMainView view) {
        super(view);
    }

    @Override
    protected Object getModel() {
        return null;
    }

    @Override
    public void prepare() {
        mView.setToolbarTitle("主机管理");
        mView.setNavCheckedItem(R.id.nav_manager);
        mView.switchFragment(new ManagerFragment());
    }

    @Override
    public void switchModule(int id) {
        switch (id) {
            case R.id.nav_manager:
                mView.setToolbarTitle("主机管理");
                mView.setNavCheckedItem(id);
                mView.switchFragment(new ManagerFragment());
                break;
            case R.id.nav_migrate:
                mView.setToolbarTitle("切换机房");
                mView.setNavCheckedItem(id);
                mView.switchFragment(new MigrateFragment());
                break;
            case R.id.nav_extra:
                mView.setToolbarTitle("额外功能");
                mView.setNavCheckedItem(id);
                mView.switchFragment(new ExtraFragment());
                break;
        }
    }
}
