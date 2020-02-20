package ru.lavrov.tm.service;

import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.repository.ProjectRepository;

import java.text.ParseException;
import java.util.Iterator;
import java.util.Scanner;

public class ProjectService {
    ProjectRepository projectRepository = new ProjectRepository();
    private final Scanner input = new Scanner(System.in);

    public void createProject() {
        System.out.println("[Project create]");
        System.out.println("enter name:");
        String command = input.nextLine();
        projectRepository.merge(new Project(command));
        System.out.println("ok");
    }

    public void clearProject() {
        projectRepository.removeAll();
        System.out.println("[All projects removed]");
    }

    public void displayProjects(){
        System.out.println("[Project list]");
        System.out.println(projectRepository.findAll());
    }

    public void removeProject() {
        System.out.println("[project remove]");
        System.out.println("enter name:");
        String command = input.nextLine();

        for (String key: projectRepository.findAll().keySet()){
            if (command.equals(projectRepository.FindOne(key).getName()))
                projectRepository.remove(key);
        }
    }

    public void renameProject(){
        System.out.println("[Project rename]");
        System.out.println("Enter the name of the project that needs a new name:");
        String command = input.nextLine();

        for (String key: projectRepository.findAll().keySet()){
            if (command.equals(projectRepository.FindOne(key).getName()))
                projectRepository.FindOne(key).setName(command);
        }
    }

    public void updateProjectStartDate() {
        System.out.println("[project start date update]");
        System.out.println("enter project name:");
        String command = input.nextLine();

        for (String key: projectRepository.findAll().keySet()){
            if (command.equals(projectRepository.FindOne(key).getName()))
                projectRepository.FindOne(key).setName(command);
        }

        Project project = findProjectByName(command);
        if (project != null) {
            System.out.println("enter start date like 'dd.MM.yyyy':");
            command = input.nextLine();
            try {
                project.setStartDate(formatter.parse(command));
            } catch (ParseException e) {
                System.out.println("Incorrect date format entered!");
            }
            System.out.println("ok");
        }
    }
}
