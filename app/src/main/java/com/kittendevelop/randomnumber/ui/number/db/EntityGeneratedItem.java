package com.kittendevelop.randomnumber.ui.number.db;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "GENERATED_ITEM")
public class EntityGeneratedItem {

    /*абсолютное время*/
    @PrimaryKey
    @ColumnInfo(name = "id")
    public long mId;

    /*значение*/
    @ColumnInfo(name = "value")
    public String mValue;

    /*дата генерации значения*/
    @ColumnInfo(name = "year")
    public int mYear;

    @ColumnInfo(name = "month")
    public int mMonth;

    @ColumnInfo(name = "day")
    public int mDay;

    /*время генерации значения*/
    @ColumnInfo(name = "hour")
    public int mHour;

    @ColumnInfo(name = "minute")
    public int mMinute;

    @ColumnInfo(name = "second")
    public int mSecond;

  /*диапазон поиска */
    @ColumnInfo(name = "from")
    public long mFrom;

    @ColumnInfo(name = "to")
    public long mTo;

    /*ресурс генерации*/
    @ColumnInfo(name = "source")
    public int mSource;


    public EntityGeneratedItem(long id, String value) {
        mId = id;
        mValue = value;
    }

    public EntityGeneratedItem confines(long from, long to){
        mFrom = from;
        mTo = to;
        return this;
    }

    public EntityGeneratedItem date(int year, int month, int day){
        mYear = year;
        mMonth = month;
        mDay = day;
        return this;
    }

    public EntityGeneratedItem time(int hour, int minute, int second){
        mHour = hour;
        mMinute = minute;
        mSecond = second;
        return this;
    }

    public EntityGeneratedItem source(int source){
        mSource = source;
        return this;
    }

    public long getId() {
        return mId;
    }

    public int getYear() {
        return mYear;
    }

    public int getMonth() {
        return mMonth;
    }

    public int getDay() {
        return mDay;
    }

    public int getHour() {
        return mHour;
    }

    public int getMinute() {
        return mMinute;
    }

    public int getSecond() {
        return mSecond;
    }

    public long getFrom() {
        return mFrom;
    }

    public long getTo() {
        return mTo;
    }

    public String getValue() {
        return mValue;
    }

    public int getSource() {
        return mSource;
    }
}
