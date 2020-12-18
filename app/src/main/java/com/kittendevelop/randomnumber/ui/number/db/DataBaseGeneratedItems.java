package com.kittendevelop.randomnumber.ui.number.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {EntityGeneratedItem.class, EntityGeneratedEx.class,EntityExItems.class,BaseEntityItems.class,BaseEntity.class},
        version = DataBaseGeneratedItems.VERSION, exportSchema = DataBaseGeneratedItems.EXPORT)
public abstract class DataBaseGeneratedItems extends RoomDatabase {

    static final int VERSION = 3;
    static final boolean EXPORT = false;

    public abstract DaoGeneratedItem workWithItems();
    public abstract DaoGeneratedEx workWithEx();
    public abstract DaoExItems workExItems();

}
