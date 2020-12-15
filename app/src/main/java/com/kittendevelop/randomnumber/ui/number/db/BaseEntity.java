package com.kittendevelop.randomnumber.ui.number.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public abstract class BaseEntity {

    /*абсолютное время*/
    @PrimaryKey
    @ColumnInfo(name = "id")
    public long mId;

    public BaseEntity id(long id){
        mId = id;
        return this;
    }

    public long getId() {
        return mId;
    }

}
