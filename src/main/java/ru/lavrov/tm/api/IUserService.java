package ru.lavrov.tm.api;

import ru.lavrov.tm.entity.User;

public interface IUserService extends IService<User> {
    void createByLogin(String login, String password, String role);
    void updatePassword(String userId, String newPassword);
    void updateLogin(String userId, String newLogin);
}
