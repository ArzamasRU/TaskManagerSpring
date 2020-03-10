package ru.lavrov.tm;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IServiceLocator;
import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.command.general.AboutCommand;
import ru.lavrov.tm.command.general.ExitCommand;
import ru.lavrov.tm.command.general.HelpCommand;
import ru.lavrov.tm.command.project.*;
import ru.lavrov.tm.command.task.*;
import ru.lavrov.tm.command.user.*;

import java.util.Arrays;
import java.util.Collection;

public final class Application {
    public static void main(@Nullable String[] args) throws IllegalAccessException, InstantiationException {
        @NotNull Bootstrap serviceLocator = new Bootstrap();
        serviceLocator.init();
    }
}