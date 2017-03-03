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

package me.pexcn.bandwagonhost.data.local;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by pexcn on 2016-08-09.
 */
public class HostManager {
    private Dao<Host, Integer> mDao;

    private static HostManager INSTANCE;

    public static HostManager getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new HostManager(context);
        }
        return INSTANCE;
    }

    private HostManager(Context context) {
        HostDbHelper helper = HostDbHelper.getInstance(context);
        try {
            mDao = helper.getDao(Host.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(Host host) {
        try {
            // TODO: 返回值
            mDao.create(host);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            mDao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Host host) {
        try {
            mDao.update(host);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Host query(int id) {
        try {
            return mDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Host> queryAll() {
        try {
            return mDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
