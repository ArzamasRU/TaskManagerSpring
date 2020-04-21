package ru.lavrov.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import ru.lavrov.tm.api.entity.IComparableEntity;
import ru.lavrov.tm.api.entity.IEntity;
import ru.lavrov.tm.api.service.IUserService;
import ru.lavrov.tm.dto.ProjectDTO;
import ru.lavrov.tm.enumerate.Status;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
    @Enumerated(EnumType.STRING)
    private Status status = Status.PLANNED;

    @ManyToOne
    @NotNull
    private User user;

    @NotNull
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Collection<Task> tasks = new ArrayList<>();

    @Autowired
    static IUserService userService;

    public Project(@Nullable String name, @NotNull User user) {
        this.name = name;
        this.user = user;
    }

    public Project(@Nullable String name,
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

    public static @Nullable ProjectDTO getProjectDTO(@NotNull final Project project) {
        if (project == null)
            return null;
        @NotNull final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setUserId(project.getUser().getId());
        projectDTO.setName(project.getName());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setCreationDate(project.getCreationDate());
        projectDTO.setStartDate(project.getStartDate());
        projectDTO.setFinishDate(project.getFinishDate());
        projectDTO.setStatus(project.getStatus());
        return projectDTO;
    }

    public static @Nullable Collection<ProjectDTO> getProjectDTO(@NotNull final Collection<Project> projectList) {
        if (projectList == null)
            return null;
        @NotNull final Collection<ProjectDTO> projectDTOList = new ArrayList<>();
        for (Project project : projectList) {
            projectDTOList.add(getProjectDTO(project));
        }
        return projectDTOList;
    }

    public static @Nullable Project getProjectFromDTO(@NotNull final ProjectDTO projectDTO) {
        if (projectDTO == null)
            return null;
        @NotNull final Project project = new Project();
        project.setId(project.getId());
        project.setUser(userService.findOne(projectDTO.getUserId()));
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setCreationDate(projectDTO.getCreationDate());
        project.setStartDate(projectDTO.getStartDate());
        project.setFinishDate(projectDTO.getFinishDate());
        project.setStatus(projectDTO.getStatus());
        return project;
    }

    public static @Nullable Collection<Project> getProjectFromDTO(@NotNull final Collection<ProjectDTO> projectDTOList) {
        if (projectDTOList == null)
            return null;
        @NotNull final Collection<Project> projectList = new ArrayList<>();
        for (ProjectDTO projectDTO : projectDTOList) {
            projectList.add(getProjectFromDTO(projectDTO));
        }
        return projectList;
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
