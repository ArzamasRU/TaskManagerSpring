package ru.lavrov.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.entity.IEntity;
import ru.lavrov.tm.dto.UserDTO;
import ru.lavrov.tm.enumerate.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "app_user")
public final class User extends AbstractEntity implements IEntity {

    @Column(unique = true)
    @Nullable
    private String login;

    @Nullable
    private String password;

    @Nullable
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Project> projects = new ArrayList<>();

    @NotNull
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
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

    public void setRole(@Nullable final Role role) {
        this.role = role;
    }

    @NotNull
    public static UserDTO getUserDTO(@NotNull final User user) {
        if (user == null)
            return null;
        @NotNull final UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    @NotNull
    public static User getUserFromDTO(@NotNull final UserDTO userDTO) {
        if (userDTO == null)
            return null;
        @NotNull final User user = new User();
        user.setId(userDTO.getId());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        return user;
    }

    @Nullable
    @Override
    public String toString() {
        return "login='" + login + '\'' +
                ", role=" + role;
    }
}
