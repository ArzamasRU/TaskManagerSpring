package ru.lavrov.tm.service;

import ru.lavrov.tm.Utils;
import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.repository.ProjectRepository;
import ru.lavrov.tm.repository.TaskRepository;
import ru.lavrov.tm.repository.TaskRepository;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

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
        taskRepository.merge(new Task(command));
    }

    public void clearTask() {
        taskRepository.removeAll();
    }

    public Map<String, Task> displayTasks(){
        return taskRepository.findAll();
    }

    public void removeTask(String taskName) throws Exception {
        Task task = findTaskByName(taskName);
        taskRepository.remove(task.getId());
    }

    public void updateTaskStartDate(String date, String taskName) throws Exception {
        updateTaskDate("start", date, taskName);
    }

    public void updateTaskFinishDate(String date, String taskName) throws Exception {
        updateTaskDate("finish", date, taskName);
    }

    public void updateTaskDate(String typeOfDate, String date, String taskName) throws Exception {
        Task task = findTaskByName(taskName);
        if (task == null)
            throw new Exception("task does not exist");
        Date newDate;
        try {
            newDate = Utils.formatter.parse(date);
        } catch (ParseException e) {
            throw new Exception("Incorrect date format entered!");
        }
        if (typeOfDate.equals("start"))
            task.setStartDate(newDate);
        else
            task.setFinishDate(newDate);
    }

    public Task findTaskByName(String name) throws Exception {
        if (name == null || name.isEmpty())
            throw new Exception("task name is empty or null");

        Task task = null;

        for (String key: taskRepository.findAll().keySet()){
            task = taskRepository.FindOne(key);
            if (name.equals(task.getName()))
                break;
        }

        if (task == null)
            throw new Exception("task does not exist");

//        for (Map.Entry<String, Task> entry: taskRepository.findAll().entrySet()){
//            task = entry.getValue();
//            if (name.equals(task.getName()))
//                break;
//        }

        return task;
    }

    public void attachTask(String taskName, String projectName) throws Exception {
        Task task = findTaskByName(taskName);
        Project project = projectService.findProjectByName(projectName);
        task.setProjectId(project.getId());
    }
}
