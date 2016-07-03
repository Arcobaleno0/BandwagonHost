package me.pexcn.bandwagonhost.main.ui;

import android.support.v4.app.Fragment;

/**
 * Created by pexcn on 2016-06-29.
 */
public interface IMainView {
    void setToolbarTitle(String title);

    void setNavCheckedItem(int id);

    void switchFragment(Fragment fragment);

    void showAboutDialog();
}
