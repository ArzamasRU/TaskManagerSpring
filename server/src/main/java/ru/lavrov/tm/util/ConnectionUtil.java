package ru.lavrov.tm.util;

import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static ru.lavrov.tm.service.AppPropertyServiceImpl.appProperties;

public final class ConnectionUtil {

    @Nullable
    public static Connection getConnection() {
        @Nullable Connection connection = null;
        try {
            @Nullable String url = appProperties.getProperty("url");
            @Nullable String user = appProperties.getProperty("login");
            @Nullable String password = appProperties.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
