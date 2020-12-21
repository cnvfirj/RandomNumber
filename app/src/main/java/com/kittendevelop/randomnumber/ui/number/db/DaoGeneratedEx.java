package com.kittendevelop.randomnumber.ui.number.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoGeneratedEx {

    @Insert
    void insert(EntityGeneratedEx ex);

    @Delete
    void delete(EntityGeneratedEx ex);

    @Query("SELECT * FROM GENERATED_EX")
    List<EntityGeneratedEx> all();

    @Query("SELECT number, value FROM BaseEntityItems")
    List<SelectEx> allValues();

    @Query("SELECT * FROM GENERATED_EX WHERE source = :source")
    List<EntityGeneratedEx>source(int source);

    @Query("SELECT * FROM GENERATED_EX WHERE year = :year")
    List<EntityGeneratedEx>year(int year);

    @Query("SELECT * FROM GENERATED_EX WHERE month = :month")
    List<EntityGeneratedEx>month(int month);

    @Query("SELECT * FROM GENERATED_EX WHERE day = :day")
    List<EntityGeneratedEx>day(int day);

    @Query("SELECT * FROM GENERATED_EX WHERE value = :value")
    EntityGeneratedEx value(String value);

    @Query("SELECT * FROM GENERATED_EX WHERE id = :id")
    EntityGeneratedEx id(long id);

}
