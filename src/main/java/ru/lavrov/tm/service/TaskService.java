package ru.lavrov.tm.service;

import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.entity.User;
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

    public void attachTaskToUser(String taskName, String login) throws RuntimeException {
        User user = userRepository.findUserByLogin(login);
        attachTaskToUser(taskName, user);
    }

    public void detachTaskfromUser(User sessionUser, String taskName){
        if (sessionUser == null)
            throw new UserIsNotAuthorizedException();
        if (taskName == null || taskName.isEmpty())
            throw new TaskNameIsInvalidException();
        Task task = taskRepository.findTaskByName(taskName);
        if (task == null)
            throw new TaskNotExistsException();
        task.setUserId(null);
    }

    public void detachTaskfromUser(String login, String taskName){
        User user = userRepository.findUserByLogin(login);
        detachTaskfromUser(user, taskName);
    }

    public void detachTaskfromProject(String taskName){
        if (taskName == null || taskName.isEmpty())
            throw new TaskNameIsInvalidException();
        Task task = taskRepository.findTaskByName(taskName);
        if (task == null)
            throw new TaskNotExistsException();
        task.setProjectId(null);
    }

    public void renameTask(String oldName, String newName) throws RuntimeException {
        if (newName == null || newName.isEmpty() || oldName == null || oldName.isEmpty())
            throw new TaskNameIsInvalidException();
        taskRepository.renameTask(oldName, newName);
    }
}
