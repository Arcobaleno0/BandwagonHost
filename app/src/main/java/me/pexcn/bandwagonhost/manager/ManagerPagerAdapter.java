package me.pexcn.bandwagonhost.manager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.ArrayMap;
import android.view.ViewGroup;

import me.pexcn.android.utils.io.LogUtils;

/**
 * Created by pexcn on 2017-03-24.
 */
@SuppressWarnings("WeakerAccess")
public class ManagerPagerAdapter extends FragmentPagerAdapter {
    private ArrayMap<String, Fragment> mMap;

    public ManagerPagerAdapter(FragmentManager fm) {
        super(fm);
        mMap = new ArrayMap<>();
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

    public void addPage(String title, Fragment page) {
        mMap.put(title, page);
    }

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
