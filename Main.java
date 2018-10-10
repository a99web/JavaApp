import java.io.Console;
import java.util.Scanner;
import java.sql.*;

// private import
import javaapp.sqlite.SQLiteJDBC;


public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static Console console = System.console();

    public static void main(String args[]){
        SQLiteJDBC db = new SQLiteJDBC("passwords.db");

        System.out.println("Please input any of the below options");
        System.out.println("**********************************************************\n");
        System.out.println("1: To insert new row into the table\n" +
                           "2: To display the data for a particular service\n" +
                           "3: Display all\n" +
                           "4: Delete a row\n" +
                           "5: Exit\n");
        System.out.println("**********************************************************\n");

        while(true) {
            String service_name;
            System.out.print("Option: ");
            int option = sc.nextInt();
            switch(option) {
                case 1:
                    System.out.print("Please input the service name: ");
                    service_name = sc.next();
                    System.out.print("Please input the username: ");
                    String username = sc.next();
                    System.out.print("Please input the password: ");
                    // console.readPassword returns char[] array
                    String password = new String(console.readPassword());
                    db.insert(service_name, username, password);
                    break;

                case 2:
                    System.out.print("Please enter the service name: ");
                    service_name = sc.next();
                    db.select(service_name);
                    break;

                case 3:
                    db.selectAll();
                    break;

                case 4:
                    System.exit(0);

                default:
                    continue;
            }
        }
    }
}
