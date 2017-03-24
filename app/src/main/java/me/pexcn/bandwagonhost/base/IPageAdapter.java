package me.pexcn.bandwagonhost.base;

/**
 * Created by pexcn on 2017-03-24.
 */
public interface IPageAdapter<T> {
    void addPage(String title, T page);

    void removePage(String title);
}
