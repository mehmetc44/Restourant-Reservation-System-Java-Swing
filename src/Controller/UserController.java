package Controller;

import Model.Employee;
import Model.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class UserController {
        public UserController() {
        dbContext = new DBContext();
    }
    private DBContext dbContext;
    
    public User getUser(String userName){
        User activeUser=null;
        try{
        Connection conn = dbContext.ConnectToDatabase();
        String query = "SELECT * FROM employees where username= '"+userName+"';";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
            activeUser = new User(rs.getInt("id"),rs.getString("NameSurname"), rs.getString("Tcnumber"), 
                    rs.getString("PhoneNumber"), rs.getString("username"),rs.getString("password"),
                    rs.getString("role"),rs.getString("country"),rs.getString("birthdate"));
            }
            conn.close();
        return activeUser;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public void registerUser(Employee user){
        new EmployeeController().addEmployee(user);
    }
    
    
}
