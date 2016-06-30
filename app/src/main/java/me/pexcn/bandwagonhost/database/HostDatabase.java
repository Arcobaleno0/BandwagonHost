package me.pexcn.bandwagonhost.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
    public void add(Host host) {

    }

    @Override
    public void delete(Host host) {

    }

    @Override
    public Host query(int id) {
        return null;
    }

    @Override
    public List<Host> queryAll() {
        return null;
    }

    @Override
    public void modify(Host host) {

    }
}
