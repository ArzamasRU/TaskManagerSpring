package ru.lavrov.tm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.entity.ExternalizationStorage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public final class JAXBUtil {

    public static <T> void writeToXMLByJAXB(@Nullable final Collection<T> list, @Nullable final String path) {
        if (list == null || list.isEmpty())
            return;
        if (path == null)
            return;
        @Nullable Class currClass = null;
        for (@Nullable final T t : list)
            if (t != null) {
                currClass = t.getClass();
                break;
            }
        if (currClass == null)
            return;
        try {
            @Nullable final JAXBContext context = JAXBContext.newInstance(currClass);
            if (context == null)
                return;
            @Nullable final Marshaller marshaller = context.createMarshaller();
            if (marshaller == null)
                return;
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            @Nullable final String className = currClass.getSimpleName();
            int index = 1;
            for (@Nullable final T t : list) {
                if (t == null)
                    continue;
                @Nullable final File file = new File(path + className + index++ + ".xml");
                if (file == null)
                    return;
                marshaller.marshal(t, file);
            }
        } catch (JAXBException e) {
            return;
        }
    }

    @Nullable
    public static <T> Collection<T> readFromXMLByJAXB(@NotNull final Class inputClass, @NotNull final String path) {
        if (inputClass == null)
            return null;
        if (path == null)
            return null;
        @Nullable final Collection<T> list = new ArrayList<>();
        try {
            @Nullable final JAXBContext context = JAXBContext.newInstance(inputClass);
            if (context == null)
                return null;
            @Nullable final Unmarshaller unmarshaller = context.createUnmarshaller();
            if (unmarshaller == null)
                return null;
            @Nullable final String className = inputClass.getSimpleName();
            @Nullable final File[] files = FileUtil.findFiles(path, className, "xml");
            for (@Nullable final File file : files) {
                if (file == null)
                    continue;
                @Nullable final T t = (T) unmarshaller.unmarshal(file);
                list.add(t);
            }
        } catch (JAXBException e) {
            return null;
        }
        return list;
    }

    @Nullable
    public static void writeToJSONByJAXB(
            @Nullable final ExternalizationStorage storage, @Nullable final String filePath
    ) {
        if (storage == null)
            return;
        if (filePath == null)
            return;
        @NotNull final Class currClass = storage.getClass();
        try {
            @Nullable final JAXBContext context = JAXBContext.newInstance(currClass);
            if (context == null)
                return;
            @Nullable final Marshaller marshaller = context.createMarshaller();
            if (marshaller == null)
                return;
            marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
            marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(storage, new File(filePath));
        } catch (JAXBException e) {
            return;
        }
    }

    @Nullable
    public static ExternalizationStorage readFromJSONByJAXB(
            @Nullable final Class inputClass, @Nullable final String filePath
    ) {
        if (inputClass == null)
            return null;
        if (filePath == null)
            return null;
        try {
            @Nullable final JAXBContext context = JAXBContext.newInstance(inputClass);
            if (context == null)
                return null;
            @Nullable final Unmarshaller unmarshaller = context.createUnmarshaller();
            if (unmarshaller == null)
                return null;
            unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
            unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true);
            @Nullable final ExternalizationStorage storage =
                    (ExternalizationStorage) unmarshaller.unmarshal(new File(filePath));
            return storage;
        } catch (JAXBException e) {
            return null;
        }
    }
}
