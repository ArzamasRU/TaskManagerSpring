package ru.lavrov.tm;

import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    private Scanner input = new Scanner(System.in);
    private List<Project> projectList = new ArrayList<>();
    private List<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        Application app = new Application();
        app.start();
    }

    public void start(){
        boolean exit = false;
        String command;

        System.out.println("*** Welcome to task manager ***");

        while (!exit) {
            command = input.nextLine();

            switch (command){
                case "help":
                    displayHelp();
                    break;
                case "project-create":
                    createProject();
                    break;
                case "project-clear":
                    clearProject();
                    break;
                case "project-list":
                    displayProjects();
                    break;
                case "project-remove":
                    removeProject();
                    break;
                case "project-rename":
                    renameProject();
                    break;
                case "task-clear":
                    clearTask();
                    break;
                case "task-create":
                    createTask();
                    break;
                case "task-list":
                    displayTasks();
                    break;
                case "task-remove":
                    removeTask();
                    break;
                case "task-rename":
                    renameTask();
                    break;
                case "exit":
                    exit = true;
                    break;
                default:
                    System.out.println("incorrect args");
                    break;
            }
        }

        input.close();
        System.out.print("you left this wonderful program");
    }

    private void removeTask(){
        String command;
        boolean objectFound = false;
        System.out.println("[task remove]");
        System.out.println("enter name:");
        command = input.nextLine();
        objectFound = false;
        for (Task task : taskList) {
            if (task.getName().equals(command)) {
                objectFound = true;
                taskList.remove(task);
                break;
            }
        }
        if (objectFound)
            System.out.println("ok");
         else
            System.out.println("task does not exist");
    }

    private void removeProject(){
        String command;
        boolean objectFound = false;
        System.out.println("[project remove]");
        System.out.println("enter name:");
        command = input.nextLine();
        objectFound = false;
        for (Project project : projectList) {
            if (project.getName().equals(command)) {
                objectFound = true;
                projectList.remove(project);
                break;
            }
        }
        if (objectFound)
            System.out.println("ok");
        else
            System.out.println("project does not exist");
    }

    private void displayHelp(){
        System.out.println("project-create");
        System.out.println("project-clear");
        System.out.println("project-list");
        System.out.println("project-remove");
        System.out.println("task-clear");
        System.out.println("task-create");
        System.out.println("task-list");
        System.out.println("task-remove");
        System.out.println("exit");
        System.out.println("help");
    }

    private void createProject() {
        String command;
        System.out.println("[Project create]");
        System.out.println("enter name:");
        command = input.nextLine();
        projectList.add(new Project(command));
        System.out.println("ok");
    }

    private void createTask() {
        String command;
        System.out.println("[Task create]");
        System.out.println("enter name:");
        command = input.nextLine();
        taskList.add(new Task(command));
        System.out.println("ok");
    }

    private void clearProject() {
        projectList.clear();
        System.out.println("[All projects removed]");
    }

    private void clearTask() {
        taskList.clear();
        System.out.println("[All tasks removed]");
    }

    private void displayProjects(){
        int i = 0;
        System.out.println("[Project list]");
        for (Project project : projectList) {
            i++;
            System.out.println(i + ". " + project.getName());
        }
    }

    private void displayTasks(){
        int i = 0;
        System.out.println("[Task list]");
        for (Task task : taskList) {
            i++;
            System.out.println(i + ". " + task.getName());
        }
    }

    private void renameTask(){
        String command;
        boolean objectFound = false;
        Task currentTask = null;
        System.out.println("[Task rename]");
        System.out.println("Enter the name of the task that needs a new name:");
        command = input.nextLine();
        objectFound = false;
        for (Task task : taskList) {
            if (task.getName().equals(command)) {
                objectFound = true;
                currentTask = task;
                break;
            }
        }
        if (objectFound) {
            System.out.println("enter new name:");
            command = input.nextLine();
            currentTask.setName(command);
            System.out.println("ok");
        }
        else
            System.out.println("project does not exist");
    }

    private void renameProject(){
        String command;
        boolean objectFound = false;
        Project currentProject = null;
        System.out.println("[Project rename]");
        System.out.println("Enter the name of the project that needs a new name:");
        command = input.nextLine();
        objectFound = false;
        for (Project project : projectList) {
            if (project.getName().equals(command)) {
                objectFound = true;
                currentProject = project;
                break;
            }
        }
        if (objectFound) {
            System.out.println("enter new name:");
            command = input.nextLine();
            currentProject.setName(command);
            System.out.println("ok");
        }
        else
            System.out.println("project does not exist");
    }
}