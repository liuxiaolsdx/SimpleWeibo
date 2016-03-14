package com.weibo.model.dao;

import java.util.List;

/**
 * Created by Sean on 16/3/13.
 */
public interface Dao<T> {
    boolean creat(T t);
    boolean delete(T t);
    boolean update(T t);
    List<T> get(int id);
}
