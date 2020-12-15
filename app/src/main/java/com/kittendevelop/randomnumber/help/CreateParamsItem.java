package com.kittendevelop.randomnumber.help;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CreateParamsItem {

    public static int[] date(){
        Calendar calendar = new GregorianCalendar();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        return new int[]{year,month,day,hour,minute,second};
    }
}
