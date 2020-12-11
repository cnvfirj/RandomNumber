package com.kittendevelop.randomnumber.ui.number.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {EntityGeneratedItem.class, EntityExItems.class},
        version = DataBaseGeneratedItems.VERSION, exportSchema = DataBaseGeneratedItems.EXPORT)
public abstract class DataBaseGeneratedItems extends RoomDatabase {

    static final int VERSION = 1;
    static final boolean EXPORT = false;

    public abstract DaoGeneratedItem workWithItems();

    public abstract DaoExItems workExItems();
}
