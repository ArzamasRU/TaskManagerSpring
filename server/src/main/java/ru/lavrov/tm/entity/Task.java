package ru.lavrov.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import ru.lavrov.tm.api.entity.IComparableEntity;
import ru.lavrov.tm.api.entity.IEntity;
import ru.lavrov.tm.api.service.IProjectService;
import ru.lavrov.tm.api.service.IUserService;
import ru.lavrov.tm.dto.TaskDTO;
import ru.lavrov.tm.enumerate.Status;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "app_task")
public final class Task extends AbstractEntity implements IEntity, IComparableEntity {

    @Nullable
    private String name;
    @Nullable
    private String description;
    @Nullable
    private Date creationDate = new Date();
    @Nullable
    private Date startDate = null;
    @Nullable
    private Date finishDate = null;

    @Nullable
    @Enumerated(EnumType.STRING)
    private Status status = Status.PLANNED;

    @ManyToOne
    @NotNull
    private Project project;

    @ManyToOne
    @NotNull
    private User user;

    @Autowired
    static IUserService userService;

    @Autowired
    static IProjectService projectService;

    public Task(@NotNull User user, @Nullable String name, @NotNull Project project) {
        this.name = name;
        this.project = project;
        this.user = user;
    }

    public Task(@Nullable String name,
                @Nullable String description,
                @Nullable Date creationDate,
                @Nullable Date startDate,
                @Nullable Date finishDate,
                @Nullable Status status) {
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.status = status;
    }

    public static @Nullable TaskDTO getTaskDTO(@NotNull final Task task) {
        if (task == null)
            return null;
        @NotNull final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setProjectId(task.getProject().getId());
        taskDTO.setUserId(task.getUser().getId());
        taskDTO.setName(task.getName());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setCreationDate(task.getCreationDate());
        taskDTO.setStartDate(task.getStartDate());
        taskDTO.setFinishDate(task.getFinishDate());
        taskDTO.setStatus(task.getStatus());
        return taskDTO;
    }

    public static @Nullable Collection<TaskDTO> getTaskDTO(@NotNull final Collection<Task> taskList) {
        if (taskList == null)
            return null;
        @NotNull final Collection<TaskDTO> taskDTOList = new ArrayList<>();
        for (Task task : taskList) {
            taskDTOList.add(getTaskDTO(task));
        }
        return taskDTOList;
    }

    public static @Nullable Task getTaskFromDTO(@NotNull final TaskDTO taskDTO) {
        if (taskDTO == null)
            return null;
        @NotNull final Task task = new Task();
        task.setId(taskDTO.getId());
        task.setProject(projectService.findOne(taskDTO.getUserId(), taskDTO.getProjectId()));
        task.setUser(userService.findOne(taskDTO.getUserId()));
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setCreationDate(taskDTO.getCreationDate());
        task.setStartDate(taskDTO.getStartDate());
        task.setFinishDate(taskDTO.getFinishDate());
        task.setStatus(taskDTO.getStatus());
        return task;
    }

    public static @Nullable Collection<Task> getTaskFromDTO(@NotNull final Collection<TaskDTO> taskDTOList) {
        if (taskDTOList == null)
            return null;
        @NotNull final Collection<Task> taskList = new ArrayList<>();
        for (TaskDTO taskDTO : taskDTOList) {
            taskList.add(getTaskFromDTO(taskDTO));
        }
        return taskList;
    }

    @Nullable
    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", description=" + description +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", projectId='" + getProject().getId() + '\'' +
                ", userId='" + getUser().getId() + '\'';
    }
}
