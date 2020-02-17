import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        boolean exit = false;
        Scanner in = new Scanner(System.in);
        List<String> projectList = new ArrayList<String>();
        List<String> taskList = new ArrayList<String>();
        String str;
        int i = 0;

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
                    projectList.add(str);
                    System.out.println("ok");
                    break;
                case "project-clear":
                    projectList.clear();
                    System.out.println("[All projects removed]");
                    break;

                case "project-list":
                    i = 0;
                    System.out.println("[Project list]");
                    for (String name : projectList) {
                        i++;
                        System.out.println(i + ". " + name);
                    }
                    break;

                case "project-remove":
                    System.out.println("[Project remove]");
                    System.out.println("enter name:");
                    str = in.nextLine();
                    if (projectList.contains(str)){
//                    projectList.remove(projectList.indexOf(str));
                        projectList.remove(str);
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
                    taskList.add(str);
                    System.out.println("ok");
                    break;
                case "task-list":
                    i = 0;
                    System.out.println("[Task list]");
                    for (String name : taskList) {
                        i++;
                        System.out.println(i + ". " + name);
                    }
                    break;
                case "task-remove":
                    System.out.println("[task remove]");
                    System.out.println("enter name:");
                    str = in.nextLine();
                    if (taskList.contains(str)){
    //                    taskList.remove(taskList.indexOf(str));
                        taskList.remove(str);
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

//            if (str.equals("help")) {
//                System.out.println("a lot of information");
//            }
//
//            else if (str.equals("project-create")) {
//                System.out.println("[Project create]");
//                System.out.println("enter name:");
//                str = in.nextLine();
//                projectList.add(str);
//                System.out.println("ok");
//            }
//
//            else if (str.equals("project-clear")) {
//                projectList.clear();
//                System.out.println("[All projects removed]");
//            }
//
//            else if (str.equals("project-list")) {
//                int i = 0;
//                for (String name : projectList) {
//                    i++;
//                    System.out.println(i + ". " + name);
//                }
//            }
//
//            else if (str.equals("project-remove")) {
//                System.out.println("[Project remove]");
//                System.out.println("enter name:");
//                str = in.nextLine();
//                if (projectList.contains(str)){
////                    projectList.remove(projectList.indexOf(str));
//                    projectList.remove(str);
//                    System.out.println("ok");
//                } else
//                    System.out.println("project does not exist");
//            }
//
//            else if (str.equals("task-clear")) {
//                taskList.clear();
//                System.out.println("[All tasks removed]");
//            }
//
//            else if (str.equals("task-create")) {
//                System.out.println("[Task create]");
//                System.out.println("enter name:");
//                str = in.nextLine();
//                projectList.add(str);
//                System.out.println("ok");
//            }
//
//            else if (str.equals("task-list")) {
//                System.out.println("[Task list]");
//                int i = 0;
//                for (String name : taskList) {
//                    i++;
//                    System.out.println(i + ". " + name);
//                }
//            }
//
//            else if (str.equals("task-remove")) {
//                System.out.println("[task remove]");
//                System.out.println("enter name:");
//                str = in.nextLine();
//                if (taskList.contains(str)){
////                    taskList.remove(taskList.indexOf(str));
//                    taskList.remove(str);
//                    System.out.println("ok");
//                } else
//                    System.out.println("task does not exist");
//            }
//
//            else if (str.equals("exit")) {
//                exit = true;
//            }
//
//            else
//                System.out.println("incorrect args");
        }

        in.close();
        System.out.print("you left this wonderful program");
    }
}

