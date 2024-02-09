package jdbc;

import java.sql.*;

public class DB {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:tcp://localhost/~/test";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";

    public static void main(String[] args) throws SQLException {
//        select();
//        insert("INSERT INTO ACCOUNT VALUES (6, '1.3.3.4', 2000, 1)");
//        create_table();
    }

    public static void create_table() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //STEP 3: Execute a query
            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();

            String sql = "CREATE TABLE H2_TEST " +
                    "(ID NUMBER not NULL, " +
                    " STR VARCHAR(20), " +
                    " PRIMARY KEY ( ID ))";
            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        }
        System.out.println("Goodbye!");
    }


    public static void select() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 3: Execute a query
            System.out.println("Connected database successfully...");
            stmt = conn.createStatement();
            String sql = "SELECT ID, ACCOUNT_NUMBER, AMOUNT FROM ACCOUNT";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                int id = rs.getInt("ID");
                String accountNumber = rs.getString("ACCOUNT_NUMBER");
                Double amount = rs.getDouble("AMOUNT");

                // Display values
                System.out.println("id: " + id);
                System.out.println("accountNumber: " + accountNumber);
                System.out.println("amount: " + amount);
                System.out.println("=========================================");
            }
            // STEP 5: Clean-up environment
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        }
        System.out.println("Goodbye!");
    }

    public static void insert(/*String sql*/) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
            conn.setAutoCommit(false);
            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "INSERT INTO ACCOUNT VALUES (6, '1.3.3.4', 2000, 1)";
            stmt.executeUpdate(sql);
            ////////////////
            sql = "INSERT INTO ACCOUNT " + "VALUES (7, '5.3.2.1', 50, 2)";
            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the table...");
            ////////////////////////////
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null)
                conn.rollback();

        } finally {
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        }
        System.out.println("Goodbye!");
    }

}