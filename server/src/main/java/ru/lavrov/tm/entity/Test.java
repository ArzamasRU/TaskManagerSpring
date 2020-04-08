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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "app_test")
public final class Test  {

    @Id
    @NotNull
    protected String id;
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
}
