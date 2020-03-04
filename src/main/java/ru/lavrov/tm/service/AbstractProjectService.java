package ru.lavrov.tm.service;

import ru.lavrov.tm.api.ProjectRepository;
import ru.lavrov.tm.api.ProjectService;
import ru.lavrov.tm.api.TaskRepository;
import ru.lavrov.tm.api.UserRepository;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;

import java.util.Collection;

public abstract class AbstractProjectService<Project> implements ProjectService<Project> {
    protected final ProjectRepository projectRepository;
    protected final TaskRepository taskRepository;
    protected final UserRepository userRepository;

    public AbstractProjectService(final ProjectRepository projectRepository, final TaskRepository taskRepository, final UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void persist(final Project project) throws RuntimeException {
        if (project == null)
            throw new ProjectNotExistsException();
        projectRepository.persist(project);
    }

    @Override
    public void merge(final Project project) {
        if (project == null)
            throw new ProjectNotExistsException();
        projectRepository.persist(project);
    }

    @Override
    public void remove(final String projectId, final String userId) {
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        if (projectId == null)
            throw new ProjectNotExistsException();
        projectRepository.remove(projectId, userId);
    }

    @Override
    public void removeAll(final String userId) {
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        projectRepository.removeAll(userId);
    }

    @Override
    public Collection<Project> findAllByUser(final String userId){
        if (userId == null)
            throw new UserIsNotAuthorizedException();
        return projectRepository.findAllByUser(userId);
    }
}
