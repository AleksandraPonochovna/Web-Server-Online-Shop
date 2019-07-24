package dao.impl;

import dao.CodeDao;
import model.Code;
import model.User;
import org.apache.log4j.Logger;
import util.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class CodeDaoImpl implements CodeDao {
    
    private static final Logger logger = Logger.getLogger(CodeDaoImpl.class);
    private static final String ADD_CODE_IN_DB = "INSERT INTO code (code, id_user) VALUES ('%s', %d)";
    private static final String GET_LAST_CODE_FOR_USER_FROM_DB = "SELECT * FROM code WHERE " +
            "id_user = %d ORDER BY id DESC LIMIT 1";

    @Override
    public void add(Code code) {
        try (Connection connection = DBConnector.connect()) {
            String sql = String.format(ADD_CODE_IN_DB, code.getCode(), code.getIdUser());
            Statement statement = connection.createStatement();
            statement.execute(sql);
            logger.info("Code  " + code + " added in DB.");
        } catch (SQLException e) {
            logger.info("Code " + code + " can't be added in DB.");
        }
    }

    @Override
    public Optional<Code> getLastCodeForUser(User user) {
        try (Connection connection = DBConnector.connect()) {
            String sql = String.format(GET_LAST_CODE_FOR_USER_FROM_DB, user.getId());
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Code code = new Code(
                        resultSet.getLong("id"),
                        resultSet.getString("code"),
                        resultSet.getLong("id_user"));
                return Optional.of(code);
            }
        } catch (SQLException e) {
            logger.info("Code for " + user + " can't be taken in DB.");
        }
        return Optional.empty();
    }

}
