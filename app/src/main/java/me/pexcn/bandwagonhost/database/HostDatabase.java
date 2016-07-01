package me.pexcn.bandwagonhost.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import me.pexcn.bandwagonhost.bean.Host;

/**
 * Created by pexcn on 2016-06-30.
 */
public class HostDatabase implements IDatabase<Host> {
    private HostDatabaseHelper mHelper;
    private SQLiteDatabase mDatabase;

    private HostDatabase(Context context) {
        mHelper = new HostDatabaseHelper(context);
        mDatabase = mHelper.getWritableDatabase();
    }

    private static HostDatabase database;

    public static HostDatabase getInstance(Context context) {
        if (database == null) {
            database = new HostDatabase(context);
        }
        return database;
    }

    @Override
    public void insert(Host host) {
        ContentValues values = new ContentValues();
        values.put(HostDatabaseHelper.TABLE_COLUMN_TITLE, host.getTitle());
        values.put(HostDatabaseHelper.TABLE_COLUMN_VEID, host.getVeid());
        values.put(HostDatabaseHelper.TABLE_COLUMN_KEY, host.getKey());
        mDatabase.insert(HostDatabaseHelper.TABLE_NAME, null, values);
    }

    @Override
    public void delete(Host host) {
        mDatabase.delete(HostDatabaseHelper.TABLE_NAME, HostDatabaseHelper.TABLE_COLUMN_ID + "=" + host.getId(), null);
    }

    @Override
    public Host query(int id) {
        // TODO
        return null;
    }

    @Override
    public List<Host> queryAll() {
        ArrayList<Host> hosts = new ArrayList<>();
        String[] columns = {
                HostDatabaseHelper.TABLE_COLUMN_ID,
                HostDatabaseHelper.TABLE_COLUMN_TITLE,
                HostDatabaseHelper.TABLE_COLUMN_VEID,
                HostDatabaseHelper.TABLE_COLUMN_KEY
        };
        Cursor cursor = mDatabase.query(HostDatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Host host = new Host();
            host.setId(cursor.getInt(cursor.getColumnIndex(HostDatabaseHelper.TABLE_COLUMN_ID)));
            host.setTitle(cursor.getString(cursor.getColumnIndex(HostDatabaseHelper.TABLE_COLUMN_TITLE)));
            host.setVeid(cursor.getString(cursor.getColumnIndex(HostDatabaseHelper.TABLE_COLUMN_VEID)));
            host.setKey(cursor.getString(cursor.getColumnIndex(HostDatabaseHelper.TABLE_COLUMN_KEY)));
            hosts.add(host);
        }
        cursor.close();
        return hosts;
    }

    @Override
    public void update(Host host) {
        ContentValues values = new ContentValues();
        values.put(HostDatabaseHelper.TABLE_COLUMN_TITLE, host.getTitle());
        values.put(HostDatabaseHelper.TABLE_COLUMN_VEID, host.getVeid());
        values.put(HostDatabaseHelper.TABLE_COLUMN_KEY, host.getKey());
        mDatabase.update(HostDatabaseHelper.TABLE_NAME, values, HostDatabaseHelper.TABLE_COLUMN_ID + "=" + host.getId(), null);
    }

    @Override
    public void close() {
        mDatabase.close();
    }
}
