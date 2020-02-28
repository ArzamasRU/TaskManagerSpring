package ru.lavrov.tm.bootstrap;

import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.command.ExitCommand.ExitCommand;
import ru.lavrov.tm.command.helpCommand.HelpCommand;
import ru.lavrov.tm.command.projectCommand.*;
import ru.lavrov.tm.command.taskCommand.*;
import ru.lavrov.tm.command.userCommand.*;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.commandException.CommandDescriptionIsInvalidException;
import ru.lavrov.tm.exception.commandException.CommandIsInvalidException;
import ru.lavrov.tm.exception.commandException.CommandNotExistsException;
import ru.lavrov.tm.exception.userException.*;
import ru.lavrov.tm.exception.utilException.UtilAlgorithmNotExistsException;
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
    private User sessionUser;

    public User getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(User sessionUser) {
        this.sessionUser = sessionUser;
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

    public void start() {
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

    private void init() throws RuntimeException {
        registry(new ExitCommand());
        registry(new HelpCommand());
        registry(new ProjectClearCommand());
        registry(new ProjectCreateCommand());
        registry(new ProjectListCommand());
        registry(new ProjectRemoveCommand());
        registry(new ProjectTasksListCommand());
        registry(new ProjectRenameCommand());
        registry(new ProjectAttachToUserCommand());
        registry(new ProjectDetachFromUserCommand());
        registry(new TaskClearCommand());
        registry(new TaskCreateCommand());
        registry(new TaskAttachToProjectCommand());
        registry(new TaskListCommand());
        registry(new TaskRemoveCommand());
        registry(new TaskRenameCommand());
        registry(new TaskAttachToUserCommand());
        registry(new TaskDetachFromUserCommand());
        registry(new TaskDetachFromProjectCommand());
        registry(new UserLoginCommand());
        registry(new UserLogoutCommand());
        registry(new UserRegisterCommand());
        registry(new UserUpdateCommand());
        registry(new UserDisplayCommand());
        registry(new UserProjectListCommand());
        registry(new UserTaskListCommand());
    }

    private void init2() throws RuntimeException {
        List<AbstractCommand> commandList = new ArrayList();
        commandList.add(new ExitCommand());
        commandList.add(new ExitCommand());
        commandList.add(new HelpCommand());
        commandList.add(new ProjectClearCommand());
        commandList.add(new ProjectCreateCommand());
        commandList.add(new ProjectListCommand());
        commandList.add(new ProjectRemoveCommand());
        commandList.add(new ProjectTasksListCommand());
        commandList.add(new ProjectRenameCommand());
        commandList.add(new ProjectAttachToUserCommand());
        commandList.add(new ProjectDetachFromUserCommand());
        commandList.add(new TaskClearCommand());
        commandList.add(new TaskCreateCommand());
        commandList.add(new TaskAttachToProjectCommand());
        commandList.add(new TaskListCommand());
        commandList.add(new TaskRemoveCommand());
        commandList.add(new TaskRenameCommand());
        commandList.add(new TaskAttachToUserCommand());
        commandList.add(new TaskDetachFromUserCommand());
        commandList.add(new TaskDetachFromProjectCommand());
        commandList.add(new UserLoginCommand());
        commandList.add(new UserLogoutCommand());
        commandList.add(new UserRegisterCommand());
        commandList.add(new UserUpdateCommand());
        commandList.add(new UserDisplayCommand());
        commandList.add(new UserProjectListCommand());
        commandList.add(new UserTaskListCommand());
        for (AbstractCommand command : commandList) {
            registry(command);
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
        final String cliCommand = command.command();
        final String cliDescription = command.description();
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
        if (sessionUser == null && !abstractCommand.isSafe())
            throw new UserIsNotAuthorizedException();
        Role role;
        if (sessionUser == null)
            role = null;
        else {
            role = sessionUser.getRole();
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
        setSessionUser(user);
    }

    public void logout(){
        setSessionUser(null);
    }
}
