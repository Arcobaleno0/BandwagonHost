package me.pexcn.bandwagonhost.manager.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.ArrayMap;
import android.view.ViewGroup;

import me.pexcn.bandwagonhost.base.IPageAdapter;
import me.pexcn.simpleutils.common.LogUtils;

/**
 * Created by pexcn on 2017-03-24.
 */
public class ManagerPagerAdapter extends FragmentPagerAdapter implements IPageAdapter<Fragment> {
    private ArrayMap<String, Fragment> mMap;
    private static ManagerPagerAdapter INSTANCE;

    private ManagerPagerAdapter(FragmentManager fm) {
        super(fm);
        mMap = new ArrayMap<>();
    }

    public static ManagerPagerAdapter getInstance(FragmentManager fm) {
        if (INSTANCE == null) {
            INSTANCE = new ManagerPagerAdapter(fm);
        }
        return INSTANCE;
    }

    @Override
    public Fragment getItem(int position) {
        return mMap == null ? null : mMap.valueAt(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mMap == null ? null : mMap.keyAt(position);
    }

    @Override
    public int getCount() {
        return mMap == null ? 0 : mMap.size();
    }

    @Override
    public void addPage(String title, Fragment page) {
        mMap.put(title, page);
    }

    @Override
    public void removePage(String title) {
        mMap.remove(title);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        try {
            super.finishUpdate(container);
        } catch (NullPointerException e) {
            LogUtils.d("Dirty fix: That's the support library issue");
        }
    }
}
