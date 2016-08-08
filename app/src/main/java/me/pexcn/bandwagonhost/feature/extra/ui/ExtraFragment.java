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

package me.pexcn.bandwagonhost.feature.extra.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.base.ui.BaseFragment;
import me.pexcn.bandwagonhost.feature.extra.presenter.ExtraPresenter;
import me.pexcn.bandwagonhost.feature.extra.presenter.IExtraPresenter;

/**
 * Created by pexcn on 2016-06-29.
 */
public class ExtraFragment extends BaseFragment<IExtraPresenter> implements IExtraView {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_extra;
    }

    @Override
    protected IExtraPresenter getPresenter() {
        return new ExtraPresenter(this);
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void showTips(String msg, int duration) {

    }

    @Override
    public void hideTips() {

    }
}
