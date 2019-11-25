package com.finastra.vaoo.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static Date initExpirationDate(Date expirationDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(expirationDate);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }
}
