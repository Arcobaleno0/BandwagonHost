package me.pexcn.bandwagonhost.main.model;

import android.content.Context;

import me.pexcn.bandwagonhost.database.HostDatabase;

/**
 * Created by pexcn on 2016-07-01.
 */
public class MainModel implements IMainModel {
    private Context mContext;

    public MainModel(Context context) {
        this.mContext = context;
    }

    @Override
    public boolean hasHostData() {
        return !HostDatabase.getInstance(mContext).queryAll().isEmpty();
    }
}
