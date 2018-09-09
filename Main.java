import java.sql.*;

public class Main {
    public static void main(String args[]){
        SQLiteJDBC db = new SQLiteJDBC();
        db.connect();    
    }
}
