package ru.lavrov.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IEntity;
import ru.lavrov.tm.dto.UserDTO;
import ru.lavrov.tm.enumerate.Role;
import ru.lavrov.tm.exception.user.UserNotExistsException;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "app_user")
public final class User extends AbstractEntity implements IEntity {

    @Nullable
    private String login;
    @Nullable
    private String password;
    @Nullable
    private Role role;

    @NotNull
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Project> projects = new ArrayList<>();

    @NotNull
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    public User(@Nullable String login, @Nullable String password, @Nullable Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public void setRole(@Nullable final String role) {
        @Nullable final Role curRole = Role.getByRole(role);
        if (curRole != null)
            this.role = curRole;
        else
            try {
                this.role = Role.valueOf(role);
            } catch (NullPointerException e) {
                this.role = null;
            }
    }

    @NotNull
    public static UserDTO getUserDTO(@NotNull final User user) {
        if (user == null)
            throw new UserNotExistsException();
        @NotNull final UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    @Nullable
    @Override
    public String toString() {
        return "login='" + login + '\'' +
                ", role=" + role;
    }
}
