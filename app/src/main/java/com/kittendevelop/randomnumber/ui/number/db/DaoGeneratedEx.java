package com.kittendevelop.randomnumber.ui.number.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
public interface DaoGeneratedEx {

    @Insert
    void insert(EntityGeneratedEx ex);

    @Delete
    void delete(EntityGeneratedEx ex);

    @Query("SELECT COUNT(id) FROM GENERATED_EX")
    int count();

    @Query("SELECT * FROM GENERATED_EX")
    List<EntityGeneratedEx> all();

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

    @Query("SELECT id, number, value FROM GENERATED_EX")
    List<CommonValues>commonValues();

    @Query("SELECT * FROM GENERATED_EX")
    Flowable<List<EntityGeneratedEx>>allRx();

    @Query("SELECT * FROM GENERATED_EX WHERE id = :id")
    Flowable<EntityGeneratedEx>idRx(long id);




}
