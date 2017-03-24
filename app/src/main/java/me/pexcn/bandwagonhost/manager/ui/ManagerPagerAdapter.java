package me.pexcn.bandwagonhost.manager.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.ArrayMap;

/**
 * Created by pexcn on 2017-03-24.
 */
public class ManagerPagerAdapter extends FragmentPagerAdapter {
    private ArrayMap<String, Fragment> mMap = new ArrayMap<>();
//    private SimpleArrayMap<String, Fragment> mMap = new SimpleArrayMap<>();

    public ManagerPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mMap.get(mMap.keyAt(position));
    }

    @Override
    public int getCount() {
        return mMap.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mMap.keyAt(position);
    }

    public void addFragment(String key, Fragment fragment) {
        mMap.put(key, fragment);
    }
}
