package mx.infotec.dads.kukulkan.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.DeleteDbFiles;
import org.h2.tools.RunScript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

// H2 Database Example

public class H2FileDatabaseConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(H2FileDatabaseConfiguration.class);

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/test";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";
    private static final String DB_SCHEMA = "schema.sql";

    public static boolean run() {
        try {
            // delete the H2 database named 'test' in the user home directory
            DeleteDbFiles.execute("~", "test", true);
            String schemaPath = new ClassPathResource(DB_SCHEMA).getFile().getAbsolutePath();
            RunScript.execute(DB_CONNECTION, DB_USER, DB_PASSWORD, schemaPath, null, false);
//            insertWithStatement();
        } catch (SQLException e) {
            LOGGER.error("SQLException: ", e);
            return false;
        } catch (IOException e) {
            LOGGER.error("IOException: ", e);
            return false;
        }
        return true;
    }

    // H2 SQL Prepared Statement Example
    private static void insertWithPreparedStatement() throws SQLException {
        Connection connection = getDBConnection();
        PreparedStatement createPreparedStatement = null;
        PreparedStatement insertPreparedStatement = null;
        PreparedStatement selectPreparedStatement = null;

        String CreateQuery = "CREATE TABLE PERSON(id int primary key, name varchar(255))";
        String InsertQuery = "INSERT INTO PERSON" + "(id, name) values" + "(?,?)";
        String SelectQuery = "select * from PERSON";
        try {
            connection.setAutoCommit(false);

            createPreparedStatement = connection.prepareStatement(CreateQuery);
            createPreparedStatement.executeUpdate();
            createPreparedStatement.close();

            insertPreparedStatement = connection.prepareStatement(InsertQuery);
            insertPreparedStatement.setInt(1, 1);
            insertPreparedStatement.setString(2, "Jose");
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();

            selectPreparedStatement = connection.prepareStatement(SelectQuery);
            ResultSet rs = selectPreparedStatement.executeQuery();
            LOGGER.info("H2 Database inserted through PreparedStatement");
            while (rs.next()) {
                LOGGER.info("Id " + rs.getInt("id") + " Name " + rs.getString("name"));
            }
            selectPreparedStatement.close();
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error("SQLException: ", e);
        } catch (Exception e) {
            LOGGER.error("Exception: ", e);
        } finally {
            connection.close();
        }
    }

    // H2 SQL Statement Example
    private static void insertWithStatement() throws SQLException {
        Connection connection = getDBConnection();
        Statement stmt = null;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            stmt.execute("CREATE TABLE PERSON(id int primary key, name varchar(255))");
            stmt.execute("INSERT INTO PERSON(id, name) VALUES(1, 'Anju')");
            stmt.execute("INSERT INTO PERSON(id, name) VALUES(2, 'Sonia')");
            stmt.execute("INSERT INTO PERSON(id, name) VALUES(3, 'Asha')");

            ResultSet rs = stmt.executeQuery("select * from PERSON");
            LOGGER.info("H2 Database inserted through Statement");
            while (rs.next()) {
                LOGGER.info("Id " + rs.getInt("id") + " Name " + rs.getString("name"));
            }
            stmt.close();
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error("SQLException: ", e);
        } catch (Exception e) {
            LOGGER.error("Exception: ", e);
        } finally {
            connection.close();
        }
    }

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.error("ClassNotFoundException: ", e);
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            LOGGER.error("SQLException: ", e);
        }
        return dbConnection;
    }
}