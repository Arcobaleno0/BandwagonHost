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

package me.pexcn.bandwagonhost.feature.hostmanager.model;

import android.content.Context;

import java.util.List;

import me.pexcn.bandwagonhost.Constant;
import me.pexcn.bandwagonhost.bean.Host;
import me.pexcn.bandwagonhost.database.HostDatabase;

/**
 * Created by pexcn on 2016-07-01.
 */
public class HostManagerModel implements IHostManagerModel {
    /**
     * TODO: 优化数据库查询操作
     */

    private Context mContext;
    private HostDatabase mDatabase;

    public HostManagerModel(Context context) {
        this.mContext = context;
        this.mDatabase = HostDatabase.getInstance(mContext);
    }

    @Override
    public boolean isEmpty() {
        return mDatabase.isEmpty();
    }

    @Override
    public void insertHost(Host host) {
        mDatabase.insert(host);
    }

    @Override
    public void removeHost(int id) {
        mDatabase.remove(id);
    }

    @Override
    public List<Host> loadList() {
        return mDatabase.queryAll();
    }

    @Override
    public List<Integer> getIds() {
        return mDatabase.queryAll(Constant.HOST_TABLE_ID);
    }

//    @Override
//    public int[] getHostIds() {
//        int size = mDatabase.queryAll().size();
//        int[] ids = new int[size];
//        for (int i = 0; i < size; i++) {
//            ids[i] = mDatabase.queryAll().get(i)._id;
//        }
//        return ids;
//    }
}
