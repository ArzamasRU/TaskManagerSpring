package ru.lavrov.tm.service;

import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.repository.TaskRepository;

import java.util.List;

public class TaskService {
    private TaskRepository taskRepository;
    private ProjectService projectService;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskService(TaskRepository taskRepository, ProjectService projectService) {
        this.taskRepository = taskRepository;
        this.projectService = projectService;
    }

    public void createTask(String command) throws Exception {
        if (command == null || command.isEmpty())
            throw new Exception("task name is empty or null");
        taskRepository.persist(new Task(command));
    }

    public void renameTask(String oldName, String newName) throws Exception {
        if (newName == null || newName.isEmpty())
            throw new Exception("task name is empty or null");
        if (oldName == null || oldName.isEmpty())
            throw new Exception("new task name is empty or null");
        Task task = findTaskByName(oldName);
        task.setName(newName);
    }

    public void clearTask() {
        taskRepository.removeAll();
    }

    public void removeTask(String taskName) throws Exception {
        Task task = findTaskByName(taskName);
        taskRepository.remove(task.getId());
    }

    public Task findTaskByName(String name) throws Exception {
        if (name == null || name.isEmpty())
            throw new Exception("task name is empty or null");

        Task currentTask = null;
        for (Task task: taskRepository.findAll()) {
            if (name.equals(task.getName())) {
                currentTask = task;
                break;
            }
        }

        if (currentTask == null)
            throw new Exception("task does not exist");

        return currentTask;
    }

    public void attachTask(String taskName, String projectName) throws Exception {
        Task task = findTaskByName(taskName);
        Project project = projectService.findProjectByName(projectName);
        task.setProjectId(project.getId());
    }

    public List<Task> getListTask() {
        return taskRepository.findAll();
    }
}
