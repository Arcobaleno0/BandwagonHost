package me.pexcn.bandwagonhost.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import me.pexcn.bandwagonhost.Constant;

/**
 * Created by pexcn on 2016-06-30.
 */
public class HostDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = Constant.HOST_DATABASE_NAME;
    private static final int DATABASE_VERSION = Constant.HOST_DATABASE_VERSION;
    private static final String TABLE_NAME = Constant.HOST_TABLE_NAME;

    private static final String TABLE_COLUMN_ID = Constant.HOST_TABLE_ID;
    private static final String TABLE_COLUMN_TITLE = Constant.HOST_TABLE_TITLE;
    private static final String TABLE_COLUMN_VEID = Constant.HOST_TABLE_VEID;
    private static final String TABLE_COLUMN_KEY = Constant.HOST_TABLE_KEY;

    private static final String CREATE_TABLE =
            "create table if not exists " + TABLE_NAME + "("
                    + TABLE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + TABLE_COLUMN_TITLE + " VARCHAR, "
                    + TABLE_COLUMN_VEID + " VARCHAR, "
                    + TABLE_COLUMN_KEY + " VARCHAR)";

    public HostDatabaseHelper(Context context) {
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
                // TODO
                break;
        }
    }
}
