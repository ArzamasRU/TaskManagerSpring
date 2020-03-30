package ru.lavrov.tm.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import static ru.lavrov.tm.constant.ConnectionConstant.CONNECTION_PROPERTIES_FILE;

public final class ConnectionUtil {

    @Nullable
    public static Connection getConnection() {
        @NotNull final Properties connectionProperties = new Properties();
        @Nullable FileInputStream fileInputStream;
        @Nullable Connection connection = null;
        try {
            fileInputStream = new FileInputStream(CONNECTION_PROPERTIES_FILE);
            connectionProperties.load(fileInputStream);
            @Nullable String url = connectionProperties.getProperty("url");
            @Nullable String user = connectionProperties.getProperty("login");
            @Nullable String password = connectionProperties.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
