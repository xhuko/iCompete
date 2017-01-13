package com.icompete.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Peter Sekan, peter.sekan@mail.muni.cz
 * @version 22/11/2016
 */
public class DayEqualsUtils {
    /**
     * Compare two date timestamp for representation of same day
     * @param a date
     * @param b date
     * @return true if dates' timestamp represent same day
     */
    public static boolean equals(Date a, Date b) {
        if (a == null || b == null) return (a == null && b == null);
        if (a == b) return true;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(a);
        cal2.setTime(b);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
               cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    public static int hashCode(Date a) {
        if (a == null) return 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(a);
        return 19 + cal.get(Calendar.YEAR)*365 + cal.get(Calendar.DAY_OF_YEAR);
    }
}
