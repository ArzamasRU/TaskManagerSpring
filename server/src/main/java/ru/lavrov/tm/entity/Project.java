package ru.lavrov.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IComparableEntity;
import ru.lavrov.tm.api.IEntity;
import ru.lavrov.tm.dto.ProjectDTO;
import ru.lavrov.tm.enumerate.Status;
import ru.lavrov.tm.exception.project.ProjectNotExistsException;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "app_project")
public final class Project extends AbstractEntity implements IEntity, IComparableEntity {

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
    private Status status = Status.PLANNED;

    @ManyToOne
    @NotNull
    private User user;

    @NotNull
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Collection<Task> tasks = new ArrayList<>();

    @NotNull
    public static ProjectDTO getProductDTO(@NotNull final Project project) {
        if (project == null)
            throw new ProjectNotExistsException();
        @NotNull final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setUserId(project.getUser().getId());
        projectDTO.setName(project.getName());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setStartDate(project.getStartDate());
        projectDTO.setFinishDate(project.getFinishDate());
        projectDTO.setStatus(project.getStatus());
        return projectDTO;
    }

    @Nullable
    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", description=" + description +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", userId='" + getUser().getId() + '\'';
    }
}
