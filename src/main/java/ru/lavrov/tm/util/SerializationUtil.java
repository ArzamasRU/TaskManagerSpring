package ru.lavrov.tm.util;

        import org.jetbrains.annotations.NotNull;

        import java.io.FileOutputStream;
        import java.text.SimpleDateFormat;
        import java.util.Scanner;

public class SerializationUtil {
    @NotNull
    private static final FileOutputStream userOutput = new FileOutputStream();
    @NotNull
    private static final String pathToFilesUser = "src/main/file/users";
    @NotNull
    private static final String pathToFilesProject = "src/main/file/project";
    @NotNull
    private static final String pathToFilesTask = "src/main/file/task";
}
