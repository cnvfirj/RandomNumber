package com.kittendevelop.randomnumber.ui.number.db;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class CommonValues {

    @PrimaryKey
    @ColumnInfo(name = "id")
    public long mId;

    @ColumnInfo(name = "number")
    public long mNumber;

    @ColumnInfo(name = "value")
    public String mValue;


}
