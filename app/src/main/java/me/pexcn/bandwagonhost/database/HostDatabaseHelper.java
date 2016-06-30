package me.pexcn.bandwagonhost.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pexcn on 2016-06-30.
 */
public class HostDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "host.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "host";

    public static final String TABLE_COLUMN_ID = "_id";
    public static final String TABLE_COLUMN_TITLE = "title";
    public static final String TABLE_COLUMN_VEID = "veid";
    public static final String TABLE_COLUMN_KEY = "key";

    public static final String CREATE_TABLE =
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
