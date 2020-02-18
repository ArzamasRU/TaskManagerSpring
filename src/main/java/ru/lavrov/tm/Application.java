package ru.lavrov.tm;

import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    static private Scanner input = new Scanner(System.in);
    static private List<Project> projectList = new ArrayList<>();
    static private List<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
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

    private static void removeTask(){
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

    private static void removeProject(){
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

    private static void displayHelp(){
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

    private static void createProject() {
        String command;
        System.out.println("[Project create]");
        System.out.println("enter name:");
        command = input.nextLine();
        projectList.add(new Project(command));
        System.out.println("ok");
    }

    private static void createTask() {
        String command;
        System.out.println("[Task create]");
        System.out.println("enter name:");
        command = input.nextLine();
        taskList.add(new Task(command));
        System.out.println("ok");
    }

    private static void clearProject() {
        projectList.clear();
        System.out.println("[All projects removed]");
    }

    private static void clearTask() {
        taskList.clear();
        System.out.println("[All tasks removed]");
    }

    private static void displayProjects(){
        int i = 0;
        System.out.println("[Project list]");
        for (Project project : projectList) {
            i++;
            System.out.println(i + ". " + project.getName());
        }
    }

    private static void displayTasks(){
        int i = 0;
        System.out.println("[Task list]");
        for (Task task : taskList) {
            i++;
            System.out.println(i + ". " + task.getName());
        }
    }
}