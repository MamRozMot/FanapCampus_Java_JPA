package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedDB {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    //    static final String DB_URL = "jdbc:h2:~/test";
    static final String DB_URL = "jdbc:h2:tcp://localhost/~/test";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";

    public static void main(String[] args) throws SQLException {
        insert();
    }


    public static void insert() throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            // STEP 3: Execute a query
            String query = "INSERT INTO account(id, account_number, amount, owner) VALUES(?, ?, ?, ?)";

            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, 8);
            preparedStatement.setString(2, "3.3.1.4");
            preparedStatement.setDouble(3, 200.0);
            preparedStatement.setLong(4, 1);
            preparedStatement.executeUpdate();

            System.out.println("Inserted records into the table...");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (conn != null)
                conn.close();
        }
        System.out.println("Goodbye!");
    }

}