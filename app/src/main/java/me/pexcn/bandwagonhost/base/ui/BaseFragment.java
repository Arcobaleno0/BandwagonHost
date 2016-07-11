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

package me.pexcn.bandwagonhost.base.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.pexcn.bandwagonhost.base.presenter.IBasePresenter;

/**
 * Created by pexcn on 2016-06-29.
 */
public abstract class BaseFragment<P extends IBasePresenter> extends Fragment {
    protected Activity mActivity;
    protected P mPresenter;

    // TODO: 抽取getArgs
    // TODO: initData位置

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        mPresenter = getPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView(view, savedInstanceState);
        initData();
    }

    // TODO: WeakReference
    @Override
    public void onDestroyView() {
        mPresenter.destroy();
        mPresenter = null;
        super.onDestroyView();
    }

    abstract protected int getLayoutId();

    abstract protected P getPresenter();

    abstract protected void initView(View view, @Nullable Bundle savedInstanceState);

    abstract protected void initData();

    // TODO: 移动到接口
    abstract protected void showTips(String msg, int duration);
}
