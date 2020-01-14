package ru.voskhod.hibernate.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    // convert String to Date
    public static Date parseDate(String dateString) throws ParseException {
        return formatter.parse(dateString);
    }

    // convert Date to string
    public static String formatDate(Date date) {
        return (date != null) ? formatter.format(date) : null;
    }

}
