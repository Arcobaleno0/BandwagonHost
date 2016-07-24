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

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import me.pexcn.bandwagonhost.Constants;

/**
 * Created by pexcn on 2016-06-30.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = Constants.Databases.DATABASE_NAME;
    private static final int DATABASE_VERSION = Constants.Databases.DATABASE_VERSION;
    private static final String TABLE_NAME = Constants.Databases.Profile.TABLE_NAME;
    private static final String TABLE_COLUMN_ID = Constants.Databases.Profile.TABLE_COLUMN_ID;
    private static final String TABLE_COLUMN_TITLE = Constants.Databases.Profile.TABLE_COLUMN_TITLE;
    private static final String TABLE_COLUMN_VEID = Constants.Databases.Profile.TABLE_COLUMN_VEID;
    private static final String TABLE_COLUMN_KEY = Constants.Databases.Profile.TABLE_COLUMN_KEY;

    private static final String CREATE_TABLE =
            "create table if not exists " + TABLE_NAME + "("
                    + TABLE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + TABLE_COLUMN_TITLE + " VARCHAR, "
                    + TABLE_COLUMN_VEID + " VARCHAR, "
                    + TABLE_COLUMN_KEY + " VARCHAR)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
            case 2:
                break;
        }
    }
}
