import java.sql.*;

public class SQLiteJDBC {

    public static void connect() {
        Connection conn = null;
        try {
              //Class.forName("org.sqlite.JDBC");
              conn = DriverManager.getConnection("jdbc:sqlite:db/passwords.db");
              Statement statement = conn.createStatement();
              // creating the Password table
              statement.execute("CREATE table passwords (of TEXT, username TEXT, pswd TEXT)");
        } catch ( Exception e ) {
              System.err.println( e.getClass().getName() + ": " + e.getMessage() );
              System.exit(0);
        }
        System.out.println("Opened database successfully");
   }
}
