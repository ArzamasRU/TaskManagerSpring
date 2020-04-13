package ru.lavrov.tm;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.context.AppContext;

public final class Server {
    public static void main(@Nullable String[] args) {
        @NotNull final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppContext.class);
        applicationContext.getBean(Bootstrap.class).init();
    }
}