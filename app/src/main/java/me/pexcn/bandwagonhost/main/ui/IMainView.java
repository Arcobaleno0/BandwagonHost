package me.pexcn.bandwagonhost.main.ui;

import android.support.v4.app.Fragment;

/**
 * Created by pexcn on 2016-06-29.
 */
public interface IMainView {
    void switchToFragment(Fragment fragment, String title, int item);

    void showAboutDialog();
}
