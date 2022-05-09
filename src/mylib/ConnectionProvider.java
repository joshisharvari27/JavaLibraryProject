package mylib;
/** imports all the classes from the java.sql package */
import java.sql.*;

/**
 * @author Sharvari
 */
public class ConnectionProvider {
    /** static variable of type Connection */
    static Connection conn;
    /** static variable of type String
     * Stores the url of the database */
    static String url = "jdbc:mysql://localhost:3306/LIBRARY";
    /** static variable of type String
     * stores the username */
    static String user = "root";
    /** static variable of type String
     * stores the password */
    static String pass = "Chikkagubbi@8495";

    /** loads the jdbc driver*/
    public static void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            /** Creates a connection */
            conn = DriverManager.getConnection(url, user, pass);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
