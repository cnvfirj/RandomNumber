package com.kittendevelop.randomnumber.ui.number.db;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class FillNewBaseEntityItem {

    /*месяц почему то считается с нуля*/
    public static void fill (BaseEntityItems entity){
        Calendar c = new GregorianCalendar();
        entity.id(c.getTime().getTime());
        entity.date(c.get(Calendar.YEAR),c.get(Calendar.MONTH)+1,c.get(Calendar.DAY_OF_MONTH));
        entity.time(c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),c.get(Calendar.SECOND));
    }
}
