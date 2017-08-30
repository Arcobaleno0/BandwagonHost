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
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.util.ArrayMap;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import me.pexcn.android.utils.Utils;
import me.pexcn.bandwagonhost.app.Constants;
import me.pexcn.bandwagonhost.data.local.bean.Host;

/**
 * Created by pexcn on 2016-06-30.
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {
    private static DBHelper INSTANCE;
    private ArrayMap<String, Dao> mDaos;

    public static DBHelper getHelper() {
        if (INSTANCE == null) {
            INSTANCE = new DBHelper(Utils.getContext());
        }
        return INSTANCE;
    }

    private DBHelper(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        mDaos = new ArrayMap<>();
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Host.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    @SuppressWarnings("unchecked")
    @Override
    public <D extends Dao<T, ?>, T> D getDao(Class<T> clazz) throws SQLException {
        String className = clazz.getSimpleName();
        D dao = (D) mDaos.get(className);
        if (dao == null) {
            dao = super.getDao(clazz);
            mDaos.put(className, dao);
        }
        return dao;
    }

    @Override
    public void close() {
        super.close();

        for (String key : mDaos.keySet()) {
            mDaos.put(key, null);
        }
        mDaos = null;
    }
}
