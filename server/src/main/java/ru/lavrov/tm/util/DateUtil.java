package ru.lavrov.tm.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {
    @NotNull
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd.MM.yyyy");

    @Nullable
    public static Date convertStrToDate(@NotNull String source) throws ParseException {
        return FORMATTER.parse(source);
    }

    @Nullable
    public static String convertDateToStr(@NotNull Date date) {
        return FORMATTER.format(date);
    }
}

