package Controller;

import Model.Employee;
import Model.Table;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeeController {
    
    public EmployeeController() {
        dbContext = new DBContext();
    }
    private DBContext dbContext;
    
    public ArrayList<Employee> getAllEmployees(){
        ArrayList<Employee> employees = new ArrayList<>();
        Employee employee;
        try{
        Connection conn = dbContext.ConnectToDatabase();
        String query = "SELECT * FROM employees";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
            employee = new Employee(rs.getString("NameSurname"), rs.getString("Tcnumber"), 
                    rs.getString("PhoneNumber"), rs.getString("username"),rs.getString("password"),
                    rs.getString("role"),rs.getString("country"),rs.getString("birthdate"));
            employees.add(employee);
            }
            conn.close();
        return employees;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList<String> getAllCountries(){
        ArrayList<String> countries = new ArrayList<>();
        String country;
        try{
        Connection conn = dbContext.ConnectToDatabase();
        String query = "SELECT * FROM countries";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                country = rs.getString("Country");
                countries.add(country);
            }
            conn.close();
        return countries;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public void addEmployee(Employee employee){
        try{
        Connection conn = dbContext.ConnectToDatabase();
        String query = "INSERT INTO employees (TCNumber, NameSurname, UserName, Password, Role, PhoneNumber, Country, BirthDate) " +
"VALUES ('" + employee.getTcNumber() + "', '" + employee.getNameSurname() + "', '" + employee.getUserName() + "', '" + employee.getPassword() + "', '" + employee.getRole() + "', '" + employee.getPhoneNumber() + "', '" + employee.getCountry() + "', '" + employee.getBirthDate() + "')";

            Statement stmt = conn.createStatement();
            stmt.execute(query);
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
    public void deleteEmployee(String tcNumber){
        try {
            Connection conn = dbContext.ConnectToDatabase();
            String query = "DELETE FROM employees WHERE tcNumber ='" + tcNumber + "' ;";
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateEmployee(Employee employee){
        try{
        Connection conn = dbContext.ConnectToDatabase();
        String query = "Update employees SET NameSurname='"+employee.getNameSurname()+
        "', UserName='"+employee.getUserName()+"', Password='"+employee.getPassword()+"', Role='"+employee.getRole()+
        "', PhoneNumber='"+employee.getPhoneNumber()+"', Country='"+employee.getCountry()+"', BirthDate='"+employee.getBirthDate()+"' where tcNumber ='"+employee.getTcNumber()+"';";
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public boolean checkTcNumberIsExist(String tcNumber){
        try{
        Connection conn = dbContext.ConnectToDatabase();
        String query = "SELECT * FROM employees where tcnumber="+tcNumber;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                conn.close();
                return true;
            }else{
                conn.close();
                return false;
            }
            
        }catch(SQLException e){
            e.printStackTrace();
            return true;
        }
    }
    public boolean checkUserNameIsExist(String userName){
        try{
        Connection conn = dbContext.ConnectToDatabase();
        String query = "SELECT * FROM employees where userName='"+userName+"'; ";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                conn.close();
                return true;
            }else{
                conn.close();
                return false;
            }
            
        }catch(SQLException e){
            e.printStackTrace();
            return true;
        }
    }
    
    public ArrayList<Employee> getCouriers(){
        ArrayList<Employee> employees = new ArrayList<>();
        Employee employee;
        try{
        Connection conn = dbContext.ConnectToDatabase();
        String query = "SELECT * FROM employees where role = 'Courier';";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
            employee = new Employee(rs.getString("NameSurname"), rs.getString("Tcnumber"), 
                    rs.getString("PhoneNumber"), rs.getString("username"),rs.getString("password"),
                    rs.getString("role"),rs.getString("country"),rs.getString("birthdate"));
            employees.add(employee);
            }
            conn.close();
        return employees;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public Employee getCourierByID(int id){
        Employee employee=null;
        try{
        Connection conn = dbContext.ConnectToDatabase();
        String query = "SELECT * FROM employees where id = "+id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
            employee = new Employee(rs.getString("NameSurname"), rs.getString("Tcnumber"), 
                    rs.getString("PhoneNumber"), rs.getString("username"),rs.getString("password"),
                    rs.getString("role"),rs.getString("country"),rs.getString("birthdate"));
            }
            conn.close();
        return employee;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    
}
