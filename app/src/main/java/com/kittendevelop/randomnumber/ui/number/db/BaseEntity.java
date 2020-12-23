package com.kittendevelop.randomnumber.ui.number.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.kittendevelop.randomnumber.help.Massages.MASSAGE;


@Entity
public abstract class BaseEntity implements Serializable {

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
