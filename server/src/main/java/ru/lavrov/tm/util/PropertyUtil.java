package ru.lavrov.tm.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.exception.property.PropertyLoadingException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {

    @NotNull
    public static Properties appProperties = new Properties();

    {
        try {
            @Nullable final FileInputStream fis =
                    new FileInputStream("server/src/main/resources/application.properties");
            if (fis != null)
                appProperties.load(fis);
        } catch (IOException e) {
            throw new PropertyLoadingException();
        }
    }
}
