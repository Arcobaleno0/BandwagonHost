package me.pexcn.bandwagonhost.feature.migrate.model;

import android.content.Context;

import java.util.List;

import me.pexcn.bandwagonhost.bean.Host;
import me.pexcn.bandwagonhost.database.HostDatabase;

/**
 * Created by pexcn on 2016-07-03.
 */
public class MigrateModel implements IMigrateModel {
    private Context mContext;
    private HostDatabase mDatabase;

    public MigrateModel(Context context) {
        this.mContext = context;
        this.mDatabase = HostDatabase.getInstance(mContext);
    }

    @Override
    public String[] getHostTitle() {
        List<Host> hosts = mDatabase.queryAll();
        String[] titles = new String[hosts.size()];
        for (int i = 0; i < hosts.size(); i++) {
            titles[i] = hosts.get(i).title;
        }
        return titles;
    }
}
