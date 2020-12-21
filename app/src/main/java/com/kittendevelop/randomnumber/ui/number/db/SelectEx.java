package com.kittendevelop.randomnumber.ui.number.db;

import androidx.room.ColumnInfo;

public class SelectEx {

    @ColumnInfo(name = "number")
    public long mNumber;

    @ColumnInfo(name = "value")
    public String mValue;
}
