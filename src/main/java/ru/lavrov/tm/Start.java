package ru.lavrov.tm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        boolean exit = false;
        Scanner in = new Scanner(System.in);
        List<Project> projectList = new ArrayList<>();
        List<Task> taskList = new ArrayList<>();
        String str;
        int i = 0;
        boolean objFound = false;

        System.out.println("*** Welcome to task manager ***");

        while (!exit) {
            str = in.nextLine();

            switch (str){
                case "help":
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
                    break;
                case "project-create":
                    System.out.println("[Project create]");
                    System.out.println("enter name:");
                    str = in.nextLine();
                    projectList.add(new Project(str));
                    System.out.println("ok");
                    break;
                case "project-clear":
                    projectList.clear();
                    System.out.println("[All projects removed]");
                    break;

                case "project-list":
                    i = 0;
                    System.out.println("[Project list]");
                    for (Project prj : projectList) {
                        i++;
                        System.out.println(i + ". " + prj.name);
                    }
                    break;

                case "project-remove":
                    System.out.println("[Project remove]");
                    System.out.println("enter name:");
                    str = in.nextLine();
                    objFound = false;
                    for (Project prj : projectList) {
                        if (prj.name.equals(str)) {
                            objFound = true;
                            projectList.remove(prj);
                            break;
                        }
                    }
                    if (objFound){
                        System.out.println("ok");
                    } else
                        System.out.println("project does not exist");
                    break;
                case "task-clear":
                    taskList.clear();
                    System.out.println("[All tasks removed]");
                    break;
                case "task-create":
                    System.out.println("[Task create]");
                    System.out.println("enter name:");
                    str = in.nextLine();
                    taskList.add(new Task(str));
                    System.out.println("ok");
                    break;
                case "task-list":
                    i = 0;
                    System.out.println("[Task list]");
                    for (Task task : taskList) {
                        i++;
                        System.out.println(i + ". " + task.name);
                    }
                    break;
                case "task-remove":
                    System.out.println("[task remove]");
                    System.out.println("enter name:");
                    str = in.nextLine();
                    objFound = false;
                    for (Task task : taskList) {
                        if (task.name.equals(str)) {
                            objFound = true;
                            taskList.remove(task);
                            break;
                        }
                    }
                    if (objFound){
                        System.out.println("ok");
                    } else
                        System.out.println("task does not exist");
                    break;
                case "exit":
                    exit = true;
                    break;
                default:
                    System.out.println("incorrect args");
                    break;
            }
        }

        in.close();
        System.out.print("you left this wonderful program");
    }
}