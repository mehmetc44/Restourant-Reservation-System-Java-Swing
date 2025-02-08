package Controller;

import Model.Customer;
import java.util.ArrayList;
import Model.Reservation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReservationController {
        public ReservationController() {
        dbContext = new DBContext();
    }
    private DBContext dbContext;
    public ArrayList<Reservation> getAllActiveReservations(){
        ArrayList<Reservation> reservations = new ArrayList<>();
        Reservation reservation;
        try{
        Connection conn = dbContext.ConnectToDatabase();
        String query = "SELECT * FROM reservations where statement='Active';";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String date=rs.getString("Date").substring(0, 10);
                reservation = new Reservation(rs.getInt("ID"),rs.getString("NameSurname"),rs.getString("PhoneNumber"),rs.getInt("TableNumber"),date,
                rs.getString("BeginHour"),rs.getString("EndHour"),rs.getInt("NumberOFpeople"),
                rs.getString("Statement"));
                reservations.add(reservation);
            }
            conn.close();
        return reservations;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList<Reservation> getAllReservations(){
        ArrayList<Reservation> reservations = new ArrayList<>();
        Reservation reservation;
        try{
        Connection conn = dbContext.ConnectToDatabase();
        String query = "SELECT * FROM reservations;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String date=rs.getString("Date").substring(0, 10);
                reservation = new Reservation(rs.getInt("ID"),rs.getString("NameSurname"),rs.getString("PhoneNumber"),rs.getInt("TableNumber"),date,
                rs.getString("BeginHour"),rs.getString("EndHour"),rs.getInt("NumberOFpeople"),
                rs.getString("Statement"));
                reservations.add(reservation);
            }
            conn.close();
        return reservations;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public void addReservation(Reservation r){
        try {
            Connection conn = dbContext.ConnectToDatabase();
            String query = "insert into reservations(TableNumber, NameSurname, PhoneNumber,Date,BeginHour,EndHour,NumberOfPeople,Statement) "
            + "values("+r.getTableNumber()+",'"+r.getNameSurname()+"','"+r.getPhoneNumber()+"','"+r.getDate()+"','"+r.getBeginHour()+"','"+r.getEndHour()+
            "',"+r.getNumberOfPerson()+",\"Active\");";
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM reservations\nWHERE id = (SELECT MAX(id) FROM reservations);");
            while(rs.next()){
                r.setId(rs.getInt("id"));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void cancelReservation(int id){
        try {
            Connection conn = dbContext.ConnectToDatabase();
            String query = "UPDATE reservations set STATEMENT = 'Passive' WHERE id = " + id + " ;";
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateReservation(Reservation r){
        try {
            Connection conn = dbContext.ConnectToDatabase();
            String query = "UPDATE reservations "
            + "SET NameSurname = '"+r.getNameSurname()+"', "
            + "TableNumber = "+r.getTableNumber()+" ,"
            + "PhoneNumber = '"+r.getPhoneNumber()+"', "
            + "Date = '"+r.getDate()+"', "
            + "BeginHour = '"+r.getBeginHour()+"', "
            + "EndHour = '"+r.getEndHour()+"', "
            + "NumberOfPeople = "+r.getNumberOfPerson()+" "
            + "WHERE id = " + r.getId() + " and Statement = 'Active';";
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
