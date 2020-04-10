package ru.lavrov.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IComparableEntity;
import ru.lavrov.tm.api.IEntity;
import ru.lavrov.tm.dto.ProjectDTO;
import ru.lavrov.tm.dto.TaskDTO;
import ru.lavrov.tm.enumerate.Status;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;
import ru.lavrov.tm.exception.task.TaskNotExistsException;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
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
    private String projectId = null;

    @Nullable
    @Enumerated(EnumType.STRING)
    private Status status = Status.PLANNED;

    @ManyToOne
    @Nullable
    private Project project;

    @ManyToOne
    @NotNull
    private User user;

    public Task(@NotNull User user, @Nullable String name, @Nullable String projectId) {
        this.name = name;
        this.projectId = projectId;
        this.user = user;
    }

    public static @Nullable TaskDTO getTaskDTO(@NotNull final Task task) {
        if (task == null)
            return null;
        @NotNull final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setProjectId(task.getProjectId());
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

    @Nullable
    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", description=" + description +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", projectId='" + projectId + '\'' +
                ", userId='" + getUser().getId() + '\'';
    }
}
