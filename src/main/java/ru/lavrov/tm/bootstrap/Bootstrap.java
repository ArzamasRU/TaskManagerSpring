package ru.lavrov.tm.bootstrap;

import ru.lavrov.tm.command.*;
import ru.lavrov.tm.exception.CommandCorruptException;
import ru.lavrov.tm.repository.ProjectRepository;
import ru.lavrov.tm.repository.TaskRepository;
import ru.lavrov.tm.service.ProjectService;
import ru.lavrov.tm.service.TaskService;

import java.util.*;

public class Bootstrap {
    private final ProjectRepository projectRepository = new ProjectRepository();
    private final TaskRepository taskRepository = new TaskRepository();
    private final ProjectService projectService = new ProjectService(projectRepository, taskRepository);
    private final TaskService taskService = new TaskService(taskRepository, projectService);
    private final Scanner input = new Scanner(System.in);
    private Map<String, AbstractCommand> commands = new LinkedHashMap();

    public ProjectService getProjectService() {
        return projectService;
    }

    public TaskService getTaskService() {
        return taskService;
    }

    public void start() throws Exception {
        init();
        System.out.println("*** WELCOME TO TASK MANAGER ***");
        String command = null;
        while (!"exit".equals(command)) {
            command = input.nextLine();
            execute(command);
        }
    }

    private void init() throws CommandCorruptException {
        registry(new ExitCommand(this));
        registry(new HelpCommand(this));
        registry(new ProjectClearCommand(this));
        registry(new ProjectCreateCommand(this));
        registry(new ProjectListCommand(this));
        registry(new ProjectRemoveCommand(this));
        registry(new ProjectTasksListCommand(this));
        registry(new ProjectRenameCommand(this));
        registry(new TaskClearCommand(this));
        registry(new TaskCreateCommand(this));
        registry(new TaskAttachCommand(this));
        registry(new TaskListCommand(this));
        registry(new TaskRemoveCommand(this));
        registry(new TaskRenameCommand(this));
    }

    private void registry(AbstractCommand command) throws CommandCorruptException {
        final String cliCommand = command.command();
        final String cliDescription = command.description();
        if (cliCommand == null || cliCommand.isEmpty())
            throw new CommandCorruptException();
        if (cliDescription == null || cliDescription.isEmpty())
            throw new CommandCorruptException();
//        command.setServiceLocator(this);
//        command.setBootstrap(this);
        commands.put(cliCommand, command);
    }

    private void execute(String command) throws Exception {
        if (command == null || command.isEmpty())
            return;
        final AbstractCommand abstractCommand = commands.get(command);
        if (abstractCommand == null)
            return;
        abstractCommand.execute();
    }

    public List<AbstractCommand> getCommands() {
        return new ArrayList(commands.values());
    }
}
