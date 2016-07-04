package me.pexcn.bandwagonhost.feature.hostmanager.ui;

/**
 * Created by pexcn on 2016-06-29.
 */
public interface IHostManagerView {
    void notifyItemInserted(int position);

    void showInsertHostDialog();

    void setSwipeRemoveItem();

    void showTips(String msg, int duration);
}
