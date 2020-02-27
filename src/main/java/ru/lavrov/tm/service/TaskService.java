package ru.lavrov.tm.service;

import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.entity.User;
import ru.lavrov.tm.exception.projectException.ProjectNameIsInvalidException;
import ru.lavrov.tm.exception.projectException.ProjectNotExistsException;
import ru.lavrov.tm.exception.taskException.TaskNameExistsException;
import ru.lavrov.tm.exception.taskException.TaskNameIsInvalidException;
import ru.lavrov.tm.exception.taskException.TaskNotExistsException;
import ru.lavrov.tm.exception.userException.UserIsNotAuthorizedException;
import ru.lavrov.tm.repository.ProjectRepository;
import ru.lavrov.tm.repository.TaskRepository;

import java.util.Collection;

public class TaskService {
    private ProjectRepository projectRepository;
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    public void persist(String taskName) throws RuntimeException {
        if (taskName == null || taskName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (taskRepository.findTaskByName(taskName) != null)
            throw new TaskNameExistsException();
        taskRepository.persist(new Task(taskName));
    }

    public void merge(String taskName) throws RuntimeException {
        if (taskName == null || taskName.isEmpty())
            throw new TaskNameIsInvalidException();
        taskRepository.merge(new Task(taskName));
    }

    public void removeAll() {
        taskRepository.removeAll();
    }

    public Collection<Task> findAll(){
        return taskRepository.findAll();
    }

    public Collection<Task> findAllByUser(User sessionUser){
        if (sessionUser == null)
            throw new UserIsNotAuthorizedException();
        return taskRepository.findAllByUser(sessionUser);
    }

    public void removeTask(String taskName) throws RuntimeException {
        if (taskName == null || taskName.isEmpty())
            throw new TaskNameIsInvalidException();
        Task task = taskRepository.findTaskByName(taskName);
        if (task == null)
            throw new TaskNotExistsException();
        taskRepository.remove(task.getId());

    }

    public void attachTaskToProject(String taskName, String projectName) throws RuntimeException {
        if (taskName == null || taskName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        Task task = taskRepository.findTaskByName(taskName);
        if (task == null)
            throw new TaskNotExistsException();
        Project project = projectRepository.findProjectByName(projectName);
        if (project == null)
            throw new ProjectNotExistsException();
        taskRepository.attachTaskToProject(task, project);
    }

    public void attachTaskToUser(String taskName, User sessionUser) throws RuntimeException {
        if (taskName == null || taskName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (sessionUser == null)
            throw new UserIsNotAuthorizedException();
        Task task = taskRepository.findTaskByName(taskName);
        if (task == null)
            throw new TaskNotExistsException();
        taskRepository.attachTaskToUser(task, sessionUser);
    }

    public void renameTask(String oldName, String newName) throws RuntimeException {
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            throw new TaskNameIsInvalidException();
        taskRepository.renameTask(oldName, newName);
    }
}
