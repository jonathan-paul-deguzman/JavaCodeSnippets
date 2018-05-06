package com.jonathanpaul.javacodesnippets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static String oracleUsername = "hr";
    private static String oraclePassword = "hr";
    private static String mySqlUsername = "root";
    private static String mySqlPassword = "root";
    private static String oracleUrl = "jdbc:oracle:thin:@localhost:1521:xe"; // OracleDB need's to be run on Linux/Windows
    private static String mySqlUrl = "jdbc:mysql://localhost:3306/sandbox"; // TODO: Get this connection working

    public static Connection getConnection(DBType dbType) throws SQLException {
        switch (dbType) {
            case ORACLEDB:
                return DriverManager.getConnection(oracleUrl, oracleUsername, oraclePassword);
            case MYSQLDB:
                return DriverManager.getConnection(mySqlUrl, mySqlUsername, mySqlPassword);
            default:
                return null;
        }
    }
}
