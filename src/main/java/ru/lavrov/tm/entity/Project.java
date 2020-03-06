package ru.lavrov.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IEntity;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public final class Project implements IEntity {
    @Nullable
    private String name;
    @Nullable
    private String id = UUID.randomUUID().toString();
    @Nullable
    private int description;
    @Nullable
    private Date startDate = new Date();
    @Nullable
    private Date finishDate = null;
    @Nullable
    private String userId;

    public Project(@Nullable final String name, @Nullable final String userId) {
        this.name = name;
        this.userId = userId;
    }

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
