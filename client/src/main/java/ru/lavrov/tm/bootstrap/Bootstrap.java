package ru.lavrov.tm.bootstrap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.reflections.Reflections;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.endpoint.*;
import ru.lavrov.tm.exception.command.CommandDescriptionIsInvalidException;
import ru.lavrov.tm.exception.command.CommandIsInvalidException;
import ru.lavrov.tm.exception.command.CommandNotExistsException;
import ru.lavrov.tm.exception.user.UserDoNotHavePermissionException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.exception.user.UserRoleIsInvalidException;
import ru.lavrov.tm.util.InputUtil;

import java.util.*;

@Getter
@NoArgsConstructor
public final class Bootstrap {
    @NotNull
    private final Map<String, AbstractCommand> commands = new LinkedHashMap();
    @NotNull
    private final UserEndpointService userEndpointService = new UserEndpointService();
    @NotNull
    private final SessionEndpointService sessionEndpointService = new SessionEndpointService();
    @NotNull
    private final ProjectEndpointService projectEndpointService = new ProjectEndpointService();
    @NotNull
    private final TaskEndpointService taskEndpointService = new TaskEndpointService();
    @NotNull
    private final GeneralCommandEndpointService generalCommandEndpointService = new GeneralCommandEndpointService();
    @Setter
    @Nullable
    private String token;
    @Nullable
    private static final Set<Class<? extends AbstractCommand>> classes;

    static {
        classes = new Reflections("ru.lavrov.tm").getSubTypesOf(AbstractCommand.class);
    }

    public void init() throws InstantiationException, IllegalAccessException {
        initProperties();
        initClasses(classes);
        System.out.println("*** WELCOME IN CLIENT APP OF TASK MANAGER ***");
        @Nullable String command = null;
        while (!"exit".equals(command)) {
            command = InputUtil.INPUT.nextLine();
            try {
                execute(command);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void initClasses(
            @Nullable Collection<Class<? extends AbstractCommand>> classes
    ) throws IllegalAccessException, InstantiationException {
        if (classes == null || classes.isEmpty())
            return;
        for (@Nullable Class command : classes) {
            if (command == null)
                continue;
            if (!AbstractCommand.class.isAssignableFrom(command))
                continue;
            registry((AbstractCommand) command.newInstance());
        }
    }

    private void initProperties(){
        System.setProperty("javax.xml.bind.context.factory","org.eclipse.persistence.jaxb.JAXBContextFactory");
    }

    private void registry(@Nullable final AbstractCommand command) {
        if (command == null)
            throw new CommandNotExistsException();
        @Nullable final String cliCommand = command.getCommand();
        @Nullable final String cliDescription = command.getDescription();
        if (cliCommand == null || cliCommand.isEmpty())
            throw new CommandIsInvalidException();
        if (cliDescription == null || cliDescription.isEmpty())
            throw new CommandDescriptionIsInvalidException();
        command.setBootstrap(this);
        commands.put(cliCommand, command);
    }

    private void execute(@Nullable final String command) {
        if (command == null || command.isEmpty())
            throw new CommandIsInvalidException();
        @Nullable final AbstractCommand abstractCommand = commands.get(command);
        if (abstractCommand == null)
            throw new CommandNotExistsException();
//        if (currentSession == null && !abstractCommand.isSafe())
//            throw new UserIsNotAuthorizedException();
//        @Nullable Role role;
//        if (currentSession == null)
//            role = null;
//        else
//            role = currentSession.getRole();
//        if (!hasPermission(abstractCommand.getRoles(), role))
//            throw new UserDoNotHavePermissionException();
        abstractCommand.execute();
    }

    private boolean hasPermission(@Nullable final Collection<Role> listRoles, @Nullable Role role) {
        if (listRoles == null)
            return true;
        if (role == null)
            throw new UserRoleIsInvalidException();
        return listRoles.contains(role);
    }

    @NotNull
    public List<AbstractCommand> getCommands() {
        return new ArrayList<>(commands.values());
    }
}
