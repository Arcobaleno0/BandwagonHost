package me.pexcn.bandwagonhost.feature.hostmanager.model;

import android.content.Context;

import java.util.List;

import me.pexcn.bandwagonhost.Constant;
import me.pexcn.bandwagonhost.bean.Host;
import me.pexcn.bandwagonhost.database.HostDatabase;

/**
 * Created by pexcn on 2016-07-01.
 */
public class HostManagerModel implements IHostManagerModel {
    /**
     * TODO: 优化数据库查询操作
     */

    private Context mContext;
    private HostDatabase mDatabase;

    public HostManagerModel(Context context) {
        this.mContext = context;
        this.mDatabase = HostDatabase.getInstance(mContext);
    }

    @Override
    public boolean isEmpty() {
        return mDatabase.isEmpty();
    }

    @Override
    public void insertHost(Host host) {
        mDatabase.insert(host);
    }

    @Override
    public void removeHost(int id) {
        mDatabase.remove(id);
    }

    @Override
    public List<Host> loadList() {
        return mDatabase.queryAll();
    }

    @Override
    public List<Integer> getIds() {
        return mDatabase.queryAll(Constant.HOST_TABLE_ID);
    }

//    @Override
//    public int[] getHostIds() {
//        int size = mDatabase.queryAll().size();
//        int[] ids = new int[size];
//        for (int i = 0; i < size; i++) {
//            ids[i] = mDatabase.queryAll().get(i)._id;
//        }
//        return ids;
//    }
}
