package ru.lavrov.tm.bootstrap;

import ru.lavrov.tm.api.*;
import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.command.about.AboutCommand;
import ru.lavrov.tm.command.exit.ExitCommand;
import ru.lavrov.tm.command.help.HelpCommand;
import ru.lavrov.tm.command.project.*;
import ru.lavrov.tm.command.task.*;
import ru.lavrov.tm.command.user.*;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.command.CommandDescriptionIsInvalidException;
import ru.lavrov.tm.exception.command.CommandIsInvalidException;
import ru.lavrov.tm.exception.command.CommandNotExistsException;
import ru.lavrov.tm.exception.user.*;
import ru.lavrov.tm.exception.util.UtilAlgorithmNotExistsException;
import ru.lavrov.tm.repository.AbstractProjectRepository;
import ru.lavrov.tm.repository.ProjectRepositoryImpl;
import ru.lavrov.tm.repository.TaskRepositoryImpl;
import ru.lavrov.tm.repository.UserRepositoryImpl;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.ProjectServiceImpl;
import ru.lavrov.tm.service.TaskServiceImpl;
import ru.lavrov.tm.service.UserServiceImpl;
import ru.lavrov.tm.util.HashUtil;

import java.security.NoSuchAlgorithmException;
import java.util.*;

public final class Bootstrap implements ServiceLocator{
    private final AbstractProjectRepository projectRepository = new ProjectRepositoryImpl();
    private final TaskRepository taskRepository = new TaskRepositoryImpl();
    private final UserRepository userRepository = new UserRepositoryImpl();
    private final ProjectService projectService = new ProjectServiceImpl(projectRepository, taskRepository, userRepository);
    private final TaskService taskService = new TaskServiceImpl(taskRepository, projectRepository, userRepository);
    private final UserService userService = new UserServiceImpl(userRepository);
    private final Scanner input = new Scanner(System.in);
    private final Map<String, AbstractCommand> commands = new LinkedHashMap();
    private User currentUser;

    @Override
    public ProjectService getProjectService() {
        return projectService;
    }

    @Override
    public TaskService getTaskService() {
        return taskService;
    }

    @Override
    public UserService getUserService() {
        return userService;
    }

    public void start() throws InstantiationException, IllegalAccessException {
        init();
        initUsers();
        System.out.println("*** WELCOME TO TASK MANAGER ***");
        String command = null;

        while (!"exit".equals(command)) {
            command = input.nextLine();
            try{
                execute(command);
            } catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void init() throws RuntimeException, IllegalAccessException, InstantiationException {
        final List<Class> commandList = Arrays.asList(ExitCommand.class,
            HelpCommand.class,
            AboutCommand.class,
            ProjectClearCommand.class,
            ProjectCreateCommand.class,
            ProjectListCommand.class,
            ProjectRemoveCommand.class,
            ProjectTasksListCommand.class,
            ProjectRenameCommand.class,
            TaskClearCommand.class,
            TaskCreateCommand.class,
            TaskListCommand.class,
            TaskRemoveCommand.class,
            TaskRenameCommand.class,
            UserLoginCommand.class,
            UserLogoutCommand.class,
            UserRegisterCommand.class,
            UserUpdateCommand.class,
            UserDisplayCommand.class,
            UserDeleteCommand.class);
        if (commandList == null)
            return;
        for (final Class command : commandList) {
            if (command == null)
                continue;
            registry((AbstractCommand) command.newInstance());
        }
    }

    private void initUsers(){
        try {
            userService.createByLogin("user", HashUtil.getHash("user"), String.valueOf(Role.User));
            userService.createByLogin("admin", HashUtil.getHash("admin"), String.valueOf(Role.Admin));
        } catch (NoSuchAlgorithmException e) {
            new UtilAlgorithmNotExistsException();
        }
    }

    private void registry(final AbstractCommand command) throws RuntimeException {
        if (command == null)
            throw new CommandNotExistsException();
        final String cliCommand = command.getCommand();
        final String cliDescription = command.getDescription();
        if (cliCommand == null || cliCommand.isEmpty())
            throw new CommandIsInvalidException();
        if (cliDescription == null || cliDescription.isEmpty())
            throw new CommandDescriptionIsInvalidException();
        command.setBootstrap(this);
        commands.put(cliCommand, command);
    }

    private void execute(final String command) throws RuntimeException {
        if (command == null || command.isEmpty())
            throw new CommandIsInvalidException();
        final AbstractCommand abstractCommand = commands.get(command);
        if (abstractCommand == null)
            throw new CommandNotExistsException();
        if (currentUser == null && !abstractCommand.isSafe())
            throw new UserIsNotAuthorizedException();
        Role role;
        if (currentUser == null)
            role = null;
        else
            role = currentUser.getRole();
        if (!hasPermission(abstractCommand.getRoles(), role))
            throw new UserDoNotHavePermissionException();
        abstractCommand.execute();
    }

    private boolean hasPermission(final Collection<Role> listRoles, Role role){
        if (listRoles == null)
            return true;
        if (role == null)
            throw new UserRoleIsInvalidException();
        final String currentUserRoleName = role.displayName();
        for (final Role currentRole : listRoles) {
            if (currentRole == null)
                continue;
            if (currentRole.displayName().equals(currentUserRoleName))
                return true;
        }
        return false;
    }

    @Override
    public List<AbstractCommand> getCommands() {
        return new ArrayList(commands.values());
    }

    public void login(final String login, final String password){
        if (login == null || login.isEmpty())
            throw new UserLoginIsInvalidException();
        if (password == null || password.isEmpty())
            throw new UserPasswordIsInvalidException();
        final User user = (User) userRepository.findEntityByName(login, null);
        if (user == null)
            throw new UserLoginNotExistsException();
        if (!password.equals(user.getPassword()))
            throw new UserLoginOrPasswordIsIncorrectException();
        setCurrentUser(user);
    }

    public void logout(){
        setCurrentUser(null);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
    }
}
