package ru.lavrov.tm.entity;

import org.jetbrains.annotations.Nullable;

import java.util.Properties;

public class AppProperties extends Properties {
    public int getIntProperty(@Nullable final String key){
        String value = super.getProperty(key);
        if (value == null)
            return 0;
        return Integer.parseInt(super.getProperty(key));
    }
}
