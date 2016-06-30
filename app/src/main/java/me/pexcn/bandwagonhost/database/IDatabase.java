package me.pexcn.bandwagonhost.database;

import java.util.List;

/**
 * Created by pexcn on 2016-06-30.
 */
public interface IDatabase<T> {
    void add(T t);

    void delete(T t);

    T query(int id);

    List<T> queryAll();

    void modify(T t);
}
