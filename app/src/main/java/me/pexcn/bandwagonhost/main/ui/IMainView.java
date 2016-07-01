package me.pexcn.bandwagonhost.main.ui;

import android.support.v4.app.Fragment;

import me.pexcn.bandwagonhost.base.ui.IBaseView;

/**
 * Created by pexcn on 2016-06-29.
 */
public interface IMainView extends IBaseView {
    void switchToFragment(Fragment fragment, String title);

    void showAddHostDialog();

    // TODO
    // void showCancelDialog();

    void showAboutDialog();
}
