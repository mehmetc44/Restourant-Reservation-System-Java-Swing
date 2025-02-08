package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    
        String url = "jdbc:mysql://localhost:3306/restourantreservation";
        String user = "root";  
        String password = "!Malatyali123";
        
        public Connection ConnectToDatabase() throws SQLException{
        Connection conn = DriverManager.getConnection(url, user, password); 
        return conn;
    }
}
