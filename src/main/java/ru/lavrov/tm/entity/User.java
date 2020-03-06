package ru.lavrov.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.lavrov.tm.api.IEntity;
import ru.lavrov.tm.role.Role;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public final class User implements IEntity {
    @Nullable
    private String id = UUID.randomUUID().toString();;
    @Nullable
    private String login;
    @Nullable
    private String password;
    @Nullable
    private Role role;

    public User(@Nullable final String login, @Nullable final String password, @Nullable final Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Nullable
    @Override
    public String getUserId() {
        return getId();
    }

    @Override
    public void setUserId(final String id) {
        setId(id);
    }

    @Nullable
    @Override
    public String getName() {
        return getLogin();
    }

    @Override
    public void setName(final String login) {
        setLogin(login);
    }

    @Nullable
    @Override
    public String toString() {
        return "login='" + login + '\'' +
                ", role=" + role;
    }
}
