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

package me.pexcn.bandwagonhost.database;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import me.pexcn.bandwagonhost.Debugable;

/**
 * Created by pexcn on 2016-08-09.
 */
public class HostManager implements Debugable {
    private Dao<Host, Integer> mDao;

    private static HostManager sInstance;

    public static HostManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new HostManager(context);
        }
        return sInstance;
    }

    private HostManager(Context context) {
        DBHelper helper = DBHelper.getInstance(context);
        try {
            mDao = helper.getDao(Host.class);
        } catch (SQLException e) {
            if (DEBUG) {
                e.printStackTrace();
            }
        }
    }

    public void add(Host host) {
        try {
            mDao.create(host);
        } catch (SQLException e) {
            if (DEBUG) {
                e.printStackTrace();
            }
        }
    }

    public void delete(int id) {
        try {
            mDao.deleteById(id);
        } catch (SQLException e) {
            if (DEBUG) {
                e.printStackTrace();
            }
        }
    }

    public void update(Host host) {
        try {
            mDao.update(host);
        } catch (SQLException e) {
            if (DEBUG) {
                e.printStackTrace();
            }
        }
    }

    public Host query(int id) {
        try {
            return mDao.queryForId(id);
        } catch (SQLException e) {
            if (DEBUG) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Host> queryAll() {
        try {
            return mDao.queryForAll();
        } catch (SQLException e) {
            if (DEBUG) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
