package com.kittendevelop.randomnumber.ui.number.db;


import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "GENERATED_ITEM")
public class EntityGeneratedItem extends BaseEntityItems {

  /*диапазон поиска */
    @ColumnInfo(name = "from")
    public long mFrom;

    @ColumnInfo(name = "to")
    public long mTo;

    public EntityGeneratedItem confines(long from, long to){
        mFrom = from;
        mTo = to;
        return this;
    }

    public long getFrom() {
        return mFrom;
    }

    public long getTo() {
        return mTo;
    }

}
