package com.kittendevelop.randomnumber.ui.number.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity
public abstract class BaseEntityItems extends BaseEntity{


    @ColumnInfo(name = "number")
    public long mNumber;

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

    /*ресурс генерации*/
    @ColumnInfo(name = "source")
    public int mSource;

    public BaseEntityItems date(int year, int month, int day){
        mYear = year;
        mMonth = month;
        mDay = day;
        return this;
    }

    public BaseEntityItems time(int hour, int minute, int second){
        mHour = hour;
        mMinute = minute;
        mSecond = second;
        return this;
    }

    public BaseEntityItems source(int source){
        mSource = source;
        return this;
    }

    public BaseEntityItems value(long number){
        mNumber = number;
        mValue = Long.toString(number);
        return this;
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

    public int getSource() {
        return mSource;
    }

    public String getValue() {
        return mValue;
    }

    public long getNumber(){
        return mNumber;
    }

}
