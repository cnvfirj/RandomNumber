package com.kittendevelop.randomnumber.ui.number.db;


import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "EX_ITEMS")
public class EntityExItems extends BaseEntity{

    @ColumnInfo(name = "tableEx")
    public String mTableEx;

    @ColumnInfo(name = "delimiter")
    public String mDelimiter;

    @ColumnInfo(name = "source")
    public int mSource;


    public EntityExItems setTableEx(String mTableEx) {
        this.mTableEx = mTableEx;
        return this;
    }

    public EntityExItems setDelimiter(String mDelimiter) {
        this.mDelimiter = mDelimiter;
        return this;
    }

    public EntityExItems setSource(int mSource) {
        this.mSource = mSource;
        return this;
    }

    public int getSource() {
        return mSource;
    }

    public String getTableEx() {
        return mTableEx;
    }

    public String getDelimiter() {
        return mDelimiter;
    }
}
