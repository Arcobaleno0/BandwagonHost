package me.pexcn.bandwagonhost.base.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * Created by pexcn on 2016-07-16.
 */
public abstract class BaseDatabase<T extends SQLiteOpenHelper, O> {
    protected T mHelper;
    protected SQLiteDatabase mDatabase;

    protected BaseDatabase(Context context) {
        mHelper = getDatabaseHelper(context);
        mDatabase = mHelper.getWritableDatabase();
    }

    protected abstract T getDatabaseHelper(Context context);

    protected abstract String getTableName();

    protected abstract String getTableColumnId();

    protected static BaseDatabase mInstance;

    public static BaseDatabase getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new BaseDatabase(context) {
                @Override
                protected SQLiteOpenHelper getDatabaseHelper(Context context) {
                    return getDatabaseHelper(context);
                }

                @Override
                protected String getTableName() {
                    return getTableName();
                }

                @Override
                protected String getTableColumnId() {
                    return getTableColumnId();
                }
            };
        } else {
            mInstance.open();
        }
        return mInstance;
    }

    public void insert(O o) {

    }

    public O query(int id) {
        return null;
    }

    public void remove(int id) {
        mDatabase.delete(getTableName(), getTableColumnId() + " = " + id, null);
    }

    public List<O> queryAll() {
        return null;
    }

    public void update(O o) {

    }

    public void open() {
        mHelper.getWritableDatabase();
    }

    public void close() {
        mDatabase.close();
    }

    public boolean isOpen() {
        return mDatabase.isOpen();
    }

    public boolean isEmpty() {
        boolean isEmpty = true;
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + getTableName(), null);
        if (cursor.moveToFirst()) {
            isEmpty = false;
        }
        cursor.close();
        return isEmpty;
    }
}
