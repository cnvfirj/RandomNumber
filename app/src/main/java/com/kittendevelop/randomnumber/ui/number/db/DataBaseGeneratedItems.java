package com.kittendevelop.randomnumber.ui.number.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {EntityGeneratedItem.class, EntityExItems.class}, version = 0, exportSchema = false)
public abstract class DataBaseGeneratedItems extends RoomDatabase {

    public abstract DaoGeneratedItem workWithItems();

    public abstract DaoExItems workExItems();
}
