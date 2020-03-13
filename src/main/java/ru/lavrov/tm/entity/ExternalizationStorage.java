package ru.lavrov.tm.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@Getter
@Setter
@XmlRootElement(name = "ExternalizationStorage")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExternalizationStorage {

    @NotNull
    @XmlElement(name = "projects")
    private Collection<Project> projectList;
    @NotNull
    @XmlElement(name = "tasks")
    private Collection<Task> taskList;
    @NotNull
    @XmlElement(name = "users")
    private Collection<User> userList;

}
