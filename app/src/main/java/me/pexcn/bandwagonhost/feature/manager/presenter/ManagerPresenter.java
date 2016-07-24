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
 *
 */

package me.pexcn.bandwagonhost.feature.manager.presenter;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import java.util.List;

import me.pexcn.bandwagonhost.base.presenter.BasePresenter;
import me.pexcn.bandwagonhost.bean.Profile;
import me.pexcn.bandwagonhost.feature.manager.model.IManagerModel;
import me.pexcn.bandwagonhost.feature.manager.model.ManagerModel;
import me.pexcn.bandwagonhost.feature.manager.ui.IManagerView;

/**
 * Created by pexcn on 2016-06-30.
 */
public class ManagerPresenter extends BasePresenter<IManagerView, IManagerModel> implements IManagerPresenter {
    public ManagerPresenter(IManagerView view) {
        super(view);
    }

    @Override
    protected IManagerModel getModel() {
        return new ManagerModel(((Fragment) mView).getActivity());
    }

    @Override
    public void prepare() {
        if (mModel.isProfileEmpty()) {
            mView.showTips("无数据\n" + "请先点击右下角的按钮添加主机", Snackbar.LENGTH_INDEFINITE);
        } else {
            mView.showProfileList(mModel.getProfileList());
        }
    }

    @Override
    public void insertProfile(Profile profile) {
        mModel.insertProfile(profile);
        mView.insertItem(profile);
        mView.showTips(profile.title + " " + "添加成功", Snackbar.LENGTH_LONG);
    }

    @Override
    public void removeProfile(int id, int position) {
        mModel.removeProfile(id);
        mView.removeItem(position);
    }

    @Override
    public List<Integer> getProfileIds() {
        return mModel.getProfileIds();
    }
}
