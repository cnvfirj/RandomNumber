package com.kittendevelop.randomnumber.ui.number.db;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "EX_ITEMS")
public class EntityExItems extends BaseEntity{

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

    @Override
    public long getId() {
        return mId;
    }

    public String getTableEx() {
        return mTableEx;
    }

    public String getDelimiter() {
        return mDelimiter;
    }
}
