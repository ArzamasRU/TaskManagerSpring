package ru.lavrov.tm;

import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.service.ProjectService;
import ru.lavrov.tm.service.TaskService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Application {
    private static final String HELP = "help";
    private static final String CREATE_PROJECT = "project-create";
    private static final String CLEAR_PROJECT = "project-clear";
    private static final String DISPLAY_PROJECTS = "project-list";
    private static final String REMOVE_PROJECT = "project-remove";
    private static final String RENAME_PROJECT = "project-rename";
    private static final String UPDATE_PROJECT_START_DATE = "project-sd-update";
    private static final String DISPLAY_PROJECT_START_DATE = "project-sd";
    private static final String UPDATE_PROJECT_FINISH_DATE = "project-fd-update";
    private static final String CLEAR_TASK = "task-clear";
    private static final String CREATE_TASK = "task-create";
    private static final String DISPLAY_TASK = "task-list";
    private static final String REMOVE_TASK = "task-remove";
    private static final String RENAME_TASK = "task-rename";
    private static final String UPDATE_TASK_START_DATE = "task-sd-update";
    private static final String UPDATE_TASK_FINISH_DATE = "task-fd-update";
    private static final String ATTACH_TASK = "task-attach";
    private static final String DISPLAY_PROJECT_TASKS = "tasks-of-project";
    private static final String EXIT = "exit";

    private List<Project> projectList = new ArrayList<>();
    private List<Task> taskList = new ArrayList<>();
    private final Scanner input = new Scanner(System.in);
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
    private final ProjectService projectService = new ProjectService();
    private final TaskService taskService = new TaskService();

    public static void main(String[] args) {
        Application app = new Application();
        app.start();
    }

    public void start() {
        boolean exitFlag = false;
        String command;

        System.out.println("*** Welcome to task manager ***");

        while (!exitFlag) {
            command = input.nextLine();

            switch (command) {
                case HELP:
                    Utils.displayHelp();
                    break;
                case CREATE_PROJECT:
                    projectService.createProject();
                    break;
                case CLEAR_PROJECT:
                    projectService.clearProject();
                    break;
                case DISPLAY_PROJECTS:
                    projectService.displayProjects();
                    break;
                case REMOVE_PROJECT:
                    projectService.removeProject();
                    break;
                case RENAME_PROJECT:
                    projectService.renameProject();
                    break;
                case UPDATE_PROJECT_START_DATE:
                    updateProjectStartDate();
                    break;
                case DISPLAY_PROJECT_START_DATE:
                    displayProjectStartDate();
                    break;
                case CLEAR_TASK:
                    clearTask();
                    break;
                case CREATE_TASK:
                    createTask();
                    break;
                case DISPLAY_TASK:
                    displayTasks();
                    break;
                case REMOVE_TASK:
                    removeTask();
                    break;
                case RENAME_TASK:
                    renameTask();
                    break;
                case ATTACH_TASK:
                    attachTask();
                    break;
                case DISPLAY_PROJECT_TASKS:
                    displayProjectTasks();
                    break;
                case EXIT:
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

    private Project findProjectByName(String name) {
        boolean objectFound = false;
        Project currentProject = null;
        for (Project project : projectList) {
            if (project.getName().equals(name)) {
                objectFound = true;
                currentProject = project;
                break;
            }
        }
        if (!objectFound)
            System.out.println("project does not exist");
        return currentProject;

        projectService.
    }

    private Task findTaskByName(String name) {
        boolean objectFound = false;
        Task currentTask = null;
        for (Task task : taskList) {
            if (task.getName().equals(name)) {
                objectFound = true;
                currentTask = task;
                break;
            }
        }
        if (!objectFound)
            System.out.println("task does not exist");
        return currentTask;
    }



    private void displayProjectStartDate() {
        System.out.println("[project start date]");
        System.out.println("enter project name:");
        String command = input.nextLine();
        System.out.println(findProjectByName(command).getStartDate());
    }

    private void displayProjectTasks() {
        System.out.println("[tasks of project]");
        System.out.println("enter project name:");
        String command = input.nextLine();
        Project project = findProjectByName(command);

        int i = 0;
        for (Task task : taskList) {
            if (task.getProjectId().equals(project.getId())) {
                i++;
                System.out.println(i + ". " + task.getName());
            }
        }
    }

    private void attachTask() {
        System.out.println("[task attach]");
        System.out.println("enter task name:");
        String command = input.nextLine();
        ;

        Task task = findTaskByName(command);
        if (task != null) {
            System.out.println("enter project name:");
            command = input.nextLine();
            Project project = findProjectByName(command);
            if (project != null) {
                task.setProjectId(project.getId());
                System.out.println("ok");
            }
        }
    }

    private void removeTask() {
        System.out.println("[task remove]");
        System.out.println("enter name:");
        String command = input.nextLine();
        ;

        Task task = findTaskByName(command);
        if (task != null) {
            taskList.remove(task);
            task = null;
            System.out.println("ok");
        }
    }


    private void createProject() {
        System.out.println("[Project create]");
        System.out.println("enter name:");
        String command = input.nextLine();
        projectList.add(new Project(command));
        System.out.println("ok");
    }

    private void createTask() {
        System.out.println("[Task create]");
        System.out.println("enter name:");
        String command = input.nextLine();
        taskList.add(new Task(command));
        System.out.println("ok");
    }

    private void clearTask() {
        taskList.clear();
        System.out.println("[All tasks removed]");
    }

    private void displayTasks() {
        int i = 0;
        System.out.println("[Task list]");
        for (Task task : taskList) {
            i++;
            System.out.println(i + ". " + task.getName());
        }
    }

    private void renameTask() {
        System.out.println("[Task rename]");
        System.out.println("Enter the name of the task that needs a new name:");
        String command = input.nextLine();
        Task task = findTaskByName(command);

        if (task != null) {
            System.out.println("enter new name:");
            command = input.nextLine();
            task.setName(command);
            System.out.println("ok");
        }
    }
}