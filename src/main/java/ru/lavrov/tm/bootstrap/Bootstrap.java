package ru.lavrov.tm.bootstrap;

import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.command.Exit.ExitCommand;
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
import ru.lavrov.tm.repository.ProjectRepository;
import ru.lavrov.tm.repository.TaskRepository;
import ru.lavrov.tm.repository.UserRepository;
import ru.lavrov.tm.role.Role;
import ru.lavrov.tm.service.ProjectService;
import ru.lavrov.tm.service.TaskService;
import ru.lavrov.tm.service.UserService;
import ru.lavrov.tm.util.HashUtil;

import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Bootstrap {
    private final ProjectRepository projectRepository = new ProjectRepository();
    private final TaskRepository taskRepository = new TaskRepository();
    private final UserRepository userRepository = new UserRepository();
    private final ProjectService projectService = new ProjectService(projectRepository, taskRepository, userRepository);
    private final TaskService taskService = new TaskService(taskRepository, projectRepository, userRepository);
    private final UserService userService = new UserService(userRepository);
    private final Scanner input = new Scanner(System.in);
    private Map<String, AbstractCommand> commands = new LinkedHashMap();
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public ProjectService getProjectService() {
        return projectService;
    }

    public TaskService getTaskService() {
        return taskService;
    }

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
        List<Class> commandList = Arrays.asList(ExitCommand.class,
            HelpCommand.class,
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
            UserDisplayCommand.class);
        for (Class command : commandList) {
            registry((AbstractCommand) command.newInstance());
        }
    }

    private void initUsers(){
        try {
            userService.persist("user", HashUtil.getHash("user"), String.valueOf(Role.User));
            userService.persist("admin", HashUtil.getHash("admin"), String.valueOf(Role.Admin));
        } catch (NoSuchAlgorithmException e) {
            System.out.println(new UtilAlgorithmNotExistsException().getMessage());
        }
    }

    private void registry(AbstractCommand command) throws RuntimeException {
        final String cliCommand = command.getCommand();
        final String cliDescription = command.getDescription();
        if (cliCommand == null || cliCommand.isEmpty())
            throw new CommandIsInvalidException();
        if (cliDescription == null || cliDescription.isEmpty())
            throw new CommandDescriptionIsInvalidException();
//        command.setServiceLocator(this);
        command.setBootstrap(this);
//        command.setSafe(isSafe);
//        command.setRoles(roles);
        commands.put(cliCommand, command);
    }

    private void execute(String command) throws RuntimeException {
        if (command == null || command.isEmpty())
            return;
        final AbstractCommand abstractCommand = commands.get(command);
        if (abstractCommand == null)
            throw new CommandNotExistsException();
        if (currentUser == null && !abstractCommand.isSafe())
            throw new UserIsNotAuthorizedException();
        Role role;
        if (currentUser == null)
            role = null;
        else {
            role = currentUser.getRole();
        }
        if (!hasPermission(abstractCommand.getRoles(), role))
            throw new UserDoNotHavePermissionException();
        abstractCommand.execute();
    }

    private boolean hasPermission(Collection<Role> listRoles, Role role){
        if (listRoles == null) {
            return true;
        }
        String currentUserRoleName = role.displayName();
        for (Role currentRole : listRoles) {
            if (currentRole.displayName().equals(currentUserRoleName))
                return true;
                //            if (currentRole.displayName().equals(role.displayName()));
        }
        return false;
    }

    public List<AbstractCommand> getCommands() {
        return new ArrayList(commands.values());
    }

    public void login(String login, String password){
        if (login == null || login.isEmpty())
            throw new UserLoginIsInvalidException();
        if (password == null || password.isEmpty())
            throw new UserPasswordIsInvalidException();
        User user = userRepository.findUserByLogin(login);
        if (user == null)
            throw new UserLoginNotExistsException();
        if (!password.equals(user.getPassword()))
            throw new UserLoginOrPasswordIsIncorrectException();
        setCurrentUser(user);
    }

    public void logout(){
        setCurrentUser(null);
    }
}
