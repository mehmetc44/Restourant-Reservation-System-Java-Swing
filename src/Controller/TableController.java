package Controller;

import Model.Table;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TableController {

    public TableController() {
        dbContext = new DBContext();
    }
    private DBContext dbContext;
    
    
    
    public boolean checkTableExist(int tableNumber){
        try{
        Connection conn = dbContext.ConnectToDatabase();
        String query = "SELECT * FROM tables where tableNumber="+tableNumber;
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
    public ArrayList<Table> getAllTables(){
        ArrayList<Table> tables = new ArrayList<>();
        Table table;
        try{
        Connection conn = dbContext.ConnectToDatabase();
        String query = "SELECT * FROM tables Order by TableNumber ASC;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                table = new Table(rs.getInt("tableNumber"),rs.getInt("capacity"));
                table.setStatement(rs.getString("statement"));
                tables.add(table);
            }
            conn.close();
        return tables;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public Table getTableByTableNumber(int tableNumber){
        Table table = null;
        
        try {
            Connection conn = dbContext.ConnectToDatabase();
            String query = "SELECT tableNumber, capacity, statement FROM Tables";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                table = new Table(rs.getInt("tableNumber"),rs.getInt("capacity"));
                table.setStatement(rs.getString("statement"));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return table;
    }
    public int getTableCount() {
        int tableCount = 0;

        try {
            Connection conn = dbContext.ConnectToDatabase();
            String query = "SELECT Count(*) As tableCount FROM Tables";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                tableCount = rs.getInt("tableCount");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableCount;
    }

    public void addTable(int TableNumber, int Capacity) {
        try {
            Connection conn = dbContext.ConnectToDatabase();
            String query = "insert into tables(TableNumber,Capacity,Statement) values(" + TableNumber + "," + Capacity + ",\"Free\");";
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTable(int TableNumber) {
        try {
            Connection conn = dbContext.ConnectToDatabase();
            String query = "DELETE FROM Tables WHERE TableNumber =" + TableNumber + " ;";
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateTableCapacity(int tableNumber, int capacity) {
        try {
            Connection conn = dbContext.ConnectToDatabase();
            String query = "UPDATE tables SET capacity = "+capacity+" WHERE tableNumber="+tableNumber;
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
    public void updateTableStatement(int tableNumber, String Statement) {
        try {
            Connection conn = dbContext.ConnectToDatabase();
            String query = "UPDATE tables SET Statement = '"+Statement+"' WHERE tableNumber="+tableNumber;
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
    
}
