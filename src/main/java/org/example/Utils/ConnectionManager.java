package org.example.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {

    private static final String URL_KEY = "db.url";
    private static final String NAME_KEY = "db.name";
    private static final String PASSWORD_KEY = "db.password";


    public static Connection open(){

        try {

            return DriverManager.getConnection(PropertiesUtil.get(URL_KEY), PropertiesUtil.get(NAME_KEY), PropertiesUtil.get(PASSWORD_KEY));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private ConnectionManager() {

    }

}
