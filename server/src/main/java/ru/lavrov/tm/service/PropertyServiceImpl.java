package ru.lavrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IPropertyService;
import ru.lavrov.tm.exception.property.PropertyLoadingException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class PropertyServiceImpl implements IPropertyService {

    @NotNull
    public static Properties appProperties = new Properties();

    public PropertyServiceImpl(){
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
