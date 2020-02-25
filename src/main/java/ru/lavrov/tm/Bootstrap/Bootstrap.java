package ru.lavrov.tm.Bootstrap;

import ru.lavrov.tm.Const.ConsoleConst;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.repository.ProjectRepository;
import ru.lavrov.tm.repository.TaskRepository;
import ru.lavrov.tm.service.ProjectService;
import ru.lavrov.tm.service.TaskService;

import java.util.Map;
import java.util.Scanner;

public class Bootstrap {
    private final ProjectRepository projectRepository = new ProjectRepository();
    private final TaskRepository taskRepository = new TaskRepository();
    private final ProjectService projectService = new ProjectService(projectRepository, taskRepository);
    private final TaskService taskService = new TaskService(taskRepository, projectService);
    private final Scanner input = new Scanner(System.in);

    public void init() throws Exception {
        boolean exitFlag = false;
        String command;

        System.out.println("*** Welcome to task manager ***");

        while (!exitFlag) {
            command = input.nextLine();

            switch (command) {
                case ConsoleConst.HELP:
                    displayHelp();
                    break;
                case ConsoleConst.CREATE_PROJECT:
                    createProject();
                    break;
                case ConsoleConst.CLEAR_PROJECT:
                    clearProject();
                    break;
                case ConsoleConst.DISPLAY_PROJECTS:
                    displayProjects();
                    break;
                case ConsoleConst.REMOVE_PROJECT:
                    removeProject();
                    break;
                case ConsoleConst.UPDATE_PROJECT_START_DATE:
                    updateProjectStartDate();
                    break;
                case ConsoleConst.UPDATE_PROJECT_FINISH_DATE:
                    updateProjectFinishDate();
                    break;
                case ConsoleConst.UPDATE_TASK_START_DATE:
                    updateProjectStartDate();
                    break;
                case ConsoleConst.UPDATE_TASK_FINISH_DATE:
                    updateProjectFinishDate();
                    break;
                case ConsoleConst.CLEAR_TASK:
                    clearTask();
                    break;
                case ConsoleConst.CREATE_TASK:
                    createTask();
                    break;
                case ConsoleConst.DISPLAY_TASK:
                    displayTasks();
                    break;
                case ConsoleConst.REMOVE_TASK:
                    removeTask();
                    break;
                case ConsoleConst.ATTACH_TASK:
                    attachTask();
                    break;
                case ConsoleConst.DISPLAY_PROJECT_TASKS:
                    displayProjectTasks();
                    break;
                case ConsoleConst.EXIT:
                    exitFlag = true;
                    break;
                default:
                    System.out.println("incorrect args");
                    break;
            }
        }
        input.close();
        System.out.print("you left this wonderful program");
    }

    private void updateProjectStartDate() throws Exception {
        updateDate("project", "start");
    }

    private void updateProjectFinishDate() throws Exception {
        updateDate("project", "finish");
    }

    private void updateTaskStartDate() throws Exception {
        updateDate("task", "start");
    }

    private void updateTaskFinishDate() throws Exception {
        updateDate("task","finish");
    }

    private void updateDate(String typeOfEntity, String typeOfDate) throws Exception {
        System.out.println("[" + typeOfEntity + " " + typeOfDate + " date update]");
        System.out.println("enter " + typeOfEntity + " name:");
        String projectName = input.nextLine();
        System.out.println("enter " + typeOfDate + " date like 'dd.MM.yyyy':");
        String date = input.nextLine();


        if (typeOfEntity.equals("project")) {
            if (typeOfDate.equals("start"))
                projectService.updateProjectStartDate(date, projectName);
            else
                projectService.updateProjectFinishDate(date, projectName);
        } else {
            if (typeOfDate.equals("start"))
                taskService.updateTaskStartDate(date, projectName);
            else
                taskService.updateTaskFinishDate(date, projectName);
        }
        System.out.println("[ok]");
    }

    private void removeTask() throws Exception {
        System.out.println("[task remove]");
        System.out.println("enter name:");
        String command = input.nextLine();
        taskService.removeTask(command);
        System.out.println("[ok]");
    }

    private void removeProject() throws Exception {
        System.out.println("[project remove]");
        System.out.println("enter name:");
        String command = input.nextLine();
        projectService.removeProject(command);
        System.out.println("[ok]");
    }

    private void createProject() throws Exception {
        System.out.println("[Project create]");
        System.out.println("enter name:");
        String command = input.nextLine();
        projectService.createProject(command);
        System.out.println("[ok]");
    }

    private void createTask() throws Exception {
        System.out.println("[Task create]");
        System.out.println("enter name:");
        String command = input.nextLine();
        taskService.createTask(command);
        System.out.println("ok");
    }

    private void clearProject() {
        projectService.clearProject();
        System.out.println("[All projects removed]");
    }

    private void clearTask() {
        taskService.clearTask();
        System.out.println("[All tasks removed]");
    }

    private void displayProjects(){
        System.out.println("[Project list]");
        for (Map.Entry<String, Project> entry: projectService.displayProjects().entrySet()){
            Project project = entry.getValue();
            System.out.println(project);
        }
    }

    private void displayTasks(){
        System.out.println("[Task list]");
        for (Map.Entry<String, Task> entry: taskService.displayTasks().entrySet()){
            Task task = entry.getValue();
            System.out.println(task);
        }
    }

    public void attachTask() throws Exception {
        System.out.println("[task attach]");
        System.out.println("enter task name:");
        String taskName = input.nextLine();
        System.out.println("enter project name:");
        String projectName = input.nextLine();
        taskService.attachTask(taskName, projectName);
        System.out.println("[ok]");
    }

    private void displayProjectTasks() throws Exception {
        System.out.println("[tasks of project]");
        System.out.println("enter project name:");
        String command = input.nextLine();
        for (Task task: projectService.displayProjectTasks(command)) {
            System.out.println(task);
        }
    }

    private void displayHelp() {
        System.out.println("help: Show all commands");
        System.out.println("project-create: Create new project");
        System.out.println("project-clear: Remove all projects");
        System.out.println("project-list: Show all projects");
        System.out.println("project-remove: Remove selected project");
        System.out.println("task-clear: Remove all tasks");
        System.out.println("task-create: Create new task");
        System.out.println("task-list: Show all tasks");
        System.out.println("task-remove: Remove selected task");
        System.out.println("task-attach: attach task to project");
        System.out.println("project-tasks: display all tasks of project");
        System.out.println("exit: Exit");
    }
}
