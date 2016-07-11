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

package me.pexcn.bandwagonhost.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import me.pexcn.bandwagonhost.Constant;
import me.pexcn.bandwagonhost.bean.Host;

/**
 * Created by pexcn on 2016-06-30.
 */
public class HostDatabase implements IDatabase<Host, Integer> {
    private HostDatabaseHelper mHelper;
    private SQLiteDatabase mDatabase;

    // TODO: 重构数据库

    private HostDatabase(Context context) {
        mHelper = new HostDatabaseHelper(context);
        mDatabase = mHelper.getWritableDatabase();
    }

    private static HostDatabase database;

    public static HostDatabase getInstance(Context context) {
        if (database == null) {
            database = new HostDatabase(context);
        } else {
            database.open();
        }
        return database;
    }

    @Override
    public void insert(Host host) {
        ContentValues values = new ContentValues();
        values.put(Constant.HOST_TABLE_TITLE, host.title);
        values.put(Constant.HOST_TABLE_VEID, host.veid);
        values.put(Constant.HOST_TABLE_KEY, host.key);
        mDatabase.insert(Constant.HOST_TABLE_NAME, null, values);
    }

    @Override
    public void remove(int id) {
        mDatabase.delete(Constant.HOST_TABLE_NAME, Constant.HOST_TABLE_ID + " = " + id, null);
    }

    @Override
    public Host query(int id) {

        return null;
    }

    @Override
    public List<Host> queryAll() {
        ArrayList<Host> hosts = new ArrayList<>();
        String[] columns = {
                Constant.HOST_TABLE_ID,
                Constant.HOST_TABLE_TITLE,
                Constant.HOST_TABLE_VEID,
                Constant.HOST_TABLE_KEY
        };
        Cursor cursor = mDatabase.query(Constant.HOST_TABLE_NAME, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Host host = new Host();
            host._id = cursor.getInt(cursor.getColumnIndex(Constant.HOST_TABLE_ID));
            host.title = cursor.getString(cursor.getColumnIndex(Constant.HOST_TABLE_TITLE));
            host.veid = cursor.getString(cursor.getColumnIndex(Constant.HOST_TABLE_VEID));
            host.key = cursor.getString(cursor.getColumnIndex(Constant.HOST_TABLE_KEY));
            hosts.add(host);
        }
        cursor.close();
        return hosts;
    }

    @Override
    public List<Integer> queryAll(String field) {
        ArrayList<Integer> fields = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("SELECT " + field + " FROM " + Constant.HOST_TABLE_NAME, null);
        while (cursor.moveToNext()) {
            fields.add(cursor.getInt(cursor.getColumnIndex(Constant.HOST_TABLE_ID)));
        }
        cursor.close();
        return fields;
    }

    @Override
    public void update(Host host) {
        ContentValues values = new ContentValues();
        values.put(Constant.HOST_TABLE_TITLE, host.title);
        values.put(Constant.HOST_TABLE_VEID, host.veid);
        values.put(Constant.HOST_TABLE_KEY, host.key);
        mDatabase.update(Constant.HOST_TABLE_NAME, values, Constant.HOST_TABLE_ID + "=" + host._id, null);
    }

    @Override
    public void open() {
        mDatabase = mHelper.getWritableDatabase();
    }

    @Override
    public void close() {
        mDatabase.close();
    }

    @Override
    public boolean isOpen() {
        return mDatabase.isOpen();
    }

    @Override
    public boolean isEmpty() {
        boolean isEmpty = true;
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + Constant.HOST_TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            isEmpty = false;
        }
        cursor.close();
        return isEmpty;
    }
}
