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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import me.pexcn.bandwagonhost.Constants;

/**
 * Created by pexcn on 2016-06-30.
 */
public class DatabaseManager {

    /**
     * TODO: 重构数据库
     */

    private static final String TABLE_NAME = Constants.HOST_TABLE_NAME;
    private static final String TABLE_COLUMN_ID = Constants.HOST_TABLE_COLUMN_ID;
    private static final String TABLE_COLUMN_TITLE = Constants.HOST_TABLE_COLUMN_TITLE;
    private static final String TABLE_COLUMN_VEID = Constants.HOST_TABLE_COLUMN_VEID;
    private static final String TABLE_COLUMN_KEY = Constants.HOST_TABLE_COLUMN_KEY;

    private DatabaseHelper mHelper;
    private SQLiteDatabase mDatabase;
    private static DatabaseManager mInstance;

    private DatabaseManager(Context context) {
        mHelper = new DatabaseHelper(context);
        mDatabase = mHelper.getWritableDatabase();
    }

    public static DatabaseManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DatabaseManager(context);
        } else {
            mInstance.open();
        }
        return mInstance;
    }

    public void insert(me.pexcn.bandwagonhost.bean.Host host) {
        ContentValues values = new ContentValues();
        values.put(TABLE_COLUMN_TITLE, host.title);
        values.put(TABLE_COLUMN_VEID, host.veid);
        values.put(TABLE_COLUMN_KEY, host.key);
        mDatabase.insert(TABLE_NAME, null, values);
    }

    public void remove(int id) {
        mDatabase.delete(TABLE_NAME, TABLE_COLUMN_ID + " = " + id, null);
    }

    public me.pexcn.bandwagonhost.bean.Host query(int id) {

        return null;
    }

    public List<me.pexcn.bandwagonhost.bean.Host> queryAll() {
        ArrayList<me.pexcn.bandwagonhost.bean.Host> hosts = new ArrayList<>();
        String[] columns = {
                TABLE_COLUMN_ID,
                TABLE_COLUMN_TITLE,
                TABLE_COLUMN_VEID,
                TABLE_COLUMN_KEY
        };
        Cursor cursor = mDatabase.query(TABLE_NAME, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {
            me.pexcn.bandwagonhost.bean.Host host = new me.pexcn.bandwagonhost.bean.Host();
            host._id = cursor.getInt(cursor.getColumnIndex(TABLE_COLUMN_ID));
            host.title = cursor.getString(cursor.getColumnIndex(TABLE_COLUMN_TITLE));
            host.veid = cursor.getString(cursor.getColumnIndex(TABLE_COLUMN_VEID));
            host.key = cursor.getString(cursor.getColumnIndex(TABLE_COLUMN_KEY));
            hosts.add(host);
        }
        cursor.close();
        return hosts;
    }

    public List<Integer> queryAll(String field) {
        ArrayList<Integer> fields = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("SELECT " + field + " FROM " + TABLE_NAME, null);
        while (cursor.moveToNext()) {
            fields.add(cursor.getInt(cursor.getColumnIndex(TABLE_COLUMN_ID)));
        }
        cursor.close();
        return fields;
    }

    public void update(me.pexcn.bandwagonhost.bean.Host host) {
        ContentValues values = new ContentValues();
        values.put(TABLE_COLUMN_TITLE, host.title);
        values.put(TABLE_COLUMN_VEID, host.veid);
        values.put(TABLE_COLUMN_KEY, host.key);
        mDatabase.update(TABLE_NAME, values, TABLE_COLUMN_ID + "=" + host._id, null);
    }

    public void open() {
        mDatabase = mHelper.getWritableDatabase();
    }

    public void close() {
        mDatabase.close();
    }

    public boolean isOpen() {
        return mDatabase.isOpen();
    }

    public boolean isEmpty() {
        boolean isEmpty = true;
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            isEmpty = false;
        }
        cursor.close();
        return isEmpty;
    }
}
