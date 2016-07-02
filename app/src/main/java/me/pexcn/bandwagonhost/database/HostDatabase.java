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
public class HostDatabase implements IDatabase<Host> {
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
    public void delete(Host host) {
        mDatabase.delete(Constant.HOST_TABLE_NAME, Constant.HOST_TABLE_ID + "=" + host._id, null);
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
}
