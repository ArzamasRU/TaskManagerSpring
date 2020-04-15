package ru.lavrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import ru.lavrov.tm.api.service.IAppPropertyService;
import ru.lavrov.tm.entity.AppProperties;
import ru.lavrov.tm.exception.property.PropertyLoadingException;

import java.io.FileInputStream;
import java.io.IOException;

@Service
public final class AppPropertyServiceImpl implements IAppPropertyService {

    @NotNull
    public static AppProperties appProperties = new AppProperties();

    public AppPropertyServiceImpl(){
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
