package ru.lavrov.tm.command.general;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.endpoint.GeneralCommandEndpointService;
import ru.lavrov.tm.endpoint.Role;
import ru.lavrov.tm.endpoint.Session;

import java.util.Arrays;
import java.util.Collection;

public final class DeserializationCommand extends AbstractCommand {
    private static final boolean SAFE = true;
    @Nullable
    private static final Collection<Role> ROLES = Arrays.asList(Role.ADMIN);
    @NotNull
    private static final String COMMAND = "deserialize";
    @NotNull
    private static final String DESCRIPTION = "Deserialize data.";

    @NotNull
    @Override
    public String getCommand() {
        return COMMAND;
    }

    @NotNull
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public void execute() {
        System.out.println("[Deserialize data]");
        @Nullable final String token = bootstrap.getToken();
        @NotNull final GeneralCommandEndpointService generalCommandEndpointService =
                bootstrap.getGeneralCommandEndpointService();
        if (generalCommandEndpointService.getGeneralCommandEndpointPort().deserialize(token))
            System.out.println("[ok]");
        else
            System.out.println("[error]");
        System.out.println();
    }

    @Override
    public boolean isSafe() {
        return SAFE;
    }

    @Nullable
    @Override
    public Collection<Role> getRoles() {
        return ROLES;
    }
}
