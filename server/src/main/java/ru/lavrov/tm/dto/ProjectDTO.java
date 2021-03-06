package ru.lavrov.tm.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.entity.IComparableEntity;
import ru.lavrov.tm.api.entity.IEntity;
import ru.lavrov.tm.enumerate.Status;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "project")
public final class ProjectDTO implements IEntity, IComparableEntity {

    @Nullable
    private String name;
    @NotNull
    private String id = UUID.randomUUID().toString();
    @Nullable
    private String description;
    @Nullable
    private Date creationDate;
    @Nullable
    private Date startDate;
    @Nullable
    private Date finishDate;
    @Nullable
    private String userId;
    @Nullable
    private Status status = Status.PLANNED;

    @Nullable
    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", description=" + description +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", userId='" + userId + '\'';
    }
}
