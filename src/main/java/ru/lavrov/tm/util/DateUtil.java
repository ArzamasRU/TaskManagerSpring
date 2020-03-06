package ru.lavrov.tm.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    @NotNull private static final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

    @Nullable
    public static Date convertStrToDate(@NotNull String source) throws ParseException {
        return formatter.parse(source);
    }

    @Nullable
    public static String convertDateToStr(@NotNull Date date) {
        return formatter.format(date);
    }
}

