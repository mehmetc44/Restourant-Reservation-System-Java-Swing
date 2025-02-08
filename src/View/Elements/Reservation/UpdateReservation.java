package View.Elements.Reservation;

import Controller.ReservationController;
import Controller.TableController;
import Model.Customer;
import Model.Reservation;
import View.Pages.ReservationsPage;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.TitledBorder;

public class UpdateReservation {

    Reservation reservation;
    private ReservationController reservationController = new ReservationController();
    private TableController tableController = new TableController();
    public UpdateReservation(ReservationsPage resPage) {
        JFrame frame = new JFrame("UpdateReservation");
        frame.setBounds(200, 300, 700, 400);
        frame.setResizable(false);
        frame.setLayout(null);

        //-----------------------NAME SURNAME
        JLabel lbl1 = new JLabel("Name-Surname: ");
        lbl1.setBounds(40, 30, 100, 20);
        frame.add(lbl1);
        JTextField nameTF = new JTextField();
        nameTF.setText(resPage.referanceReservation.getNameSurname());
        nameTF.setBounds(160, 30, 140, 30);
        frame.add(nameTF);

        //-----------------------PHONE NUMBER
        JLabel lbl2 = new JLabel("Phone Number: ");
        lbl2.setBounds(40, 80, 100, 20);
        frame.add(lbl2);
        JTextField phoneTF = new JTextField();
        phoneTF.setText(resPage.referanceReservation.getPhoneNumber());
        phoneTF.setBounds(160, 80, 140, 30);
        frame.add(phoneTF);

        //---------------------TABLE NUMBER
        JLabel lbl3 = new JLabel("Table Number: ");
        lbl3.setBounds(40, 130, 100, 20);
        frame.add(lbl3);
        int size = tableController.getAllTables().size();
        String[] tables = new String[size];
        for (int i = 0; i < size; i++) {
            tables[i] = Integer.toString(tableController.getAllTables().get(i).getTableNumber());
        }

        JComboBox<String> tableCB = new JComboBox<>(tables);
        tableCB.setBounds(160, 130, 140, 30);
        tableCB.setSelectedItem(resPage.referanceReservation.getTableNumber());
        frame.add(tableCB);

        //------------------------PERSONS
        JLabel lbl4 = new JLabel("Persons: ");
        lbl4.setBounds(40, 180, 100, 20);
        frame.add(lbl4);
        JTextField personsTF = new JTextField();
        personsTF.setText(Integer.toString(resPage.referanceReservation.getNumberOfPerson()));
        personsTF.setBounds(160, 180, 140, 30);
        frame.add(personsTF);

        //---------------------------DATE
        JLabel lbl5 = new JLabel("Date: ");
        lbl5.setBounds(40, 230, 100, 20);
        frame.add(lbl5);

        Date today = new Date();
        SpinnerDateModel model = new SpinnerDateModel(today, null, null, Calendar.DAY_OF_MONTH);
        JSpinner dateSpinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(editor);
        dateSpinner.setBounds(160, 230, 140, 30);
        frame.add(dateSpinner);
        Date selectedDate = (Date) dateSpinner.getValue();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(selectedDate);

        //----------------------------HOUR
        JLabel lbl6 = new JLabel("Hour (Begin-End): ");
        lbl6.setBounds(40, 280, 130, 20);
        frame.add(lbl6);
        JLabel lbl7 = new JLabel("/");
        lbl7.setFont(new Font("Arial", Font.PLAIN, 40));
        lbl7.setBounds(245, 283, 20, 10);
        frame.add(lbl7);

        JComboBox<String> beginHourCB = new JComboBox<>(new String[]{"09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"});
        beginHourCB.setBounds(160, 280, 40, 20);
        frame.add(beginHourCB);
        String[] minutes = new String[6];
        minutes[0] = "00";
        for (int i = 10; i < 60; i += 10) {
            minutes[i / 10] = Integer.toString(i);
        }

        JComboBox<String> beginMinuteCB = new JComboBox<>(minutes);
        beginMinuteCB.setBounds(200, 280, 40, 20);
        frame.add(beginMinuteCB);
        String beginHour = beginHourCB.getSelectedItem().toString() + ":" + beginMinuteCB.getSelectedItem().toString();

        JComboBox<String> endHourCB = new JComboBox<>(new String[]{"09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"});
        endHourCB.setBounds(260, 280, 40, 20);
        frame.add(endHourCB);

        JComboBox<String> endMinuteCB = new JComboBox<>(minutes);
        endMinuteCB.setBounds(300, 280, 40, 20);
        frame.add(endMinuteCB);
        String endHour = endHourCB.getSelectedItem().toString() + ":" + endMinuteCB.getSelectedItem().toString();

        //-----------------------------DESCRIPTION
        JTextArea description = new JTextArea();
        description.setBounds(350, 30, 300, 180);
        description.setBorder(new TitledBorder("Description"));
        frame.add(description);

        JButton applyButton = new JButton("Apply");
        applyButton.setBounds(420, 260, 110, 40);
        frame.add(applyButton);
        JButton backButton = new JButton("Back");
        backButton.setBounds(540, 260, 110, 40);
        frame.add(backButton);

        applyButton.addActionListener(e -> {
            if (!tableCB.getSelectedItem().toString().isEmpty() && !formattedDate.isEmpty() && !beginHour.isEmpty() && !endHour.isEmpty()
                    && !personsTF.getText().isEmpty() && !nameTF.getText().isEmpty() && !phoneTF.getText().isEmpty()) {

    int answer = JOptionPane.showConfirmDialog(null, 
            "Do you want to update this Reservation?", 
            "Confirm", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE);

            if (answer == JOptionPane.YES_OPTION) {
            int id =resPage.allActiveReservations.get(resPage.table.getSelectedRow()).getId();
                System.out.println(id);
            reservation = new Reservation(id, nameTF.getText(), phoneTF.getText(), Integer.parseInt((String) tableCB.getSelectedItem()),
                        formattedDate, beginHour, endHour, Integer.parseInt(personsTF.getText()), "Active");
                reservationController.updateReservation(reservation);
                ArrayList<Reservation> allActiveReservations= reservationController.getAllActiveReservations();
                
            }     
            } else {
                JOptionPane.showMessageDialog(description, "Please enter all informations!");
            }
            resPage.model1.removeRow(resPage.table.getSelectedRow());
            resPage.model1.addRow(new Object[]{reservation.getNameSurname(), reservation.getPhoneNumber(),
                reservation.getTableNumber(), reservation.getNumberOfPerson(), reservation.getDate(),
                reservation.getBeginHour() + "-" + reservation.getEndHour(), "Active"});
            
            resPage.reservationsPane.setVisible(false);
            resPage.reservationsPane.setVisible(true);
            
        });
        frame.setVisible(true);
    }
}
