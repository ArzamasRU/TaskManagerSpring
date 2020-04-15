package ru.lavrov.tm.api.repository;

import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lavrov.tm.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {

    @Transactional
    @Modifying
    @Query("UPDATE User SET password = :newPassword WHERE id = :userId")
    void updatePassword(@Param("userId") @Nullable String userId,
                        @Param("newPassword") @Nullable String newPassword);

    @Transactional
    @Modifying
    @Query("UPDATE User SET login = :newLogin WHERE id = :userId")
    void updateLogin(@Param("userId") @Nullable String userId,
                     @Param("newLogin") @Nullable String newLogin);

    @Nullable User findByLogin(@Nullable String login);
}