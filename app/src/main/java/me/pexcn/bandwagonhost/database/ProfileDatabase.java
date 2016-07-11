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
import me.pexcn.bandwagonhost.bean.Profile;

/**
 * Created by pexcn on 2016-06-30.
 */
public class ProfileDatabase implements IDatabase<Profile, Integer> {
    private ProfileDatabaseHelper mHelper;
    private SQLiteDatabase mDatabase;

    // TODO: 重构数据库

    private ProfileDatabase(Context context) {
        mHelper = new ProfileDatabaseHelper(context);
        mDatabase = mHelper.getWritableDatabase();
    }

    private static ProfileDatabase database;

    public static ProfileDatabase getInstance(Context context) {
        if (database == null) {
            database = new ProfileDatabase(context);
        } else {
            database.open();
        }
        return database;
    }

    @Override
    public void insert(Profile profile) {
        ContentValues values = new ContentValues();
        values.put(Constant.HOST_TABLE_TITLE, profile.title);
        values.put(Constant.HOST_TABLE_VEID, profile.veid);
        values.put(Constant.HOST_TABLE_KEY, profile.key);
        mDatabase.insert(Constant.HOST_TABLE_NAME, null, values);
    }

    @Override
    public void remove(int id) {
        mDatabase.delete(Constant.HOST_TABLE_NAME, Constant.HOST_TABLE_ID + " = " + id, null);
    }

    @Override
    public Profile query(int id) {

        return null;
    }

    @Override
    public List<Profile> queryAll() {
        ArrayList<Profile> profiles = new ArrayList<>();
        String[] columns = {
                Constant.HOST_TABLE_ID,
                Constant.HOST_TABLE_TITLE,
                Constant.HOST_TABLE_VEID,
                Constant.HOST_TABLE_KEY
        };
        Cursor cursor = mDatabase.query(Constant.HOST_TABLE_NAME, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Profile profile = new Profile();
            profile._id = cursor.getInt(cursor.getColumnIndex(Constant.HOST_TABLE_ID));
            profile.title = cursor.getString(cursor.getColumnIndex(Constant.HOST_TABLE_TITLE));
            profile.veid = cursor.getString(cursor.getColumnIndex(Constant.HOST_TABLE_VEID));
            profile.key = cursor.getString(cursor.getColumnIndex(Constant.HOST_TABLE_KEY));
            profiles.add(profile);
        }
        cursor.close();
        return profiles;
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
    public void update(Profile profile) {
        ContentValues values = new ContentValues();
        values.put(Constant.HOST_TABLE_TITLE, profile.title);
        values.put(Constant.HOST_TABLE_VEID, profile.veid);
        values.put(Constant.HOST_TABLE_KEY, profile.key);
        mDatabase.update(Constant.HOST_TABLE_NAME, values, Constant.HOST_TABLE_ID + "=" + profile._id, null);
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
