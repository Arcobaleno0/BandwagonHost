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

package me.pexcn.bandwagonhost.feature.profile.presenter;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import java.util.List;

import me.pexcn.bandwagonhost.base.presenter.BasePresenter;
import me.pexcn.bandwagonhost.bean.Profile;
import me.pexcn.bandwagonhost.feature.profile.model.ProfileModel;
import me.pexcn.bandwagonhost.feature.profile.model.IProfileModel;
import me.pexcn.bandwagonhost.feature.profile.ui.IProfileView;

/**
 * Created by pexcn on 2016-06-30.
 */
public class ProfilePresenter extends BasePresenter<IProfileView, IProfileModel> implements IProfilePresenter {
    public ProfilePresenter(IProfileView view) {
        super(view);
    }

    @Override
    protected IProfileModel getModel() {
        return new ProfileModel(((Fragment) mView).getActivity());
    }

    @Override
    public void prepare() {
        if (mModel.isEmpty()) {
            mView.showTips("无数据\n" + "点击右下角的 + 号以添加主机", Snackbar.LENGTH_INDEFINITE);
        } else {
            mView.showList(mModel.getProfileList());
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
    public List<Integer> getIds() {
        return mModel.getIds();
    }
}
