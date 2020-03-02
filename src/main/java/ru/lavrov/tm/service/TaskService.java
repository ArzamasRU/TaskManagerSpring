package ru.lavrov.tm.service;

import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.exception.project.ProjectNameIsInvalidException;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.task.TaskNameExistsException;
import ru.lavrov.tm.exception.task.TaskNameIsInvalidException;
import ru.lavrov.tm.exception.task.TaskNotExistsException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
import ru.lavrov.tm.repository.ProjectRepository;
import ru.lavrov.tm.repository.TaskRepository;
import ru.lavrov.tm.repository.UserRepository;

import java.util.Collection;

public class TaskService {
    private ProjectRepository projectRepository;
    private TaskRepository taskRepository;
    private UserRepository userRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public void createByName(String taskName, String projectName, String userId) throws RuntimeException {
        if (taskName == null || taskName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        Project project = projectRepository.findProjectByName(projectName);
        if (project == null)
            throw new ProjectNotExistsException();
        if (!project.getUserId().equals(userId))
            throw new ProjectNotExistsException();
        if (taskRepository.findProjectTaskByName(taskName, project.getId(), userId) != null)
            throw new TaskNameExistsException();
        persist(new Task(taskName, project.getId(), userId));
    }

    public void persist(Task task) throws RuntimeException {
        if (task == null)
            throw new TaskNotExistsException();
        taskRepository.persist(task);
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

    public Collection<Task> findAllByUser(String userId){
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        return taskRepository.findAllByUser(userId);
    }

    public void removeTaskByName(String taskName, String userId) throws RuntimeException {
        if (taskName == null || taskName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        Task task = taskRepository.findTaskByName(taskName);
        if (task == null)
            throw new TaskNotExistsException();
        if (!task.getUserId().equals(userId))
            throw new TaskNotExistsException();
        taskRepository.remove(task.getId());
    }

    public void renameTask(String projectName, String oldName, String newName, String userId) throws RuntimeException {
        if (projectName == null || projectName.isEmpty())
            throw new ProjectNameIsInvalidException();
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            throw new TaskNameIsInvalidException();
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        Project project = projectRepository.findProjectByName(newName);
        if (project == null)
            throw new ProjectNotExistsException();
        Task task = taskRepository.findTaskByName(oldName);
        if (task == null)
            throw new TaskNotExistsException();
        if (taskRepository.findProjectTaskByName(newName, project.getId(), userId) != null)
            throw new TaskNotExistsException();
        taskRepository.renameTask(task.getId(), newName);
    }
}
