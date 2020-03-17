package ru.lavrov.tm.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Collection;

public final class FileUtil {

    @Nullable
    public static File[] findFiles(
            @NotNull final String path, @NotNull final String prefix, @NotNull final String suffix
    ) {
        @Nullable final File dir = new File(path);
        if (dir == null)
            return null;
        @Nullable final File[] filesList = dir.listFiles(new FilenameFilter() {
            public boolean accept(@NotNull final File dir, @NotNull final String name) {
                return name.startsWith(prefix) && name.endsWith(suffix);
            }
        });
        return filesList;
    }
}
