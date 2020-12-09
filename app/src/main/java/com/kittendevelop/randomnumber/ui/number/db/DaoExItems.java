package com.kittendevelop.randomnumber.ui.number.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoExItems {
    @Insert
    void insert(EntityExItems ex);
//
//    @Update
//    void update(EntityGeneratedItem perm);

    @Delete
    void delete(EntityExItems ex);

    @Query("SELECT * FROM EX_ITEMS")
    List<EntityExItems>all();

    @Query("SELECT * FROM EX_ITEMS WHERE id = :id")
    EntityExItems get(long id);
}
