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
 *
 */

package me.pexcn.bandwagonhost.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import me.pexcn.bandwagonhost.Constants;
import me.pexcn.bandwagonhost.base.database.IDatabase;
import me.pexcn.bandwagonhost.feature.manager.bean.Profile;

/**
 * Created by pexcn on 2016-06-30.
 */
public class ProfileDatabase implements IDatabase<Profile> {
    private static final String TABLE_NAME = Constants.MANAGER.DATABASE.TABLE_NAME;
    private static final String TABLE_COLUMN_ID = Constants.MANAGER.DATABASE.TABLE_COLUMN_ID;
    private static final String TABLE_COLUMN_TITLE = Constants.MANAGER.DATABASE.TABLE_COLUMN_TITLE;
    private static final String TABLE_COLUMN_VEID = Constants.MANAGER.DATABASE.TABLE_COLUMN_VEID;
    private static final String TABLE_COLUMN_KEY = Constants.MANAGER.DATABASE.TABLE_COLUMN_KEY;

    private ProfileDatabaseHelper mHelper;
    private SQLiteDatabase mDatabase;

    // TODO: 重构数据库

    private ProfileDatabase(Context context) {
        mHelper = new ProfileDatabaseHelper(context);
        mDatabase = mHelper.getWritableDatabase();
    }

    private static ProfileDatabase mInstance;

    public static ProfileDatabase getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ProfileDatabase(context);
        } else {
            mInstance.open();
        }
        return mInstance;
    }

    @Override
    public void insert(Profile profile) {
        ContentValues values = new ContentValues();
        values.put(TABLE_COLUMN_TITLE, profile.title);
        values.put(TABLE_COLUMN_VEID, profile.veid);
        values.put(TABLE_COLUMN_KEY, profile.key);
        mDatabase.insert(TABLE_NAME, null, values);
    }

    @Override
    public void remove(int id) {
        mDatabase.delete(TABLE_NAME, TABLE_COLUMN_ID + " = " + id, null);
    }

    @Override
    public Profile query(int id) {

        return null;
    }

    @Override
    public List<Profile> queryAll() {
        ArrayList<Profile> profiles = new ArrayList<>();
        String[] columns = {
                TABLE_COLUMN_ID,
                TABLE_COLUMN_TITLE,
                TABLE_COLUMN_VEID,
                TABLE_COLUMN_KEY
        };
        Cursor cursor = mDatabase.query(TABLE_NAME, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Profile profile = new Profile();
            profile._id = cursor.getInt(cursor.getColumnIndex(TABLE_COLUMN_ID));
            profile.title = cursor.getString(cursor.getColumnIndex(TABLE_COLUMN_TITLE));
            profile.veid = cursor.getString(cursor.getColumnIndex(TABLE_COLUMN_VEID));
            profile.key = cursor.getString(cursor.getColumnIndex(TABLE_COLUMN_KEY));
            profiles.add(profile);
        }
        cursor.close();
        return profiles;
    }

    @Override
    public List<Integer> queryAll(String field) {
        ArrayList<Integer> fields = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("SELECT " + field + " FROM " + TABLE_NAME, null);
        while (cursor.moveToNext()) {
            fields.add(cursor.getInt(cursor.getColumnIndex(TABLE_COLUMN_ID)));
        }
        cursor.close();
        return fields;
    }

    @Override
    public void update(Profile profile) {
        ContentValues values = new ContentValues();
        values.put(TABLE_COLUMN_TITLE, profile.title);
        values.put(TABLE_COLUMN_VEID, profile.veid);
        values.put(TABLE_COLUMN_KEY, profile.key);
        mDatabase.update(TABLE_NAME, values, TABLE_COLUMN_ID + "=" + profile._id, null);
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
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            isEmpty = false;
        }
        cursor.close();
        return isEmpty;
    }
}
