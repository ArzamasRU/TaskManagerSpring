package ru.lavrov.tm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

    public Date convertStrToDate(String source) throws ParseException {
        return formatter.parse(source);
    }

    public String convertDateToStr(Date date) {
        return formatter.format(date);
    }
}

