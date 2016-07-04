package me.pexcn.bandwagonhost.database;

import java.util.List;

/**
 * Created by pexcn on 2016-06-30.
 */
public interface IDatabase<T, TT> {
    void insert(T t);

    void remove(int id);

    T query(int id);

    List<T> queryAll();

    List<TT> queryAll(String field);

    void update(T t);

    void open();

    void close();

    boolean isOpen();

    boolean isEmpty();
}
