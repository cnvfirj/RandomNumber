package com.kittendevelop.randomnumber.ui.number.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoGeneratedItem {
    @Insert
    void insert(EntityGeneratedItem perm);
//
//    @Update
//    void update(EntityGeneratedItem perm);

    @Delete
    void delete(EntityGeneratedItem perm);

    @Query("SELECT * FROM GENERATED_ITEM")
    List<EntityGeneratedItem>all();

    @Query("SELECT * FROM GENERATED_ITEM WHERE source = :source")
    List<EntityGeneratedItem>source(int source);

    @Query("SELECT * FROM GENERATED_ITEM WHERE year = :year")
    List<EntityGeneratedItem>year(int year);

    @Query("SELECT * FROM GENERATED_ITEM WHERE month = :month")
    List<EntityGeneratedItem>month(int month);

    @Query("SELECT * FROM GENERATED_ITEM WHERE day = :day")
    List<EntityGeneratedItem>day(int day);

    @Query("SELECT * FROM GENERATED_ITEM WHERE `from` LIKE :from OR `to` LIKE :to")
    List<EntityGeneratedItem>confines(long from, long to);

    @Query("SELECT * FROM GENERATED_ITEM WHERE value = :value")
    EntityGeneratedItem value(String value);

    @Query("SELECT * FROM GENERATED_ITEM WHERE id = :id")
    EntityGeneratedItem id(long id);







}
