package ru.lavrov.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IComparableEntity;
import ru.lavrov.tm.api.IEntity;
import ru.lavrov.tm.dto.TaskDTO;
import ru.lavrov.tm.enumerate.Status;
import ru.lavrov.tm.exception.task.TaskNotExistsException;

import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "task")
public final class Task extends AbstractEntity implements IEntity, IComparableEntity {

    @ManyToOne
    @NotNull
    private User user;
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
    private Status status = Status.PLANNED;

    @NotNull
    public TaskDTO getTaskDTO(@NotNull final Task task) {
        if (task == null)
            throw new TaskNotExistsException();
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
