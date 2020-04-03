package ru.lavrov.tm.dto;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@Getter
@Setter
@XmlRootElement(name = "ExternalizationStorage")
@XmlAccessorType(XmlAccessType.FIELD)
public final class ExternalizationStorage {

    @Nullable
    @XmlElement(name = "projects")
    private Collection<Project> projectList;
    @Nullable
    @XmlElement(name = "tasks")
    private Collection<Task> taskList;
    @Nullable
    @XmlElement(name = "users")
    private Collection<User> userList;

}
