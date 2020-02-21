package ru.lavrov.tm.service;

import ru.lavrov.tm.Utils;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.repository.ProjectRepository;
import ru.lavrov.tm.repository.TaskRepository;

import java.text.ParseException;
import java.util.*;

public class ProjectService {
    private final ProjectRepository projectRepository = new ProjectRepository();
    private final TaskRepository taskRepository = new TaskRepository();
    private final Scanner input = new Scanner(System.in);

    public void createProject(String command) {
        projectRepository.merge(new Project(command));
    }

    public void clearProject() {
        projectRepository.removeAll();
    }

    public Map<String, Project> displayProjects(){
        return projectRepository.findAll();
    }

    public void removeProject(String projectName) throws Exception {
        Project project = findProjectByName(projectName);
        projectRepository.remove(project.getId());
        for (Map.Entry<String, Task> entry: taskRepository.findAll().entrySet()){
            Task task = entry.getValue();
            if (task.getProjectId().equals(project.getId()))
                taskRepository.remove(task.getId());
        }
    }

    public void updateProjectStartDate(String date, String projectName) throws Exception {
        updateProjectDate("start", date, projectName);
    }

    public void updateProjectFinishDate(String date, String projectName) throws Exception {
        updateProjectDate("finish", date, projectName);
    }

    public void updateProjectDate(String typeOfDate, String date, String projectName) throws Exception {
        Project project = findProjectByName(projectName);
        if (project == null)
            throw new Exception("project does not exist");
        Date newDate;
        try {
            newDate = Utils.formatter.parse(date);
        } catch (ParseException e) {
            throw new Exception("Incorrect date format entered!");
        }
        if (typeOfDate.equals("start"))
            project.setStartDate(newDate);
        else
            project.setFinishDate(newDate);
    }

    public void displayProjectStartDate() throws Exception {
        displayProjectDate("start");
    }

    public void displayProjectFinishDate() throws Exception {
        displayProjectDate("finish");
    }

    private void displayProjectDate(String typeOfDate) throws Exception {
        System.out.println("[project " + typeOfDate + " date]");
        System.out.println("enter project name:");
        String command = input.nextLine();
        if (typeOfDate.equals("start"))
            findProjectByName(command).getStartDate();
        else
            findProjectByName(command).getFinishDate();
    }

    public Project findProjectByName(String name) throws Exception {
        Project project = null;

        for (String key: projectRepository.findAll().keySet()){
            project = projectRepository.FindOne(key);
            if (name.equals(project.getName()))
                break;
        }

        if (project == null)
            throw new Exception("project does not exist");

//        for (Map.Entry<String, Project> entry: projectRepository.findAll().entrySet()){
//            project = entry.getValue();
//            if (name.equals(project.getName()))
//                break;
//        }

        return project;
    }

    public List<Task> displayProjectTasks(String projectName) throws Exception {
        List<Task> list = new ArrayList();
        Project project = findProjectByName(projectName);

        for (String key: taskRepository.findAll().keySet()){
            Task task = taskRepository.FindOne(key);
            if (task.getProjectId().equals(project.getId())) {
                list.add(task);
            }
        }
        return list;
    }
}
