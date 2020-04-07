package ru.lavrov.tm.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IEntity;
import ru.lavrov.tm.enumerate.Role;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "user")
public final class UserDTO implements IEntity {

    @NotNull
    private String id = UUID.randomUUID().toString();
    @Nullable
    private String login;
    @Nullable
    private String password;

    @Nullable
    private Role role;

    public UserDTO(@Nullable final String login, @Nullable final String password, @Nullable final Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @JsonIgnore
    public void setRole(@Nullable final String role) {
        @Nullable final Role curRole = Role.getByRole(role);
        if (curRole != null)
            this.role = curRole;
        else
            this.role = Role.valueOf(role);
    }

    public void setRole(@Nullable final Role role) {
        this.role = role;
    }

    @Nullable
    @Override
    public String toString() {
        return "login='" + login + '\'' +
                ", role=" + role;
    }
}
