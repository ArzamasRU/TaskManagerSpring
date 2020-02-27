package ru.lavrov.tm.bootstrap;

import ru.lavrov.tm.command.AbstractCommand;
import ru.lavrov.tm.command.ExitCommand.ExitCommand;
import ru.lavrov.tm.command.helpCommand.HelpCommand;
import ru.lavrov.tm.command.projectCommand.*;
import ru.lavrov.tm.command.taskCommand.*;
import ru.lavrov.tm.command.userCommand.*;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.commandException.CommandDescNotExistsException;
import ru.lavrov.tm.exception.commandException.CommandNotExistsException;
import ru.lavrov.tm.exception.userException.*;
import ru.lavrov.tm.repository.ProjectRepository;
import ru.lavrov.tm.repository.TaskRepository;
import ru.lavrov.tm.repository.UserRepository;
import ru.lavrov.tm.service.ProjectService;
import ru.lavrov.tm.service.TaskService;
import ru.lavrov.tm.service.UserService;

import java.util.*;

public class Bootstrap {
    private final ProjectRepository projectRepository = new ProjectRepository();
    private final TaskRepository taskRepository = new TaskRepository();
    private final UserRepository userRepository = new UserRepository();
    private final ProjectService projectService = new ProjectService(projectRepository, taskRepository);
    private final TaskService taskService = new TaskService(taskRepository, projectRepository);
    private final UserService userService = new UserService(userRepository);
    private final Scanner input = new Scanner(System.in);
    private final boolean safeCommand = true;
    private final boolean notSafeCommand = false;
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
        registry(new ExitCommand(), notSafeCommand);
        registry(new HelpCommand(), safeCommand);
        registry(new ProjectClearCommand(), notSafeCommand);
        registry(new ProjectCreateCommand(), notSafeCommand);
        registry(new ProjectListCommand(), notSafeCommand);
        registry(new ProjectRemoveCommand(), notSafeCommand);
        registry(new ProjectTasksListCommand(), notSafeCommand);
        registry(new ProjectRenameCommand(), notSafeCommand);
        registry(new ProjectAttachToUserCommand(), notSafeCommand);
        registry(new TaskClearCommand(), notSafeCommand);
        registry(new TaskCreateCommand(), notSafeCommand);
        registry(new TaskAttachToProjectCommand(), notSafeCommand);
        registry(new TaskListCommand(), notSafeCommand);
        registry(new TaskRemoveCommand(), notSafeCommand);
        registry(new TaskRenameCommand(), notSafeCommand);
        registry(new TaskAttachToUserCommand(), notSafeCommand);
        registry(new UserLoginCommand(), safeCommand);
        registry(new UserLogoutCommand(), notSafeCommand);
        registry(new UserRegisterCommand(), safeCommand);
        registry(new UserUpdateCommand(), notSafeCommand);
        registry(new UserDisplayCommand(), notSafeCommand);
    }

    private void registry(AbstractCommand command, boolean isSafe) throws RuntimeException {
        final String cliCommand = command.command();
        final String cliDescription = command.description();
        if (cliCommand == null || cliCommand.isEmpty())
            throw new CommandNotExistsException();
        if (cliDescription == null || cliDescription.isEmpty())
            throw new CommandDescNotExistsException();
//        command.setServiceLocator(this);
        command.setBootstrap(this);
        command.setSafe(isSafe);
        commands.put(cliCommand, command);
    }

    private void execute(String command) throws RuntimeException {
        if (command == null || command.isEmpty())
            return;
        final AbstractCommand abstractCommand = commands.get(command);
        if (abstractCommand == null)
            return;
        if (sessionUser == null && !abstractCommand.isSafe())
            throw new UserIsNotAuthorizedException();
        abstractCommand.execute();
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
        if (password.equals(user.getPassword()))
            throw new UserLoginOrPasswordIsIncorrectException();
        setSessionUser(user);
    }

    public void logout(){
        setSessionUser(null);
    }
}
