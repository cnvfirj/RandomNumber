package com.kittendevelop.randomnumber.ui.number.db;


import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoExItems extends BaseDao{

    @Query("SELECT * FROM EX_ITEMS")
    List<EntityExItems>all();

    @Query("SELECT * FROM EX_ITEMS WHERE id = :id")
    EntityExItems id(long id);
}
