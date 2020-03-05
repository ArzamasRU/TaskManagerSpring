//package ru.lavrov.tm.service;
//
//import ru.lavrov.tm.api.ITaskRepository;
//import ru.lavrov.tm.api.ITaskService;
//import ru.lavrov.tm.api.IUserRepository;
//import ru.lavrov.tm.entity.Task;
//import ru.lavrov.tm.exception.task.TaskNotExistsException;
//import ru.lavrov.tm.exception.user.UserIsNotAuthorizedException;
//import ru.lavrov.tm.repository.AbstractProjectRepository;
//
//import java.util.Collection;
//
//public abstract class AbstractTaskService implements ITaskService<Task> {
//    protected final AbstractProjectRepository projectRepository;
//    protected final ITaskRepository taskRepository;
//    protected final IUserRepository userRepository;
//
//    public AbstractTaskService(final AbstractProjectRepository projectRepository, final ITaskRepository taskRepository,
//                               final IUserRepository userRepository) {
//        this.projectRepository = projectRepository;
//        this.taskRepository = taskRepository;
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public void persist(final Task task) throws RuntimeException {
//        if (task == null)
//            throw new TaskNotExistsException();
//        taskRepository.persist(task);
//    }
//
//    @Override
//    public void merge(final Task task) throws RuntimeException {
//        if (task == null)
//            throw new TaskNotExistsException();
//        taskRepository.merge(task);
//    }
//
//    @Override
//    public void remove(final String taskId, final String userId) {
//        if (userId == null || userId.isEmpty())
//            throw new UserIsNotAuthorizedException();
//        if (taskId == null || taskId.isEmpty())
//            throw new TaskNotExistsException();
//        taskRepository.remove(taskId, userId);
//    }
//
//    @Override
//    public void removeAll(final String userId) {
//        if (userId == null || userId.isEmpty())
//            throw new UserIsNotAuthorizedException();
//        taskRepository.removeAll(userId);
//    }
//
//    @Override
//    public Collection<Task> findAll(final String userId){
//        if (userId == null || userId.isEmpty())
//            throw new UserIsNotAuthorizedException();
//        return taskRepository.findAll(userId);
//    }
//}
