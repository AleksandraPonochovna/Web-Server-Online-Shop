package util;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/db" +
            "?verifyServerCertificate=false" +
            "&useSSL=false" +
            "&requireSSL=false" +
            "&useLegacyDatetimeCode=false" +
            "&amp" +
            "&serverTimezone=UTC";
    private static final String login = "root";
    private static final String password = "aleksandra";
    private static final Logger logger = Logger.getLogger(DBConnector.class);

    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(dbUrl, login, password);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("The connection with DB had a error SQLException or ClassNotFoundException.");
        }
        return null;
    }

}
