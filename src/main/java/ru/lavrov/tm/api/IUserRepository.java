package ru.lavrov.tm.api;

import ru.lavrov.tm.entity.User;

public interface IUserRepository extends IRepository<User> {
    void updatePassword(String userId, String newPassword);
    void updateLogin(String userId, String newLogin);
    User findEntityByName(String entityName, String userId);
}