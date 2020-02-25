package ru.lavrov.tm.service;

import ru.lavrov.tm.Util.DateUtil;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.repository.ProjectRepository;
import ru.lavrov.tm.repository.TaskRepository;

import java.text.ParseException;
import java.util.*;

public class ProjectService {
    private ProjectRepository projectRepository;
    private TaskRepository taskRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public ProjectService(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    public void createProject(String command) throws Exception {
        if (command == null || command.isEmpty())
            throw new Exception("project name is empty or null");
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
            newDate = DateUtil.formatter.parse(date);
        } catch (ParseException e) {
            throw new Exception("Incorrect date format entered!");
        }
        if (typeOfDate.equals("start"))
            project.setStartDate(newDate);
        else
            project.setFinishDate(newDate);
    }

    public Project findProjectByName(String name) throws Exception {
        if (name == null || name.isEmpty())
            throw new Exception("project name is empty or null");
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
