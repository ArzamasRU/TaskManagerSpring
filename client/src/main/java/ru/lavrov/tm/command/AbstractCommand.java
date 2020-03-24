package ru.lavrov.tm.command;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.bootstrap.Bootstrap;
import ru.lavrov.tm.endpoint.Role;

import java.util.Collection;

@NoArgsConstructor
public abstract class AbstractCommand {
    @NotNull
    protected Bootstrap bootstrap;

    @NotNull
    public Bootstrap getBootstrap() {
        return bootstrap;
    }

    @NotNull
    public abstract String getCommand();

    @NotNull
    public abstract String getDescription();

    public abstract void execute();

    public void setBootstrap(@NotNull final Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public abstract boolean isSafe();

    @Nullable
    public abstract Collection<Role> getRoles();
}

