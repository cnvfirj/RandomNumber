package com.kittendevelop.randomnumber.ui.number.db;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "EX_ITEMS")
public class EntityExItems {

    @PrimaryKey
    @ColumnInfo(name = "id")
    public long mId;

    @ColumnInfo(name = "tableEx")
    public String mTableEx;

    @ColumnInfo(name = "delimiter")
    public String mDelimiter;

    public EntityExItems(long id, String tableEx, String delimiter) {
        this.mId = id;
        this.mTableEx = tableEx;
        this.mDelimiter = delimiter;
    }

    public long getmId() {
        return mId;
    }

    public String getmTableEx() {
        return mTableEx;
    }

    public String getmDelimiter() {
        return mDelimiter;
    }
}
