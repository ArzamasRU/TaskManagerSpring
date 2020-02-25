package ru.lavrov.tm.service;

import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.repository.ProjectRepository;
import ru.lavrov.tm.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

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
        projectRepository.persist(new Project(command));
    }

    public void renameProject(String oldName, String newName) throws Exception {
        if (newName == null || newName.isEmpty())
            throw new Exception("project name is empty or null");
        if (oldName == null || oldName.isEmpty())
            throw new Exception("new project name is empty or null");
        Project project = findProjectByName(oldName);
        project.setName(newName);
    }

    public void clearProject() {
        projectRepository.removeAll();
    }

    public void removeProject(String projectName) throws Exception {
        Project project = findProjectByName(projectName);
        projectRepository.remove(project.getId());
        for (Task task: taskRepository.findAll()) {
            if (project.getId().equals(task.getProjectId()))
                taskRepository.remove(task.getId());
        }
    }

    public Project findProjectByName(String name) throws Exception {
        if (name == null || name.isEmpty())
            throw new Exception("project name is empty or null");

        Project currentProject = null;
        for (Project project: projectRepository.findAll()) {
            if (name.equals(project.getName())) {
                currentProject = project;
                break;
            }
        }

        if (currentProject == null)
            throw new Exception("project does not exist");

        return currentProject;
    }

    public List<Task> displayProjectTasks(String projectName) throws Exception {
        List<Task> list = new ArrayList();
        Project project = findProjectByName(projectName);

        for (Task task: taskRepository.findAll()) {
            if (task.getProjectId().equals(project.getId())) {
                list.add(task);
            }
        }

        return list;
    }

    public List<Project> getListProject() {
        return projectRepository.findAll();
    }
}
