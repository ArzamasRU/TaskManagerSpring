package ru.lavrov.tm.api;

public interface UserService<T> extends Service<T> {
    void createByLogin(String login, String password, String role);
    void updatePassword(String userId, String newPassword);
    void updateLogin(String userId, String newLogin);
}
