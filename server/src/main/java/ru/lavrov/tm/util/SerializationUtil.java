package ru.lavrov.tm.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.entity.IEntity;
import ru.lavrov.tm.exception.util.SerializationException;

import java.io.*;
import java.util.Collection;

public final class SerializationUtil {

    public static void write(@NotNull final Collection<? extends IEntity> list, @NotNull final String path) {
        if (list == null)
            throw new SerializationException();
        try {
            @NotNull final FileOutputStream fileOutputStream = new FileOutputStream(path);
            @NotNull final ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
        } catch (IOException e) {
            return;
        }
    }

    @Nullable
    public static <T> Collection<T> read(@NotNull final String path) {
        @Nullable final Collection<T> list;
        try {
            @NotNull final FileInputStream fileInputStream = new FileInputStream(path);
            @NotNull final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            list = (Collection<T>) objectInputStream.readObject();
        } catch (Exception e) {
            return null;
        }
        return list;
    }
}
