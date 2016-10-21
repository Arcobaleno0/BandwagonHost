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

package me.pexcn.bandwagonhost.manager.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.View;

import java.util.List;

import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.bean.database.Host;
import me.pexcn.bandwagonhost.manager.presenter.IManagerPresenter;
import me.pexcn.simpleutils.base.mvp.view.BaseFragment;

/**
 * Created by pexcn on 2016-06-29.
 */
public class ManagerFragment extends BaseFragment<IManagerPresenter> implements IManagerView {

    private TextInputEditText mTitle;
    private TextInputEditText mVeid;
    private TextInputEditText mKey;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    public IManagerPresenter createPresenter() {
        return null;
    }


    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        mPresenter.prepare();
    }

    @Override
    public void insertItem(Host host) {

    }

    @Override
    public void removeItem(int position) {

    }

    @Override
    public void showHostList(List<Host> hosts) {

    }

    @Override
    public void showAddHostDialog() {
        @SuppressLint("InflateParams") View view = getLayoutInflater(null).inflate(R.layout.dialog_add_host, null);
        mTitle = (TextInputEditText) view.findViewById(R.id.et_title);
        mVeid = (TextInputEditText) view.findViewById(R.id.et_veid);
        mKey = (TextInputEditText) view.findViewById(R.id.et_key);
//        mTitle.setFilters(new InputFilter[]{new TextFilter(mTitle)});
//        mVeid.setFilters(new InputFilter[]{new TextFilter(mVeid)});
//        mKey.setFilters(new InputFilter[]{new TextFilter(mKey)});


    }

    @Override
    public void showPopupMenu(View view) {

    }

    private void insertProfile() {
        Host host = new Host();
        host.title = mTitle.getText().toString();
        host.veid = mVeid.getText().toString();
        host.key = mKey.getText().toString();
        if ("".equals(host.title) || "".equals(host.veid) || "".equals(host.key)) {
            if ("".equals(host.title)) {
                mTitle.setError("标题不能为空");
            }
            if ("".equals(host.veid)) {
                mVeid.setError("VEID 不能为空");
            }
            if ("".equals(host.key)) {
                mKey.setError("KEY 不能为空");
            }
        } else {
            mPresenter.insertHost(host);

        }
    }


//
//    @Override
//    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP && !event.isCanceled()) {
//            dialog.cancel();
//            return true;
//        }
//        return false;
//    }
}
