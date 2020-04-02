package ru.lavrov.tm.command.user;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.endpoint.TokenEndpointService;
import ru.lavrov.tm.util.InputUtil;

import static ru.lavrov.tm.util.HashUtil.md5Hard;

@NoArgsConstructor
public final class UserLoginCommand extends AbstractCommand {
    private static final boolean SAFE = true;
    @NotNull
    private static final String COMMAND = "login";
    @NotNull
    private static final String DESCRIPTION = "Authorization.";

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
        System.out.println("[Please Log in]");
        System.out.println("enter login:");
        @Nullable final String login = InputUtil.INPUT.nextLine();
        System.out.println("enter password:");
        @NotNull final String password = InputUtil.INPUT.nextLine();
        @Nullable final String hashedPassword = md5Hard(password);
        @NotNull final TokenEndpointService tokenEndpointService = bootstrap.getTokenEndpointService();
        @Nullable final String token = tokenEndpointService.getTokenEndpointPort().login(login, hashedPassword);
        if (token == null  || token.isEmpty())
            System.out.println("[login or password is incorrect!]");
        else {
            bootstrap.setCurrentToken(token);
            System.out.println("[You are logged in]");
        }

        System.out.println();
    }

    @Override
    public boolean isSafe() {
        return SAFE;
    }


}