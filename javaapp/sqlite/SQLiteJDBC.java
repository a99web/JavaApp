package javaapp.sqlite;

import java.sql.*;

public class SQLiteJDBC {

    private Connection conn = null;

    public SQLiteJDBC(String database){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:db/" + database);
            createTable("passwords");
        }
        catch ( Exception e ) {
              System.err.println( e.getClass().getName() + ": " + e.getMessage() );
              System.exit(0);
        }
        System.out.println("Successfully connected to the database");
    }

   public void createTable(String table){
       try {
              Statement statement = conn.createStatement();
              // creating the Password table
              statement.execute("CREATE table IF NOT EXISTS passwords (row_id INTEGER PRIMARY KEY AUTOINCREMENT, service TEXT, username TEXT, password TEXT)");
        } catch ( Exception e ) {
              System.err.println( e.getClass().getName() + ": " + e.getMessage() );
              System.exit(0);
        }
        System.out.println("Created Table " + table + " successfully");
   }

   public void insert(String service, String username, String password){
        
        String sql = "INSERT INTO passwords(service,username,password) VALUES(?,?,?)";
 
        try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, service);
                pstmt.setString(2, username);
                pstmt.setString(3, password);
                pstmt.executeUpdate();
        } catch (SQLException e) {
                System.out.println(e.getMessage());
        }
                
   }

   public void select(String service_name){
     
        String sql = "SELECT * FROM passwords WHERE service='" + service_name +"'";
        
        try {
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql);
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("service") +  "\t" + 
                                   rs.getString("username") + "\t" +
                                   rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

   public void selectAll(){

        String sql = "SELECT * FROM passwords";

        try {
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql);

            System.out.format("%-10s %-20s %-20s %-20s\n", "ROW_ID", "SERVICE", "USERNAME", "PASSWORD");
            // loop through the result set
            while (rs.next()) {
                System.out.format("%-10s %-20s %-20s %-20s\n",rs.getString("row_id"), 
                                   rs.getString("service"),
                                   rs.getString("username"),
                                   rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
