package View.Pages;
import Controller.ReservationController;
import Model.Reservation;
import View.Elements.Reservation.NewReservation;
import View.Elements.Reservation.UpdateReservation;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
public class ReservationsPage extends JPanel{
    private Reservation reservation;
    public DefaultTableModel model1;
    public JScrollPane reservationsPane;
    public Reservation referanceReservation;
    public JTable table;
    public int selectedRow=-1;
    private boolean  statement=true;
    public ArrayList<Reservation> allActiveReservations;
    private ReservationController reservationController = new ReservationController();
    public ReservationsPage() {
        this.setLayout(null);
        this.setBounds(0, 0, 1000, 800);
        this.setBackground(Color.white);
        
        model1= new DefaultTableModel(new String[]{"Name-Surname","Phone Number","Table","Persons","Date","Begin-End time","Statement"}, 0);
        table = new JTable(model1);
        table.setRowHeight(30);
        reservationsPane = new JScrollPane(table);
        reservationsPane.setBounds(75, 100, 850, 400);
        
        allActiveReservations = reservationController.getAllActiveReservations();
        for(Reservation r:allActiveReservations){
        model1.addRow(new Object[]{r.getNameSurname(),r.getPhoneNumber(),r.getTableNumber(),r.getNumberOfPerson(),r.getDate(),r.getBeginHour()+"-"+r.getEndHour(),r.getStatement()});
        }
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            if(statement){
                selectedRow = table.getSelectedRow();
                if (selectedRow != -1) { 
                    referanceReservation = allActiveReservations.get(selectedRow);
                }
            } else {
                System.out.println("No selection.");
            }
        }
    }
});

 
        JLabel title = new JLabel("Reservations");
        title.setBounds(360, 50,260,35);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        
        JButton newReservation= new JButton("New Reservation");
        newReservation.setBounds(75, 550, 175, 70);
        JButton changeReservations= new JButton("All Reservations");
        changeReservations.setBounds(300, 550, 175, 70);
        JButton updateReservation= new JButton("Update");
        updateReservation.setBounds(525, 550, 175, 70);
        JButton cancelReservation= new JButton("Cancel");
        cancelReservation.setBounds(750, 550, 175, 70);
        
        newReservation.addActionListener(e -> {
            new NewReservation(this);
        });
        cancelReservation.addActionListener(e -> {
            if(this.statement==true){
                       int answer = JOptionPane.showConfirmDialog(null, 
            "Do you want to cancel this Reservation?", 
            "Confirm", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE);

        if (answer == JOptionPane.YES_OPTION) {
            selectedRow = table.getSelectedRow();
            if(selectedRow!=-1){
                allActiveReservations.remove(selectedRow);
                reservationController.cancelReservation(selectedRow);
            JOptionPane.showMessageDialog(this, "Table has been deleted.");
            }
        }
                }else{
                    JOptionPane.showMessageDialog(this, "You must open Active orders page!");
                }
          
        });
        changeReservations.addActionListener(e->{
            if(this.statement==false){
            model1.setRowCount(0);
             
            for(Reservation r:reservationController.getAllActiveReservations()){
            model1.addRow(new Object[]{r.getNameSurname(),r.getPhoneNumber(),r.getTableNumber(),r.getNumberOfPerson(),r.getDate(),r.getBeginHour()+"-"+r.getEndHour(),r.getStatement()});
            this.statement=true;
            changeReservations.setText("All Reservations");
            }}else{
                model1.setRowCount(0);
                ArrayList<Reservation> allRes = reservationController.getAllReservations();
            for(Reservation r:allRes){
            model1.addRow(new Object[]{r.getNameSurname(),r.getPhoneNumber(),r.getTableNumber(),r.getNumberOfPerson(),r.getDate(),r.getBeginHour()+"-"+r.getEndHour(),r.getStatement()});
            this.statement=false;
            changeReservations.setText("Active Reservations");
            }}
 
        });
        updateReservation.addActionListener(e->{

                if(this.selectedRow!=-1){
                    new UpdateReservation(this);
                }else{
                    JOptionPane.showMessageDialog(this, "You must open Active orders page!");
                }

            
        });

        
        this.add(updateReservation);
        this.add(cancelReservation);
        this.add(newReservation);
        this.add(changeReservations);
        this.add(title);
        this.add(reservationsPane);
        this.setVisible(true);
    }
}
