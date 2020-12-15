package com.kittendevelop.randomnumber.ui.number.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;


@Dao
public interface BaseDao {

    @Insert
    void insert(BaseEntity ex);

    @Delete
    void delete(BaseEntity ex);
}
