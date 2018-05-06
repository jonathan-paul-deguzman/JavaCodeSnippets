package com.jonathanpaul.javacodesnippets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * General JDBC info:
 *
 * - JDBC is an API for Java that defines how a client accesses a database
 * - JDBC provides methods for querying and updating data in the database
 * - JDBC supports two-tiered and three-tiered models:
 *      - two-tired: Client talks directly to the database (requires JDBC driver), aka client-server
 *      - three-tiered (preferred): Application server acts as middle-man for client and database
 *              - JDBC is used in the middle tier application server
 * - JDBC has two layers: JDBC API and JDBC Driver API
 *      - JDBC API: supports application-To-JDBC Manager connection
 *      - JDBC Driver API: supports JDBC Manager-to-Driver connection
 *      - A driver manager manages a set of JDBC drivers
 *      - For example, if our Java app wants to access the Oracle database, we need to use the Oracle JDBC driver
 *      - Class.forName("path.to.Driver") registers a driver with the driver manager, but is DEPRECATED in JDBC 4.0
 * - Types of JDBC drivers:
 *      - JDBC-ODBC bridge: converts JDBC to ODBC driver method calls which interact with the database
 *              - easy to use, but takes a performance hit
 *      - Native-API driver: Native API driver converts JDBC method calls into native database API calls
 *              - faster than ODBC, but not type safe and platform dependent
 *      - Middleware driver: Network protocol driver converts java method calls into middleware specific calls, which then converts to database specific calls
 *              - since client talks to middleware server, it doesn't need to be changed for any DB, but slow performance
 *      - Pure Java driver: JDBC method calls are converted into direct DB calls
 *              - platform independent and fast performance, but drivers are database dependent
 * - Which drivers should I use?
 *      - If you are accessing one of the DBs like Oracle, MySQL, etc. use the Pure Java driver
 *      - If you are accessing multiple DBs at the same time, use Middleware driver
 *      - Native-API and JDBC-ODBC are used less frequently. Native-API drivers should be our 3rd option
 */

public class Main {

    public static void main(String[] args) {
        establishDatabaseConnection();
    }

    private static void establishDatabaseConnection() {
        Connection connection = null;
        try {
            connection = DBUtil.getConnection(DBType.MYSQLDB);
            System.out.println("Connection established to MySQL Database");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
