package ru.lavrov.tm.entity;

import ru.lavrov.tm.role.Role;

import java.util.UUID;

public final class User {
    private String id = UUID.randomUUID().toString();;
    private String login;
    private String password;
    private Role role;

    public User() {
    }

    public User(final String login, final String password, final Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "login='" + login + '\'' +
                ", role=" + role;
    }
}
