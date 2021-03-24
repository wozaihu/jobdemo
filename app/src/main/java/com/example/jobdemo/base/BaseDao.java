package com.example.jobdemo.base;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import java.util.List;

public interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(T item);

    @Insert
    void insertItems(List<T> items);

    @Delete
    void delete(T item);

    @Delete
    void deleteItems(List<T> items);
}
