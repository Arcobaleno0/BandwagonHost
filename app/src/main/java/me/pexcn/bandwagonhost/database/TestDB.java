package me.pexcn.bandwagonhost.database;

import android.content.Context;

import me.pexcn.bandwagonhost.Constants;
import me.pexcn.bandwagonhost.base.database.BaseDatabase;
import me.pexcn.bandwagonhost.feature.manager.bean.Profile;

/**
 * Created by pexcn on 2016-07-16.
 */
public class TestDB extends BaseDatabase<ProfileDatabaseHelper, Profile> {
    protected TestDB(Context context) {
        super(context);
    }

    @Override
    protected ProfileDatabaseHelper getDatabaseHelper(Context context) {
        return new ProfileDatabaseHelper(context);
    }

    @Override
    protected String getTableName() {
        return Constants.MANAGER.DATABASE.TABLE_NAME;
    }

    @Override
    protected String getTableColumnId() {
        return Constants.MANAGER.DATABASE.TABLE_COLUMN_ID;
    }
}
